package be.rubus.security.workshop.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SimpleHash {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String stringToHash = "mySecretPassword";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(stringToHash.getBytes());
        String hashed = Base64.getEncoder().encodeToString(messageDigest.digest());
        System.out.println(hashed);
    }
}
