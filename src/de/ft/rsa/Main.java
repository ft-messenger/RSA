package de.ft.rsa;

import javax.crypto.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

public class Main {
    static KeyPair key = null;
    String test = "120602326452364709574000957964748712277588178839476482137276385885014833755141950658419194263656396308223495760384220494598238797316315329889956777256049898117158219884240724907149943911534886528243874068377637312852927395775343999313410348709657311711249014142183910123881197901446640874572501232626909281127";
    byte[] keycode = test.getBytes();
    KeyFactory keyFactory;
    KeySpec keySpec= new PKCS8EncodedKeySpec(keycode);
    PublicKey publicKey;

    {
        try {
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        gen();



        byte[] enc = encrypt("a55b3y", key.getPublic());
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
