package de.ft.rsa;

import javax.crypto.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;

public class Main {
    static KeyPair key = null;
    KeySpec keySpec;
    static PublicKey publicKey;




    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {





       key = utils.gen();
        byte[] bytetest = new String("test").getBytes();
        byte[] enc = utils.encrypt("bytetest", publicKey);
        System.out.println("publickey: "+key.getPublic());
        System.out.println(new String(enc));
    }

}
