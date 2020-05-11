package de.ft.rsa;

import javax.crypto.*;
import java.security.*;
import java.security.spec.KeySpec;

public class Main {
    static KeyPair key = null;


    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        gen();
        byte[] enc = encrypt("Hallo Tim", key.getPublic());
        System.out.println("publickey: "+key.getPublic());
        System.out.println(new String(enc));
    }

    public static void gen() throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(512);
        key = keygen.generateKeyPair();



    }

    public static byte[] encrypt(String message, PublicKey pk) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pk);
        byte[] chiffrat = cipher.doFinal(message.getBytes());
        return chiffrat;
    }

    public static String decrypt(byte[] chiffrat, PrivateKey sk) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        byte[] dec = null;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,sk);
        dec = cipher.doFinal(chiffrat);
        return new String(dec);
    }
}
