package de.ft.rsa;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "79.231.224.114";
        Socket meinEchoSocket = new Socket(host,4321);

        OutputStream socketoutstr = meinEchoSocket.getOutputStream();
        OutputStreamWriter osr = new OutputStreamWriter( socketoutstr );
        BufferedWriter bw = new BufferedWriter( osr );

        InputStream socketinstr = meinEchoSocket.getInputStream();
        InputStreamReader isr = new InputStreamReader( socketinstr );
        BufferedReader br = new BufferedReader( isr );

        String anfrage = "Hallo Tim";
        String antwort;

        bw.write(anfrage);
        bw.newLine();
        bw.flush();
        antwort = br.readLine();

        System.out.println("Host = "+host);
        System.out.println("Echo = "+antwort);

        bw.close();
        br.close();
        meinEchoSocket.close();
    }
}
