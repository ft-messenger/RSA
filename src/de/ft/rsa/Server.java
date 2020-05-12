package de.ft.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;


import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
   static ServerSocket serverSocket;
   static KeyPair key = null;
   static Socket clientSocket;
   static OutputStream socketoutstr;
   static OutputStreamWriter osr;
   static DataOutputStream bw;
   static InputStream socketinstr;
static BufferedWriter bfr;
static  InputStreamReader isr;
static   BufferedReader br;
static  DataInputStream dIn;
static PublicKey publicKeyfromclient = null;

    public static void main(String[] argv) throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {


            prepareconnection(4321);

            key = utils.gen();





            transmittkey();


            resivekey();






            Timer time = new Timer();
            time.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {



                        int length = dIn.readInt();                    // read length of incoming message
                        if (length > 0) {
                            byte[] message = new byte[length];
                            dIn.readFully(message, 0, message.length); // read the message
                            System.out.println(utils.decrypt(message, key.getPrivate()));
                            System.out.println();

                        }

                    } catch (IOException e) {

                    } catch (InvalidKeyException e) {

                    } catch (NoSuchPaddingException e) {

                    } catch (NoSuchAlgorithmException e) {

                    } catch (BadPaddingException e) {

                    } catch (IllegalBlockSizeException e) {

                    }
                }
            },0,20);






    } // ende: main

    private static void sendcryptmessage(String string) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IOException {

        bw.writeInt(utils.encrypt(string,publicKeyfromclient).length);
        bw.write(utils.encrypt(string,publicKeyfromclient));
    }

    private static void resivekey() throws IOException {
        String anfrage;
        String antwort;

        anfrage = br.readLine();


        try {
            publicKeyfromclient = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec( new BigInteger(anfrage.split("&")[0]), new BigInteger(anfrage.split("&")[1])));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void transmittkey() throws IOException {


        RSAPublicKey pub = (RSAPublicKey) key.getPublic();
        String test = ""+pub.getModulus()+"&"+pub.getPublicExponent();

        bfr.write(test);
        bfr.newLine();
        bfr.flush();
    }

    private static void prepareconnection(int port) throws IOException {
         serverSocket = new ServerSocket(port);
         clientSocket = serverSocket.accept();

         socketoutstr = clientSocket.getOutputStream();
         osr = new OutputStreamWriter( socketoutstr );
         bw = new DataOutputStream(socketoutstr);

         bfr = new BufferedWriter(osr);
         socketinstr = clientSocket.getInputStream();
         isr = new InputStreamReader( socketinstr );
         br = new BufferedReader( isr );
         dIn = new DataInputStream(clientSocket.getInputStream());
    }

} // Ende: public class MeinEchoServer
