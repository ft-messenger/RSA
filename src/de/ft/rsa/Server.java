package de.ft.rsa;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class Server {

    public static void main(String[] args) {
        System.out.println("This is Alice. ");
        Key PubKey = null;
        Socket sock = null;
        PrintStream pr = null;
        Cipher cipher = null;
        try {
            ServerSocket ser = new ServerSocket(4321);
            sock = ser.accept();
            InputStream inputStream = sock.getInputStream();

            // read from the stream
            InputStream stream = sock.getInputStream();
            byte[] data = new byte[2048];
            int count = stream.read(data);


            X509EncodedKeySpec ks = new X509EncodedKeySpec(data);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PubKey = kf.generatePublic(ks);

            String test = "Hello Felix";
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, PubKey);

            //send the encrypted text to the client
            //pr.println(cipher);

        } catch (Exception ex) {
        }






        // Get Public key From Client

    }
}
