package be.rubus.security.workshop.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Base64;

/**
 *
 */
public class JWSHello {

    public static void main(String[] args) throws JOSEException, ParseException {

        SecureRandom random = new SecureRandom();
        byte[] sharedSecret = new byte[32]; // == 256 Bit, \ OKfor256-bitbasedhash
        random.nextBytes(sharedSecret);

        JWSSigner signer = new MACSigner(sharedSecret);

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload("Hello, world!"));

        jwsObject.sign(signer);

        String s = jwsObject.serialize();
        System.out.println("JWT = " + s);

        JWSObject jws = JWSObject.parse(s);

        JWSVerifier verifier = new MACVerifier(sharedSecret);

        if (jws.verify(verifier)) {
            System.out.println("Payload of JWT " + jws.getPayload());
        }

        String payload = "SGVsbG8sIHdvcmxkIQ";  // Between the . (dots)

        byte[] content = Base64.getDecoder().decode(payload);
        // Use the Base64 of the JDK don't need dependencies
        System.out.println("BASE64 decoded =" + new String(content));

        String tamperedPayload = Base64.getEncoder().encodeToString("Tamperedmessage".getBytes());
        System.out.println("tampered Payload " + tamperedPayload);

        String tamperedMessage = "eyJhbGciOiJIUzI1NiJ9." + tamperedPayload.replace("=", "") + ".EV6iwJwHAPEPGosSnaqk7oa8z8YHiCiv21pci5e9Wjc";

        JWSObject myMessage = JWSObject.parse(tamperedMessage);
        System.out.println("Verify success ? " + myMessage.verify(verifier));
    }

}
