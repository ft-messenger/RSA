package de.ft.rsa;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Client {
    public static void main(String[] args) {
        //Generate Key
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        Key alicePublicKey = kp.getPublic();
        Key alicePrivateKey = kp.getPrivate();

        PrintWriter cOut = null;
        System.out.println("This is Bob. ");
        PrintStream pr = null;
        Socket sock = null;
        byte[] dectyptedText = null;
        byte[] publicKeyBytes = alicePublicKey.getEncoded();

        try{
            sock = new Socket("79.231.224.114", 4321);
            pr = new PrintStream(sock.getOutputStream());
            System.out.println(alicePublicKey);
            pr.println(publicKeyBytes);
            pr.flush();

        }catch(Exception ex){

        }


  /*  try{
        InputStream stream = sock.getInputStream();
        byte[] data = new byte[10];
        int count = stream.read(data);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, alicePrivateKey);
        dectyptedText = cipher.doFinal(data);
        System.out.println("Decrypted" + data);
    }catch(Exception ex){
        System.out.println("Error Output");
    }
    */
    }
}
