package com.petx.service.usuario;

import com.petx.domain.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleService {

    private String googleEndPoint = "www.rota.com.br";

    public Usuario cadastrarGoogle(String tokenGmail) {
        Map<String, Object> consultaURL = consultarGoogle(tokenGmail);

        //ajustar apos a rota correta for especificada
        String email = (String) consultaURL.get("email");
        String nome = (String) consultaURL.get("name");
        String telefone = (String) consultaURL.get("phone");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setTelefone(telefone);

        return usuario;
    }

    public Usuario autenticarGoogle(String tokenGmail) {
        Map<String, Object> consultaURL = consultarGoogle(tokenGmail);

        //ajustar apos a rota correta for especificada
        String email = (String) consultaURL.get("email");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        return usuario;
    }

    private Map<String, Object> consultarGoogle(String tokenGmail) {
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format(googleEndPoint, tokenGmail);

        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (ResourceAccessException | HttpServerErrorException e) {
            throw new RuntimeException("Erro ao acessar informações do perfil do usuário do Google");
        }
    }

}
