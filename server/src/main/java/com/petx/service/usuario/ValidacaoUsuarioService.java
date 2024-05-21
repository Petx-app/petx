package com.petx.service.usuario;

import com.petx.domain.usuario.*;
import com.petx.repository.UsuarioRepository;
import com.petx.repository.ValidacaoSenhaRepository;
import com.petx.repository.ValidacaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class ValidacaoUsuarioService {

    @Autowired
    private ValidacaoUsuarioRepository validacaoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidacaoSenhaRepository validacaoSenhaRepository;

    public boolean verificarEmail(EmailValidar email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email.getEmail().toLowerCase());
        return optionalUsuario.isEmpty();
    }

    public void cadastrarEmailValidacao(ValidacaoEmail validarEmail){
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(validarEmail.getEmail());

        if(optionalValidacaoEmail.isPresent()){
            throw new RuntimeException("ja tem email cadastrado");
        }
        validacaoUsuarioRepository.save(validarEmail);
    }

    public boolean validarEmail(String email, String codigoVerificacaoEmail){
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(email);

        if(optionalValidacaoEmail.isPresent()){
            ValidacaoEmail validacaoEmail = optionalValidacaoEmail.get();

            if(validacaoEmail.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                    && ChronoUnit.MINUTES.between(validacaoEmail.getHoraInserida(), LocalDateTime.now()) <= 2) {
                if (validacaoEmail.getCodigo().equals(codigoVerificacaoEmail.toUpperCase())) {
                    return true;
                }
                throw new RuntimeException("Codigo incorreto");
            }
            validacaoUsuarioRepository.deleteById(optionalValidacaoEmail.get().getId());
            throw new RuntimeException("Codigo expirado");
        }
        throw new RuntimeException("Erro ao salvar usuario sem autenticar");
    }

    public UUID gerarCodigoTrocarSenha(EmailValidar email){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email.getEmail());

        if(optionalUsuario.isPresent()){
            ValidacaoSenha validacaoSenha = new ValidacaoSenha();
            validacaoSenha.setUsuario(optionalUsuario.get());
            ValidacaoSenha codigoValidacao = validacaoSenhaRepository.save(validacaoSenha);
            return codigoValidacao.getCodigoValidar();
        }
        throw new RuntimeException("erro ao localizar email");
    }

    public void trocarSenha(TrocarSenha senhaNova){
        Optional<ValidacaoSenha> optionalValidarSenha = validacaoSenhaRepository.findByCodigoValidar(senhaNova.getCodigoValidacao());

        if(optionalValidarSenha.isPresent()){
            ValidacaoSenha validacaoSenha = optionalValidarSenha.get();

            if(validacaoSenha.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                    && ChronoUnit.MINUTES.between(validacaoSenha.getHoraInserida(), LocalDateTime.now()) <= 5) {
                Optional<Usuario> optionalUsuario = usuarioRepository.findById(validacaoSenha.getUsuario().getId());
                if (optionalUsuario.isPresent()) {
                    Usuario usuario = optionalUsuario.get();
                    usuario.setSenha(senhaNova.getSenha());
                    usuarioRepository.save(usuario);
                    validacaoSenhaRepository.deleteById(validacaoSenha.getId());
                }
           }
            else{
                validacaoSenhaRepository.deleteById(validacaoSenha.getId());
                throw new RuntimeException("Codigo Expirado, solicite troca de senha novamente");
            }
        }
        else{
            throw new RuntimeException("Codigo Expirado");
        }
    }
}