package com.petx.facade;

import com.petx.api.dto.Usuario.*;
import com.petx.domain.usuario.EmailValidar;
import com.petx.domain.usuario.TrocarSenha;
import com.petx.domain.usuario.Usuario;
import com.petx.domain.usuario.ValidacaoEmail;
import com.petx.mapper.usuario.UsuarioMapper;
import com.petx.service.security.JwtServiceImpl;
import com.petx.service.security.UserTokenService;
import com.petx.service.usuario.EmailService;
import com.petx.service.usuario.GoogleService;
import com.petx.service.usuario.UsuarioService;
import com.petx.service.usuario.ValidacaoUsuarioService;
import com.petx.utils.GerarCodigoVerificacaoEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

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
    private UserTokenService buscarIdToken;

    @Autowired
    private EmailService emailService;

    @Autowired
    private GerarCodigoVerificacaoEmail gerarCodigo;

    @Autowired
    private ValidacaoUsuarioService validacaoUsuarioService;

    public UsuarioLogadoDTO cadastrar(UsuarioDTO usuarioDTO) {
        validacaoUsuarioService.validarEmail(usuarioDTO.getEmail().toLowerCase(), usuarioDTO.getCodigoVerificacao());

        Usuario usuario = mapper.toEntity(usuarioDTO);

        Usuario usuarioCadastrado = service.cadastrar(usuario);
        String token = jwtService.gerarToken(usuarioCadastrado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setNome(usuarioCadastrado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public UsuarioLogadoDTO cadastrarGoogle(String tokenGoogle) {
        Usuario usuario = googleService.cadastrarGoogle(tokenGoogle);

        Usuario usuarioCadastrado = service.cadastrarGoogle(usuario);
        String token = jwtService.gerarToken(usuarioCadastrado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setNome(usuarioCadastrado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public UsuarioDTO buscar(String token) {
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Usuario usuario = service.buscar(uuid);
        usuario.setSenha(null);
        UsuarioDTO usuarioDto = mapper.toDTO(usuario);

        return usuarioDto;
    }

    public void atualizar(UsuarioDTO usuarioDTO, String token) {
        Usuario usuario = mapper.toEntity(usuarioDTO);
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);

        service.atualizar(usuario, uuid);
    }

    public void deletar(String token) {
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        service.deletar(uuid);
    }

    public UsuarioLogadoDTO autenticar(LoginUsuarioDTO loginUsuarioDTO) {
        Usuario usuario = mapper.toEntityLogin(loginUsuarioDTO);

        Usuario usuarioLogado = service.autenticar(usuario);
        String token = jwtService.gerarToken(usuarioLogado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setNome(usuarioLogado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public UsuarioLogadoDTO autenticarGoogle(String tokenGoogle){
        Usuario usuario = googleService.autenticarGoogle(tokenGoogle);
        Usuario usuarioLogado = service.autenticarGoogle(usuario);
        String token = jwtService.gerarToken(usuarioLogado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setNome(usuarioLogado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public void validarEmail(EmailDTO emailDTO){
        EmailValidar email = mapper.toEntityEmail(emailDTO);

        if(validacaoUsuarioService.verificarEmail(email)){
            ValidacaoEmail validacaoEmail = gerarCodigo.gerarCodigoVerificacaoEmail(email.getEmail());
            emailService.validarEmail(validacaoEmail);
            validacaoUsuarioService.cadastrarEmailValidacao(validacaoEmail);
        } else{
            throw new RuntimeException("usuario já existe");
        }
    }

    public void esqueceuSenha(EmailDTO emailDTO){
        EmailValidar email = mapper.toEntityEmail(emailDTO);

        if(!validacaoUsuarioService.verificarEmail(email)){
            UUID codigo = validacaoUsuarioService.gerarCodigoTrocarSenha(email);
            emailService.esqueceuSenha(email, codigo);
        } else{
            throw new RuntimeException("usuario nao existe, primeiro cadastre");
        }
    }

    public void trocarSenha(TrocarSenhaDTO senha, UUID codigoValidacao){
        TrocarSenha senhaNova  = mapper.toEntityTrocarSenha(senha, codigoValidacao);
        validacaoUsuarioService.trocarSenha(senhaNova);
    }
}