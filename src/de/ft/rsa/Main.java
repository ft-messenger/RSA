package de.ft.rsa;

import javax.crypto.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class Main {
    static KeyPair key = null;
    KeySpec keySpec;
    static PublicKey publicKey;




    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {


        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec( new BigInteger("10910853303221765486890206096462437523034786405640349999702259107045652321478800202054466683468758145555104815413076674318387553951639597769938150328850093"), new BigInteger("65537")));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


        gen();
        byte[] bytetest = new String("test").getBytes();
        byte[] enc = encrypt("bytetest", publicKey);
        System.out.println("publickey: "+key.getPublic());
        System.out.println(new String(enc));
    }

    public static void gen() throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(1024);
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
