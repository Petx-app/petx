package com.petx.service.scheduler;

import com.petx.domain.usuario.ValidacaoEmail;
import com.petx.repository.ValidacaoUsuarioRepository;
import com.petx.utils.GerarHoraAtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.petx.utils.GerarHoraAtual.horaAtual;

@Service
public class ScheduledCodigoDeVerificacaoEmailDeletarService {

    @Autowired
    private ValidacaoUsuarioRepository validacaoUsuarioRepository;

    @Autowired
    private GerarHoraAtual gerarHoraAtual;

    @Scheduled(cron = "0 0 11 * * *")
    public void tempoExpiracao(){
        List<ValidacaoEmail> registroCodigoVerificacao = validacaoUsuarioRepository.findAll();
        System.out.println("rodando shcundele");
        if(!registroCodigoVerificacao.isEmpty()){
            for(ValidacaoEmail registro : registroCodigoVerificacao){
                if((horaAtual() - registro.getHoraInserida()) > 2 ){
                    validacaoUsuarioRepository.deleteById(registro.getId());
                }
            }
        }
    }
}
