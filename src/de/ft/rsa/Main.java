package de.ft.rsa;

import javax.crypto.*;
import java.math.BigInteger;
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

        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec( new BigInteger("10910853303221765486890206096462437523034786405640349999702259107045652321478800202054466683468758145555104815413076674318387553951639597769938150328850093"), new BigInteger("65537")));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


       key = utils.gen();
        byte[] bytetest = new String("test").getBytes();
        byte[] enc = utils.encrypt("bytetest", publicKey);
        System.out.println("publickey: "+key.getPublic());
        System.out.println(new String(enc));
    }

}
