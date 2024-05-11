package com.petx.facade;

import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.api.dto.UsuarioLogadoDTO;
import com.petx.domain.Usuario;
import com.petx.mapper.UsuarioMapper;
import com.petx.service.GoogleService;
import com.petx.service.JwtServiceImpl;
import com.petx.service.UserTokenService;
import com.petx.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsuarioFacade {

    @Autowired
    private UsuarioService service;

    @Autowired
    private GoogleService googleService;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserTokenService buscarIdToken;

    @Value("${salt.password}")
    private String salt;

    public UsuarioLogadoDTO cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.toEntity(usuarioDTO);

        usuario.setSenha(usuario.getSenha() + salt);
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioCadastrado = service.cadastrar(usuario);
        String token = jwtService.gerarToken(usuarioCadastrado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setId(usuarioCadastrado.getId());
        usuarioLogadoDTO.setNome(usuarioCadastrado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public UsuarioLogadoDTO cadastrarGoogle(String tokenGoogle) {
        Usuario usuario = googleService.cadastrarGoogle(tokenGoogle);

        Usuario usuarioCadastrado = service.cadastrarGoogle(usuario);
        String token = jwtService.gerarToken(usuarioCadastrado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setId(usuarioCadastrado.getId());
        usuarioLogadoDTO.setNome(usuarioCadastrado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public UsuarioDTO buscar(String token) {
        Long id = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Usuario usuario = service.buscar(id);
        usuario.setSenha(null);
        UsuarioDTO usuarioDto = mapper.toDTO(usuario);

        return usuarioDto;
    }

    public void atualizar(UsuarioDTO usuarioDTO, String token) {
        Long id = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Usuario usuario = mapper.toEntity(usuarioDTO);

        usuario.setSenha(usuario.getSenha() + salt);
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        service.atualizar(usuario, id);
    }

    public void deletar(String token) {
        Long id = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        service.deletar(id);
    }

    public UsuarioLogadoDTO autenticar(LoginUsuarioDTO loginUsuarioDTO) {
        Usuario usuario = mapper.toEntityLogin(loginUsuarioDTO);

        usuario.setSenha(usuario.getSenha() + salt);

        Usuario usuarioLogado = service.autenticar(usuario);
        String token = jwtService.gerarToken(usuarioLogado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setId(usuarioLogado.getId());
        usuarioLogadoDTO.setNome(usuarioLogado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public UsuarioLogadoDTO autenticarGoogle(String tokenGoogle){
        Usuario usuario = googleService.autenticarGoogle(tokenGoogle);

        Usuario usuarioLogado = service.autenticarGoogle(usuario);
        String token = jwtService.gerarToken(usuarioLogado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setId(usuarioLogado.getId());
        usuarioLogadoDTO.setNome(usuarioLogado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }
}