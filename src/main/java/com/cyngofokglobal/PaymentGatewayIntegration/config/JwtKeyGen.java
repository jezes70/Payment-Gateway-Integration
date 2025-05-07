package com.cyngofokglobal.PaymentGatewayIntegration.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGen {

    public static void main(String[] args) {

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Your secure JWT secret key:\n\n" + encodedKey);
    }
}
