package com.petx.service.security;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserTokenService {

    @Value("${jwt.chave-criptografia}")
    private String chaveCriptografia;

    @Autowired
    JwtServiceImpl jwtServiceImpl;

    public UUID getIdDoUsuarioDoTokenJWT(String token) {
        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            JWTClaimsSet claims = jwtServiceImpl.obterClaims(token);
            String uuid = claims.getStringClaim("userUuid");
            return UUID.fromString(uuid);
        }catch (Exception e) {
            throw new RuntimeException("erro ao descriptografar chave do usuario ", e);
        }
    }
}
