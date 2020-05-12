package de.ft.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class utils {


    public static KeyPair gen() throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(1024);
        KeyPair key = keygen.generateKeyPair();

        return key;


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
