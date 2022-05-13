package be.rubus.security.workshop.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashWithSalt {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String passwordToHash = "mySecretPassword";

        long start = System.currentTimeMillis();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] hashedPassword = md.digest(passwordToHash.getBytes());

        long end = System.currentTimeMillis();

        Base64.Encoder encoder = Base64.getEncoder();
        String hashed = encoder.encodeToString(salt) + ":" + encoder.encodeToString(hashedPassword);
        System.out.println(hashed);

        System.out.printf("Time to hash %s%n", end-start);

    }
}
