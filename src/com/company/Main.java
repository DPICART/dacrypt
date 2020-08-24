package com.company;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

        String message = "To be or not to be.";

        runOne(message);
        runTwo(message);

    }

    private static void runOne(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        System.out.println("\nRun one.");
        EncDecService encDecService = new EncDecService();
        SecretKey secretKey = encDecService.generateSecretKey();
        String encrypted = encDecService.encrypt(message, secretKey);
        String clear = encDecService.decrypt(encrypted, secretKey);

        System.out.println(encrypted);
        System.out.println(clear);
    }

    private static void runTwo(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        System.out.println("\nRun two.");
        EncDecService encDecService = new EncDecService();
        SecretKey secretKey = encDecService.generateSecretKey();
        String encrypted = encDecService.encrypt(message, secretKey);
        int times = 10;
        for (int i = 0; i < times; i++) {
            encrypted = encDecService.encrypt(encrypted, secretKey);
        }
        System.out.println(String.format("Encrypted %s times: %s", times, encrypted));
        for (int i = 0; i < times+1; i++) {
            encrypted = encDecService.decrypt(encrypted, secretKey);
        }
        System.out.println(String.format("Decrypted %s times: %s", times, encrypted));
    }
}
