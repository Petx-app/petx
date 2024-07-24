package com.petx.service.usuario;

import com.petx.domain.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleService {

    private String googleEndPoint = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";

    public Usuario consultarUsuarioGoogle(String tokenGmail) {
        Map<String, Object> consultaURL = consultarGoogle(tokenGmail);

        String verificado = (String) consultaURL.get("email_verified");

        if("true".equals(verificado)){
            String email = (String) consultaURL.get("email");
            String nome = (String) consultaURL.get("name");
            String sub = (String) consultaURL.get("sub");

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setIdGoogle(sub);

            return usuario;
        }

        throw new RuntimeException("erro ao se autenticar com google");
    }

    private Map<String, Object> consultarGoogle(String tokenGmail) {
        RestTemplate restTemplate = new RestTemplate();

        String url = googleEndPoint + tokenGmail;

        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (ResourceAccessException | HttpServerErrorException e) {
            throw new RuntimeException("Erro ao acessar informações do perfil do usuário do Google");
        }
    }

}
