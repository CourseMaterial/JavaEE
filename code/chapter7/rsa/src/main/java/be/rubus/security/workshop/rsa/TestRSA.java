package be.rubus.security.workshop.rsa;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class TestRSA {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();


        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        String secretMessage = "Workshop secret message";

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessage.getBytes(StandardCharsets.UTF_8));
        System.out.println(Base64.getEncoder().encodeToString(encryptedMessageBytes));


        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        System.out.println(decryptedMessage);
    }
}
