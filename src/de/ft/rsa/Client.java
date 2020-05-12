package de.ft.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    public Client(){

        }


    static KeyPair key = null;
    static PublicKey publicKey_fremd= null;

    public static PublicKey getKeyFremd(String antwort) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec( new BigInteger(antwort.split("&")[0]), new BigInteger(antwort.split("&")[1])));

    }
    public static void sendmyPublicKey(BufferedWriter bw) throws IOException {
        RSAPublicKey pub = (RSAPublicKey) key.getPublic();
        bw.write(""+pub.getModulus()+"&"+pub.getPublicExponent());
        bw.newLine();
        bw.flush();
    }

    public static String readMessage(DataInputStream dIn) throws IOException, NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException {
        String messageresult="-1";
        if(dIn.available()>0) {
            int length = dIn.readInt();
            if (length > 0) {
                byte[] message = new byte[length];
                dIn.readFully(message, 0, message.length); // read the message
                messageresult = utils.decrypt(message, key.getPrivate());


            }
        }

        return messageresult;
    }

    public static void sendMessage(String message,DataOutputStream bo) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IOException {
        bo.writeInt(utils.encrypt(message,publicKey_fremd).length);
        bo.write(utils.encrypt(message,publicKey_fremd));
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        key=utils.gen();
        String host = "79.231.224.114";
        Socket meinEchoSocket = new Socket(host,4321);

        OutputStream socketoutstr = meinEchoSocket.getOutputStream();
        OutputStreamWriter osr = new OutputStreamWriter( socketoutstr );
        BufferedWriter bw = new BufferedWriter( osr );
        DataOutputStream bwtest = new DataOutputStream(socketoutstr);
        InputStream socketinstr = meinEchoSocket.getInputStream();
       InputStreamReader isr = new InputStreamReader( socketinstr );
        DataInputStream dIn = new DataInputStream(meinEchoSocket.getInputStream());
        BufferedReader br = new BufferedReader( isr );


        sendmyPublicKey(bw);



        publicKey_fremd = getKeyFremd(br.readLine());


        sendMessage("d7u69r",bwtest);

Timer time = new Timer();
time.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        try {
            System.out.println(readMessage(dIn));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }
},0,1);





    }
}
