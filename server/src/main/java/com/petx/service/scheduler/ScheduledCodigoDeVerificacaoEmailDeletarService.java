package com.petx.service.scheduler;

import com.petx.domain.usuario.ValidacaoEmail;
import com.petx.domain.usuario.ValidacaoSenha;
import com.petx.repository.ValidacaoSenhaRepository;
import com.petx.repository.ValidacaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ScheduledCodigoDeVerificacaoEmailDeletarService {

    @Autowired
    private ValidacaoUsuarioRepository validacaoUsuarioRepository;

    @Autowired
    private ValidacaoSenhaRepository validacaoSenhaRepository;

    @Scheduled(cron = "0 0 11 * * *")
    public void tempoExpiracaoEmail(){
        List<ValidacaoEmail> registroCodigoVerificacao = validacaoUsuarioRepository.findAll();
        if(!registroCodigoVerificacao.isEmpty()){
            for(ValidacaoEmail registro : registroCodigoVerificacao){
                if(registro.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                        && ChronoUnit.MINUTES.between(registro.getHoraInserida(), LocalDateTime.now()) > 2) {
                    validacaoUsuarioRepository.deleteById(registro.getId());
                }
            }
        }
    }

    @Scheduled(cron = "0 0 11 * * *")
    public void tempoExpiracaoSenha(){
        List<ValidacaoSenha> registroCodigoVerificacao = validacaoSenhaRepository.findAll();
        if(!registroCodigoVerificacao.isEmpty()){
            for(ValidacaoSenha registro : registroCodigoVerificacao){
                if(registro.getHoraInserida().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                        && ChronoUnit.MINUTES.between(registro.getHoraInserida(), LocalDateTime.now()) > 5) {
                    validacaoSenhaRepository.deleteById(registro.getId());
                }
            }
        }
    }
}