/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Logic.SimKartica;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//
/**
 * Klasa koja sadrži informacije za "unikatnu" sim karticu.
 * Metodom run() omogucava spajenje na server.
 * @author tin
 */
public class SimClient {

    private SimKartica simKartica;

    public SimKartica getSimKartica() {
        return simKartica;
    }

    public void setSimKartica(SimKartica simKartica) {
        this.simKartica = simKartica;
    }
    //hostname ovisi o IP adresi 
    static String hostname = "127.0.1.1";
    static int port = 8898;

    DataOutputStream dataOut;
    private Socket socket;
    private String message;
    
    /**
     * u slucaju da ova metoda zatreba !!!
     * @return 
     */
//    public String setMessage(String message){
//        return message;
//    }

    public String getMessage() {
        return message;
    }

//        public Sender(DataOutputStream dataOut) {
//            this.dataOut = dataOut;
//        }
    /**
     * Omogucava spajanje na TCPServer
     * Socket je krajnja tocka koja sluzi za komunikaciju izmmedu dvije masine
     * 
     * @throws Exception 
     */
    public void run() throws Exception {
//        String a;
        Socket SOCK = new Socket(hostname,port);
//        do {
            PrintStream PS = new PrintStream(SOCK.getOutputStream());

            InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
            BufferedReader BR = new BufferedReader(IR);
            String m = BR.readLine();
            this.message = m;
            System.out.println(m);
    }
    }









//            Scanner sc = new Scanner(System.in);
//            a = sc.nextLine();
//            if (a.equals("kraj")) {
//                break;
//            }
//            PS.println(a);
//        } while (true);
//   
//
//    public void readMessage(SimKartica simKartica, String message) {
//    }
//    private SocketClient socketClient;
//
//    public void openSocketSimClient(SimKartica sim) {
//        try {
//            System.out.println("openSocketClient ---- TU SAM");
//            this.simKartica = sim;
//            this.socketClient = new SocketClient();
//            socketClient.run();
//       
//        } catch (Exception ex) {
//            Logger.getLogger(SimClient.class.getName()).log(Level.SEVERE, null, ex);
//        }        