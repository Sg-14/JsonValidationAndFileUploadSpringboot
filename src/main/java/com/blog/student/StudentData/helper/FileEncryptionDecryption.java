package com.blog.student.StudentData.helper;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileEncryptionDecryption {
    private static final String STRATEGY = "AES";

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(STRATEGY);
        keyGenerator.init(256); // AES-256 encryption
        return keyGenerator.generateKey();
    }

    public static void encryptFile(Key key, File inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(STRATEGY);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputData = new byte[(int)inputFile.length()];
        inputStream.read(inputData);

        byte[] outputData = cipher.doFinal(inputData);

        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        fileOutputStream.write(outputData);

        fileOutputStream.close();
        inputStream.close();
    }

    public static void decryptFile(Key key, File inputFile, File outputFile) throws Exception{
        Cipher cipher = Cipher.getInstance(STRATEGY);
        cipher.init(Cipher.DECRYPT_MODE, key);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputData = new byte[(int) inputFile.length()];
        inputStream.read(inputData);

        byte[] outputData = cipher.doFinal(inputData);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputData);

        outputStream.close();
        inputStream.close();
    }

    public static String keyToString(SecretKey key){
        String response = Base64.getEncoder().encodeToString(key.getEncoded());
        return response;
    }

    public static SecretKey stringToKey(String s){
        byte[]decodedKey = Base64.getDecoder().decode(s);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, STRATEGY);
    }
}
