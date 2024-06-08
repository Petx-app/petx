package com.petx.service.usuario;

import com.petx.domain.usuario.*;
import com.petx.repository.UsuarioRepository;
import com.petx.repository.ValidacaoSenhaRepository;
import com.petx.repository.ValidacaoUsuarioRepository;
import com.petx.utils.Criptografia;
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

    @Autowired
    Criptografia criptografia;

    public boolean verificarEmail(EmailValidar email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email.getEmail().toLowerCase());
        return optionalUsuario.isEmpty();
    }

    public void cadastrarEmailValidacao(ValidacaoEmail validarEmail){
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(validarEmail.getEmail());

        if(optionalValidacaoEmail.isPresent()){
            ValidacaoEmail validacaoEmail = optionalValidacaoEmail.get();
            validacaoUsuarioRepository.deleteById(validacaoEmail.getId());
        }
        validacaoUsuarioRepository.save(validarEmail);
    }

    public boolean validarEmail(CodigoValidacaoEmail codigoValidacaoEmail){
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(codigoValidacaoEmail.getEmail());

        if(optionalValidacaoEmail.isPresent()){
            ValidacaoEmail validacaoEmail = optionalValidacaoEmail.get();

            if(validacaoEmail.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                    && ChronoUnit.MINUTES.between(validacaoEmail.getHoraInserida(), LocalDateTime.now()) <= 2) {
                if (validacaoEmail.getCodigo().equals(codigoValidacaoEmail.getCodigoVerificacao().toUpperCase())) {
                    validacaoEmail.setValidado(true);
                    validacaoUsuarioRepository.save(validacaoEmail);
                    return true;
                }
                throw new RuntimeException("Codigo incorreto");
            }
            validacaoUsuarioRepository.deleteById(optionalValidacaoEmail.get().getId());
            throw new RuntimeException("Codigo expirado");
        }
        throw new RuntimeException("Erro ao salvar usuario sem autenticar");
    }

    public boolean confirmarEmailValidado(String email){
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(email);
        if(optionalValidacaoEmail.isPresent()){
            ValidacaoEmail validacaoEmail = optionalValidacaoEmail.get();

            if(validacaoEmail.isValidado()){
                return true;
            }else{
                validacaoUsuarioRepository.deleteById(validacaoEmail.getId());
                throw new RuntimeException("email nao esta validado.");
            }
        }
        return false;
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

    public boolean validarLinkTrocaSenha(UUID codigoValidacao){
        Optional<ValidacaoSenha> optionalValidacaoSenha = validacaoSenhaRepository.findByCodigoValidar(codigoValidacao);

        if(optionalValidacaoSenha.isPresent()){
            ValidacaoSenha validacaoSenha = optionalValidacaoSenha.get();

            if(validacaoSenha.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                    && ChronoUnit.MINUTES.between(validacaoSenha.getHoraInserida(), LocalDateTime.now()) <= 15) {
                return true;
            }
            validacaoSenhaRepository.deleteById(validacaoSenha.getId());
            throw new RuntimeException("Link expirado");
        }
        throw new RuntimeException("Link expirado");
    }

    public void trocarSenha(TrocarSenha senhaNova){
        Optional<ValidacaoSenha> optionalValidarSenha = validacaoSenhaRepository.findByCodigoValidar(senhaNova.getCodigoValidacao());

        if(optionalValidarSenha.isPresent()){
            ValidacaoSenha validacaoSenha = optionalValidarSenha.get();

            if(validacaoSenha.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                    && ChronoUnit.MINUTES.between(validacaoSenha.getHoraInserida(), LocalDateTime.now()) <= 15) {

                Optional<Usuario> optionalUsuario = usuarioRepository.findById(validacaoSenha.getUsuario().getUuid());
                if (optionalUsuario.isPresent()) {
                    Usuario usuario = optionalUsuario.get();
                    usuario.setSenha(criptografia.criptogafarSenha(senhaNova.getSenha(), usuario.getUuid()));
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