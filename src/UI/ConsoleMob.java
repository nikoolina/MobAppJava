/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Client.SimClient;
import Data.LocalDatabase.DataTransfer;
import Logic.Kontakt;
import Logic.Message;
import Logic.SimKartica;
import java.util.Scanner;

/**
 * Klasa koja sluzi za rad sa programom.
 * koristi logiku ostalih klasa.
 * @author tin
 */
public class ConsoleMob {

    /**
     * metoda koja se koristi za pokretanje aplikacije.
     * sadrzi kostur aplikacije
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
        System.out.println("Unesite serijski broj kartice ");
        String sim = sc.nextLine();
        SimKartica simKartica = new SimKartica(sim);

        System.out.println(simKartica);

        if (simKartica.getSerijskiBroj() == null) {
            System.out.println("IZASLI STE IZ PROGRAMA");
        } else {
// NAKON ODABIRA SIM KARTICE.....
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
                int number;
                String email;
                Kontakt k = new Kontakt();

                switch (num) {
                    case 1: {
                        System.out.print("Enter name: ");
                        name = sc.next();
                        System.out.print("Enter lastname : ");
                        lastname = sc.next();
                        System.out.print("Enter phone number : ");
                        number = sc.nextInt();
                        System.out.print("Enter e-mail : ");
                        email = sc.next();
                        k.setIme(name);
                        k.setPrezime(lastname);
                        k.setBroj(number);
                        k.setEmail(email);

                        if (Data.LocalDatabase.DataTransfer.insertKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), k)) {
                            System.out.println(ANSI_GREEN + "Contact " + k.getIme() + " inserted" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Contact " + k.getIme() + " NOT inserted" + ANSI_RESET);
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("Enter name : ");
                        name = sc.next();
                        if (Data.LocalDatabase.DataTransfer.deleteKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name)) {
                            System.out.println(ANSI_GREEN + "Contact : " + name + " deleted" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Contact " + name + " does not exist" + ANSI_RESET);
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("Enter name: ");
                        name = sc.next();
                        if (DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name) != null) {

                            System.out.println(DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name));

                            System.out.print("Enter new phone number : ");
                            number = sc.nextInt();
                            if (Data.LocalDatabase.DataTransfer.updateKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name, number)) {
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
                        k = DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), name);
                        if (k != null) {
                            System.out.println(k);
                        } else {
                            System.out.println("There is no contact named " + name);
                        }
                        break;
                    }
                    case 5: {
                        Data.LocalDatabase.DataTransfer.viewTable();
                        break;
                    }
                    case 6: {
                        System.out.print("Are you sure you want to delete ALL your contacts  (Y/N) :");
                        String s = sc.next();
                        switch (s) {
                            case "Y": {
                                if (DataTransfer.resetTable(Data.LocalDatabase.ConnectToDatabase.getConnection())) {
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
                           Message mm = new Message();
                        System.out.println("unesite primatelja");
                        String primatelj = sc.next();
                        k = DataTransfer.findKontakt(Data.LocalDatabase.ConnectToDatabase.getConnection(), primatelj);
                        if (k != null) {
                            mm.setReciever(k);
                           
                        } else {
                            System.out.println("kontakt ne postoji..ili se vratite na MENU i unesite novi kontakt ");
                        }
                        
                   
                        mm.setTextMessage(simClient.getMessage());
                        mm.setSimKartica(simClient.getSimKartica());
                        mm.setIsMessageRead("false");
                        if(Data.ServerDataBasePackage.DataTransferServer.insertMessage(Data.ServerDataBasePackage.ConnectToServerBase.getConnection(), mm) ){
                            
                            System.out.println(ANSI_GREEN + "message send " + ANSI_RESET);
                        }else{
                            System.out.println(ANSI_RED + "message not send" + ANSI_RESET);
                        }

                        break;
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