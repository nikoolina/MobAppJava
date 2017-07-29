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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//

/**
 * Klasa koja sadrži informacije za "unikatnu" sim karticu. Metodom run()
 * omogucava spajenje na server.
 *
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

    static String hostname = "127.0.1.1";
    static int port = 8896;

    DataOutputStream dataOut;
    private Socket socket;
    private String message;

    /**
     * u slucaju da ova metoda zatreba !!!
     *
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
     * Omogucava spajanje na TCPServer Socket je krajnja tocka koja sluzi za
     * komunikaciju izmmedu dvije masine
     *
     * @throws Exception
     */
    public void run() throws Exception {
//        String a;
        Socket SOCK = new Socket(hostname, port);

//        do {
        PrintStream PS = new PrintStream(SOCK.getOutputStream());

        InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);
        String m = BR.readLine();
        this.message = m;
        System.out.println("ovo je this.message" + m);
    }

    private Socket incoming;
    private InputStream ins;
    private OutputStream ous;

    public void sayHi() throws IOException, InterruptedException {

        SimKartica sim;

        try {
            incoming = new Socket("127.0.1.1", 8896);

            ins = incoming.getInputStream();
            ous = incoming.getOutputStream();

            new Thread(new Runnable() {

                public void run() {
                    Scanner s = new Scanner(ins);
                    while (s.hasNextLine()) {
                        System.out.println(s.nextLine());
                    }
                }
            }).start();

            //dio za pisanje na konzolu
            PrintWriter pw = new PrintWriter(ous);
            Scanner sc = new Scanner(System.in);

            String brTelefona;
            String s = "";
            do {
//                brojTelefona = konzola.nextLine();

                Thread.sleep(200);
                System.out.println("Unesite PIN: ");
                int pin = sc.nextInt();
                System.out.println("Unesite PUK: ");
                int puk = sc.nextInt();
                System.out.println("Unesite broj telefona: ");
                brTelefona = sc.next();

                System.out.println("Aktivacija ( D )");
                s = sc.next();
                switch (s) {
                    case "D": {
                        sim = new SimKartica();
                        sim.setPin(pin);
                        sim.setPuk(puk);
                        sim.setTelefonskiBroj(brTelefona);
                        System.out.println("SimClient: " + sim);
                        pw.println(pin + " " + puk + " " + brTelefona);
                        pw.flush();
                        break;
                    }
                    default: {
                        System.out.println("kartica nije aktivirana !");
                        break;
                    }

                }
                break;
            } while (true);

        } catch (Exception e) {
            e.getMessage();
        }

//        return sim = new SimKartica();
    }

    public void posaljiPinNaServer() throws IOException, InterruptedException {

        SimKartica sim;

        try {
            incoming = new Socket("127.0.1.1", 8896);

            ins = incoming.getInputStream();
            ous = incoming.getOutputStream();

            new Thread(new Runnable() {

                public void run() {
                    Scanner s = new Scanner(ins);
                    while (s.hasNextLine()) {
                        System.out.println(s.nextLine());
                    }
                }
            }).start();

            //dio za pisanje na konzolu
            PrintWriter pw = new PrintWriter(ous);
            Scanner sc = new Scanner(System.in);

            String brTelefona;
            String s = "";
            do {
//                brojTelefona = konzola.nextLine();

                Thread.sleep(200);
                System.out.println("Unesite PIN: ");
                int pin = sc.nextInt();

//                s = sc.next();

                sim = new SimKartica();
                sim.setPin(pin);
                System.out.println("Logging ...");
                pw.println(pin);
                pw.flush();
                break;

            } while (true);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public String dohvatiSerijskiBroj() throws IOException, InterruptedException {

        PrintWriter pw= new PrintWriter(ous);

        Scanner sc = new Scanner(incoming.getInputStream());
        String serBr = sc.nextLine();
//        System.out.println("SERIJSKI BROJ ::: " + serBr);
       
        return serBr.substring(serBr.lastIndexOf(" ") + 1);
    }
}

//            Scanner incoming = new Scanner(System.in);
//            a = incoming.nextLine();
//            if (a.equals("kraj")) {
//                break;
//            }
//            PS.println(a);
//        } while (true);
//   
//
//    public void readMessage(SimKartica sim, String message) {
//    }
//    private SocketClient socketClient;
//
//    public void openSocketSimClient(SimKartica sim) {
//        try {
//            System.out.println("openSocketClient ---- TU SAM");
//            this.sim = sim;
//            this.socketClient = new SocketClient();
//            socketClient.run();
//       
//        } catch (Exception ex) {
//            Logger.getLogger(SimClient.class.getName()).log(Level.SEVERE, null, ex);
//        }   