package com.petx.service.usuario;

import com.petx.domain.usuario.EmailValidar;
import com.petx.domain.usuario.TrocarSenha;
import com.petx.domain.usuario.Usuario;
import com.petx.domain.usuario.ValidacaoEmail;
import com.petx.repository.UsuarioRepository;
import com.petx.repository.ValidacaoUsuarioRepository;
import com.petx.utils.GerarHoraAtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.petx.utils.GerarHoraAtual.horaAtual;

@Service
public class ValidacaoUsuarioService {

    @Autowired
    private ValidacaoUsuarioRepository validacaoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerarHoraAtual gerarHoraAtual;


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
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(email.toLowerCase());

        if(optionalValidacaoEmail.isPresent()){
            ValidacaoEmail validacaoEmail = optionalValidacaoEmail.get();

            if(horaAtual() - validacaoEmail.getHoraInserida() < 2) {
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

    public void trocarSenha(TrocarSenha trocarSenha){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(trocarSenha.getEmail().toLowerCase());

        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
           if(usuario.getEmail().equals(trocarSenha.getEmail())){
                usuario.setSenha(trocarSenha.getSenha());
                usuarioRepository.save(usuario);

                Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(trocarSenha.getEmail());
                if (optionalValidacaoEmail.isPresent()) {
                    ValidacaoEmail validacaoEmail = optionalValidacaoEmail.get();
                    validacaoUsuarioRepository.deleteById(validacaoEmail.getId());
                }
        }
        }else{
            throw new RuntimeException("erro ao alterar senha");
        }
    }
}