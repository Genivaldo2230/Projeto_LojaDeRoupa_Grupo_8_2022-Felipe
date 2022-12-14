/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public abstract class Criptografia {
    
    private static byte[] IV = new byte[16];

    public  static String encrypt(String value) {
        try {
             String key = "3lQRe1t2AgSmlQBw";
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return DatatypeConverter.printBase64Binary(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            String key = "3lQRe1t2AgSmlQBw";
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
            String texto = new String(original);
            byte[] formatado = texto.getBytes("windows-1252");
            texto = new String(formatado, "UTF-8");
            return texto;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
        public  String CriptografiaMD5(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
    }
}
