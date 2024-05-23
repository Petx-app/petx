package com.petx.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserTokenService {

    @Value("${jwt.chave-assinatura}")
    private String chaveAssinatura;

    public UUID getIdDoUsuarioDoTokenJWT(String token) {
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(chaveAssinatura)
                    .parseClaimsJws(token)
                    .getBody();

            String userUuidString = claims.get("userUuid").toString();
            return UUID.fromString(userUuidString);
        }catch (Exception e) {
            throw new RuntimeException("erro ao descriptografar chave do usuario ", e);
        }
    }
}
