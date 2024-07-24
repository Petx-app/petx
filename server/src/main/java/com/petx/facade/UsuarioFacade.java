package com.petx.facade;

import com.nimbusds.jose.JOSEException;
import com.petx.api.dto.Usuario.*;
import com.petx.domain.usuario.*;
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

    public UsuarioLogadoDTO cadastrar(UsuarioDTO usuarioDTO) throws JOSEException {
        if(validacaoUsuarioService.confirmarEmailValidado(usuarioDTO.getEmail())){
            Usuario usuario = mapper.toEntity(usuarioDTO);

            Usuario usuarioCadastrado = service.cadastrar(usuario);
            String token = jwtService.gerarToken(usuarioCadastrado);

            UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
            usuarioLogadoDTO.setNome(usuarioCadastrado.getNome().split(" ")[0]);
            usuarioLogadoDTO.setToken(token);

            return usuarioLogadoDTO;
        }
        return null;
    }

    public UsuarioLogadoDTO cadastrarGoogle(String tokenGoogle) throws JOSEException {
        Usuario usuario = googleService.consultarUsuarioGoogle(tokenGoogle);

        Usuario usuarioLogado;
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();

        if(validacaoUsuarioService.verificarEmail(usuario.getEmail())){
            usuarioLogado = service.cadastrarGoogle(usuario);
        } else{
            usuarioLogado = service.autenticarGoogle(usuario);
        }

        String token = jwtService.gerarToken(usuarioLogado);
        usuarioLogadoDTO.setNome(usuarioLogado.getNome());
        usuarioLogadoDTO.setToken(token);
        usuarioLogadoDTO.setCadastroFinalizado(usuarioLogado.getCadastroFinalizado());

        return usuarioLogadoDTO;
    }

    public UsuarioDTO buscar(String token) {
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Usuario usuario = service.buscar(uuid);
        usuario.setSenha(null);
        UsuarioDTO usuarioDto = mapper.toDTO(usuario);

        return usuarioDto;
    }

    public void atualizar(UsuarioAtualizarDTO usuarioAtualizarDTO, String token) {
        Usuario usuario = mapper.toEntityUsuarioAtualizar(usuarioAtualizarDTO);
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);

        service.atualizar(usuario, uuid);
    }

    public void atualizarSenha(TrocarSenhaDTO trocarSenhaDTO, String token){
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        AtualizarSenha atualizarSenha = mapper.toEntityAtualizarSenha(trocarSenhaDTO, uuid);

        service.atualizarSenha(atualizarSenha, uuid);
    }

    public void deletar(String token) {
        UUID uuid = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        service.deletar(uuid);
    }

    public UsuarioLogadoDTO autenticar(LoginUsuarioDTO loginUsuarioDTO) throws JOSEException {
        Usuario usuario = mapper.toEntityLogin(loginUsuarioDTO);

        Usuario usuarioLogado = service.autenticar(usuario);
        String token = jwtService.gerarToken(usuarioLogado);

        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setNome(usuarioLogado.getNome());
        usuarioLogadoDTO.setToken(token);

        return usuarioLogadoDTO;
    }

    public void validarEmail(EmailDTO emailDTO){
        EmailValidar email = mapper.toEntityEmail(emailDTO);

        if(validacaoUsuarioService.verificarEmail(email.getEmail())){
            ValidacaoEmail validacaoEmail = gerarCodigo.gerarCodigoVerificacaoEmail(email.getEmail());
            emailService.validarEmail(validacaoEmail);
            validacaoUsuarioService.cadastrarEmailValidacao(validacaoEmail);
        } else{
            throw new RuntimeException("usuario já existe");
        }
    }

    public void confirmarEmail(CodigoValidacaoEmailDTO codigoValidacaoEmailDTO){
        CodigoValidacaoEmail codigoValidacaoEmail = mapper.toEntityCodigoValidacaoEmail(codigoValidacaoEmailDTO);
        validacaoUsuarioService.validarEmail(codigoValidacaoEmail);
    }

    public void esqueceuSenha(EmailDTO emailDTO){
        EmailValidar email = mapper.toEntityEmail(emailDTO);

        if(!validacaoUsuarioService.verificarEmail(email.getEmail())){
            UUID codigo = validacaoUsuarioService.gerarCodigoTrocarSenha(email);
            emailService.esqueceuSenha(email, codigo);
        } else{
            throw new RuntimeException("Email não existe, é necessario cadastrar!");
        }
    }

    public void validarLink(UUID codigoValidacao){
        validacaoUsuarioService.validarLinkTrocaSenha(codigoValidacao);
    }

    public void trocarSenha(TrocarSenhaDTO senha, UUID codigoValidacao){
        TrocarSenha senhaNova  = mapper.toEntityTrocarSenha(senha, codigoValidacao);
        validacaoUsuarioService.trocarSenha(senhaNova);
    }
}