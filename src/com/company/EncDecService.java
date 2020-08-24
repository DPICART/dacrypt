package com.company;

import javax.crypto.*;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncDecService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private KeyGenerator keyGenerator;
    private Cipher cipher;

    public EncDecService() {
    }

    public String encrypt(String in, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        getCipher().init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] utf8 = in.getBytes(Charset.forName("UTF-8"));
        byte[] out = getCipher().doFinal(utf8);
        return Base64.getEncoder().encodeToString(out);
    }

    public String decrypt(String in, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        getCipher().init(Cipher.DECRYPT_MODE, secretKey);
        byte[] base64dec = Base64.getDecoder().decode(in.getBytes());
        byte[] out = getCipher().doFinal(base64dec);
        return new String(out);
    }

    public SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        return getKeyGenerator().generateKey();
    }

    private KeyGenerator getKeyGenerator() throws NoSuchAlgorithmException {
        if (null == this.keyGenerator) {
            this.keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        }
        return this.keyGenerator;
    }

    private Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (null == this.cipher) {
            this.cipher = Cipher.getInstance(TRANSFORMATION);
        }
        return cipher;
    }

}
