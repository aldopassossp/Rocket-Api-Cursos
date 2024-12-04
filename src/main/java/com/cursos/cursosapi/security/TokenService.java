package com.cursos.cursosapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cursos.cursosapi.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
//public class TokenService {
//
//    @Value("${security.token.secret}")
//    private String secretKey;
//
//    public String gerarToken(Usuario usuario){
//        try {
//            var algorithm = Algorithm.HMAC256(secretKey);
//            return JWT.create()
//                    .withIssuer("Cursos")
//                    .withSubject(usuario.getUsername())
//                    .withClaim("id", usuario.getId())
//                    .withExpiresAt(dataExpiracao())
//                    .sign(algorithm);
//        } catch (JWTCreationException exception){
//            throw new RuntimeException("Erro ao gerar o token");
//        }
//    }
//
//    public String getSubject(String tokenJWT){
//        System.out.println(tokenJWT);
//        try {
//            var algorithm = Algorithm.HMAC256(secretKey);
//            return JWT.require(algorithm)
//                    .withIssuer("Cursos")
//                    .build()
//                    .verify(tokenJWT)
//                    .getSubject();
//
//        } catch (JWTVerificationException exception){
//            exception.printStackTrace();
//            throw new RuntimeException("Token JWT inválido ou expirado! " + tokenJWT);
//        }
//    }
//
//    private Instant dataExpiracao() {
//        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
//    }
//}
public class TokenService {

    @Value("${security.token.secret}")
    private String secretKey;

    private static final int EXPIRATION_HOURS = 2;

    public String gerarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secretKey); // Garante que a chave secreta foi carregada
            return JWT.create()
                    .withIssuer("Cursos")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId()) // Adiciona o ID como uma claim
                    .withExpiresAt(dataExpiracao())  // Define a data de expiração
                    .sign(algorithm);               // Assina o token com o algoritmo e a chave secreta
        } catch (IllegalArgumentException | JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }


    public Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(EXPIRATION_HOURS).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("Cursos")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!", exception);
        }
    }

    private Instant dataExpiracao() {
        return getExpirationDate();
        //return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

