package be.rubus.security.workshop.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 */
public class JWTMain {

    public static void main(String[] args) throws ParseException, JOSEException {
        String privateContent = readFile("private.jwk");
        JWK privateJWK = JWK.parse(privateContent);

        String apiKey = privateJWK.getKeyID();

        System.out.println(createToken((RSAKey) privateJWK, apiKey));
    }

    private static String readFile(String fileName) {
        InputStream keys = JWTMain.class.getClassLoader().getResourceAsStream(fileName);
        return new Scanner(keys).useDelimiter("\\Z").next();
    }

    private static String createToken(RSAKey privateKey, String apiKey) throws JOSEException {

        // Create RSA signer
        JWSSigner signer = new RSASSASigner(privateKey);

        // Prepare JWT with claims set
        JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder();
        claimsSetBuilder.subject("Rudy");
        claimsSetBuilder.audience("Workshop");

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(apiKey).build();

        SignedJWT signedJWT = new SignedJWT(header, claimsSetBuilder.build());

        // Apply the signature
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
}
