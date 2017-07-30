/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Client.SimClient;
import Data.LocalDatabase.DataTransfer;
import Data.ServerDataBasePackage.DataTransferServer;
import Logic.Imenik;
import Logic.Poruka;
import Logic.SimKartica;
import java.util.Scanner;
/**
 * Klasa koja sluzi za rad sa programom. koristi logiku ostalih klasa.
 *
 * @author tin
 */
public class ConsoleMob {

    /**
     * metoda koja se koristi za pokretanje aplikacije. sadrzi kostur aplikacije
     *
     * @throws Exception
     */
    static void startMob() throws Exception {
        Scanner sc = new Scanner(System.in);
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";

//ODABIR SIM KARTICE    
        System.out.println(ANSI_YELLOW + "DOBRODOŠLI ---- MOBAPP ---- PC VERSION_0.1" + ANSI_RESET);
        System.out.println("Želite li aktivirati karticu ili se ulogirati ? (A/L)");
        String odabir = sc.nextLine();

        SimKartica simKartica;
        SimClient client = new SimClient();
        switch (odabir) {
            case "A": {

//                client = new SimClient();
                client.sayHi();

                break;
            }
            case "L": {
                client.posaljiPinNaServer();
                break;
            }
            default: {
                System.out.println("Nije unesena SimKartica ");
                break;
            }
        }

        String serijskiBroj = client.dohvatiSerijskiBroj();

        System.out.println("SERIJSKI BROJ ::: " + serijskiBroj);
        
        simKartica = new SimKartica();

        if (serijskiBroj.equals("0")) {
            System.out.println("IZASLI STE IZ PROGRAMA");
        } else {
// NAKON ODABIRA SIM KARTICE.....

            Thread.sleep(1000);
            int num;

            System.out.println(ANSI_GREEN + " Wellcome to new exsperience!!!" + ANSI_RESET);

            do {
                System.out.println("MENU");
                System.out.println("1 - new contact");
                System.out.println("2 - delete contact");
                System.out.println("3 - change contact number");
                System.out.println("4 - find contact");
                System.out.println("5 - view contacts");
                System.out.println("6 - reset menu");
                System.out.println("7 - send message");
                System.out.println("8 - read message");
                System.out.println("0 - exit");
                System.out.print("Select number from menu : ");

                num = sc.nextInt();

                String name;
                String lastname;
                String number;
                String email;
                Imenik k = new Imenik();

                switch (num) {
                    case 1: {
                        System.out.print("Enter name: ");
                        name = sc.next();
                        System.out.print("Enter lastname : ");
                        lastname = sc.next();
                        System.out.print("Enter phone number : ");
                        number = sc.next();
                        System.out.print("Enter e-mail : ");
                        email = sc.next();
                        k.setIme(name);
                        k.setPrezime(lastname);
                        k.setBrojTelefona(number);
                        k.setEmail(email);

                        // posprema simKarticu u Imenik
                        simKartica.setSerijskiBroj(Integer.parseInt(serijskiBroj));
                        DataTransfer.createNewTable(simKartica.getSerijskiBroj());

                        if (Data.LocalDatabase.DataTransfer.insertKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), k, simKartica.getSerijskiBroj())) {
                            System.out.println(ANSI_GREEN + "Contact " + k.getIme() + " inserted" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Contact " + k.getIme() + " NOT inserted" + ANSI_RESET);
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("Enter name : ");
                        name = sc.next();
                        if (Data.LocalDatabase.DataTransfer.deleteKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name, simKartica.getSerijskiBroj())) {
                            System.out.println(ANSI_GREEN + "Contact : " + name + " deleted" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Contact " + name + " does not exist" + ANSI_RESET);
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("Enter name: ");
                        name = sc.next();
                        if (DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name, simKartica.getSerijskiBroj()) != null) {

                            System.out.println(DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name, simKartica.getSerijskiBroj()));

                            System.out.print("Enter new phone number : ");
                            number = sc.next();
                            if (Data.LocalDatabase.DataTransfer.updateKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name, number, simKartica.getSerijskiBroj())) {
                                System.out.println(ANSI_GREEN + "Contact : " + name + " updated" + ANSI_RESET);
                            } else {
                                System.out.println(ANSI_RED + "Contact " + name + " NOT updated" + ANSI_RESET);
                            }
                        } else {
                            System.out.println("There is no contact named " + name);
                        }

                        break;

                    }
                    case 4: {
                        System.out.print("Enter name :");
                        name = sc.next();
                        k = DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name, simKartica.getSerijskiBroj());
                        if (k != null) {
                            System.out.println(k);
                        } else {
                            System.out.println("There is no contact named " + name);
                        }
                        break;
                    }
                    case 5: {
                        simKartica.setSerijskiBroj(Integer.parseInt(serijskiBroj));
                        Data.LocalDatabase.DataTransfer.viewTable(simKartica.getSerijskiBroj());
                        break;
                    }
                    case 6: {
                        System.out.print("Are you sure you want to delete ALL your contacts  (Y/N) :");
                        String s = sc.next();
                        switch (s) {
                            case "Y": {
                                if (DataTransfer.resetTable(Data.LocalDatabase.ConnectToDatabase.getConnection(), simKartica.getSerijskiBroj())) {
                                    System.out.println("All your files are deleted");

                                } else {
                                    System.out.println("Could not delete All your files ");

                                }
                                break;
                            }
                            case "N": {
                                System.out.println("All files are still here ");
                                break;
                            }
                        }
                        break;
                    }
                    case 7: {

                        SimClient simClient = new SimClient();
                        simClient.setSimKartica(simKartica);
                        simClient.run();
                        Poruka mm = new Poruka();

                        System.out.println("unesite ime primatelja");
                        String primatelj = sc.next();

                        k = DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), primatelj, simKartica.getSerijskiBroj());
                        if (k != null) {

                            //novo
                            String brojPrimatelja = k.getBrojTelefona();
                            // ovo ne moze tu ici jer server nije na istom racunalu!!
//                            SimKartica simPrimatelja = DataTransferServer.findSim(Data.ServerDataBasePackage.ConnectToServerBase.getConnection(), brojPrimatelja);
                            System.out.println(brojPrimatelja);	
                            if (brojPrimatelja != null) {
                                mm.setBrTelPrimatelj(brojPrimatelja);
                            } else {
                                System.out.println("KARTICA NIJE AKTIVIRANA");
                            }

                            //ovo je bilo//
//                            mm.setBrTelPrimatelj(k.getBrojTelefona());
                            //kraj novog
                        } else {
                            System.out.println("kontakt ne postoji..ili se vratite na MENU i unesite novi kontakt ");

                        }
                        mm.setBrTelPosiljatelj(simClient.getSimKartica().getTelefonskiBroj());
                        System.out.println("Unesite poruku ... ");

                        Scanner sc1 = new Scanner(System.in);
                        String textPoruke = sc1.nextLine();

                        mm.setTextMessage(textPoruke);
                        mm.setBrTelPrimatelj(k.getBrojTelefona());
                        mm.setPorukaProcitana(false);

//                        if (Data.ServerDataBasePackage.DataTransferServer.insertMessage(Data.ServerDataBasePackage.ConnectToServerBase.getConnection(), mm)) {
//
//                            System.out.println(ANSI_GREEN + "message send " + ANSI_RESET);
//                        } else {
//                            System.out.println(ANSI_RED + "message not send" + ANSI_RESET);
//                        }
//
//                        break;
                    }
                    case 8: {

                        break;
                    }
                }
            } while (num != 0);
            System.out.println(ANSI_GREEN + " Good bye!!!" + ANSI_RESET);
        }
    }
}