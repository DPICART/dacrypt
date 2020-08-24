package com.company;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

        EncDecService encDecService = new EncDecService();

        SecretKey secretKey = encDecService.generateSecretKey();

        String message = "I am a Teapot";

        String encrypted = encDecService.encrypt(message, secretKey);

        System.out.println(encrypted);

        String clear = encDecService.decrypt(encrypted, secretKey);

        System.out.println(clear);

    }
}
