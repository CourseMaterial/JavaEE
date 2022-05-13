package be.rubus.security.workshop.jwt;


import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 *
 */
public class JWKManager {

    public static void main(String[] args) {
        String xApiKey = UUID.randomUUID().toString();
        JWK jwk = make(2048, xApiKey);

        System.out.println("x-api-key");
        System.out.println(xApiKey);

        System.out.println("Private");
        System.out.println(jwk.toJSONString());

        System.out.println("Public");
        System.out.println(jwk.toPublicJWK().toJSONString());
    }

    private static RSAKey make(int keySize, String kid) {

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(keySize);
            KeyPair kp = generator.generateKeyPair();

            RSAPublicKey pub = (RSAPublicKey) kp.getPublic();
            RSAPrivateKey priv = (RSAPrivateKey) kp.getPrivate();

            return new RSAKey.Builder(pub)
                    .privateKey(priv)
                    .keyID(kid)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unexpected exception ", e);
        }
    }
}
