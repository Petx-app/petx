package com.petx.service.security;

import com.petx.domain.admin.Admin;
import com.petx.domain.usuario.Usuario;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

@Service
public class JwtServiceImpl {

    @Value("${jwt.expiracao}")
    private String expiracao;

    @Value("${jwt.chave-assinatura}")
    private String chaveAssinatura;

    @Value("${jwt.chave-criptografia}")
    private String chaveCriptografia;

    private SecretKey getSecretKey() {
        byte[] chaveBytes = Base64.getDecoder().decode(chaveCriptografia);
        return new SecretKeySpec(chaveBytes, "AES");
    }

    public String gerarToken(Usuario usuario) throws JOSEException {
        long exp = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date data = Date.from(instant);

        String dataExpiracaoToken = dataHoraExpiracao.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(usuario.getEmail())
                .expirationTime(data)
                .claim("nome", usuario.getNome())
                .claim("horaExpiracao", dataExpiracaoToken)
                .claim("userUuid", usuario.getUuid().toString())
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claims
        );

        signedJWT.sign(new MACSigner(chaveAssinatura));

        JWEObject jweObject = new JWEObject(
                new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM),
                new Payload(signedJWT)
        );

        jweObject.encrypt(new DirectEncrypter(getSecretKey()));

        return jweObject.serialize();
    }

    public String gerarTokenAdmin(Admin admin) throws JOSEException {
        long exp = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date data = Date.from(instant);

        String dataExpiracaoToken = dataHoraExpiracao.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(admin.getUsuario())
                .expirationTime(data)
                .claim("usuario", admin.getUsuario())
                .claim("horaExpiracao", dataExpiracaoToken)
                .claim("userid", admin.getId())
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claims
        );

        signedJWT.sign(new MACSigner(chaveAssinatura));

        JWEObject jweObject = new JWEObject(
                new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM),
                new Payload(signedJWT)
        );

        jweObject.encrypt(new DirectEncrypter(getSecretKey()));

        return jweObject.serialize();
    }

    public JWTClaimsSet obterClaims(String token) throws Exception {
        JWEObject jweObject = JWEObject.parse(token);
        jweObject.decrypt(new DirectDecrypter(getSecretKey()));

        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
        if (signedJWT == null) {
            throw new IllegalArgumentException("Invalid JWE payload.");
        }

        return signedJWT.getJWTClaimsSet();
    }

    public boolean isTokenValido(String token) {
        try {
            JWTClaimsSet claims = obterClaims(token);
            java.util.Date dataEx = claims.getExpirationTime();
            LocalDateTime dataExpiracao = dataEx.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(dataExpiracao);
        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) {
        try {
            JWTClaimsSet claims = obterClaims(token);
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}