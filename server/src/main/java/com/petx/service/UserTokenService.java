package com.petx.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    @Value("${jwt.chave-assinatura}")
    private String chaveAssinatura;

    public Long getIdDoUsuarioDoTokenJWT(String token) {
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(chaveAssinatura)
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = Long.valueOf(claims.get("userid").toString());
            return userId;
        }catch (Exception e) {
            throw new RuntimeException("erro ao descriptografar chave do usuario ", e);
        }
    }
}
