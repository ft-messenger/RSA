package de.ft.rsa;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] argv) {

        try {
            ServerSocket serverSocket = new ServerSocket(4321);
            Socket clientSocket = serverSocket.accept();

            OutputStream socketoutstr = clientSocket.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter( socketoutstr );
            BufferedWriter bw = new BufferedWriter( osr );

            InputStream socketinstr = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader( socketinstr );
            BufferedReader br = new BufferedReader( isr );

            String anfrage;
            String antwort;

            anfrage = br.readLine();
            antwort = "Antwort auf "+anfrage;
            bw.write(antwort);
            bw.newLine();
            bw.flush();

            bw.close();
            br.close();
            clientSocket.close();
            serverSocket.close();
        }
        catch (UnknownHostException uhe) {
            System.out.println(uhe);
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }

    } // ende: main

} // Ende: public class MeinEchoServer
