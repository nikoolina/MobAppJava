///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Data.ServerDataBasePackage;
//
//import Logic.Poruka;
//import Logic.SimKartica;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * Klasa koja sluzi za rad sa bazom putem ukljucivanja servera.
// *
// * @author tin
// */
//public class DataTransferServer {
//    
//    /**
//     * metoda koja pretražuje po broju telefona primatelja poruke da 
//     * li je kartica s tim telefonskim brojem aktivirana 
//     * tj. da li postoji korisnik kojem saljemo poruku .
//     * @param conn - konekcija na bazu podataka
//     * @param serijskiBroj - unikatna vrijednost
//     * @return SimKartica
//     */
//    public static SimKartica findSim(Connection conn, String brojTelefona) {
//
//        try {
//
//            String sql = "Select serijskiBroj, pin , puk, brojTelefona from simKartica where brojTelefona = ? ";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setString(1 , brojTelefona);
//
//            ResultSet rs = pstm.executeQuery();
//            if (rs.next()) {
//                int sb = rs.getInt("serijskiBroj");
//                int pin = rs.getInt("pin");
//                int puk = rs.getInt("puk");
//                String bt = rs.getString("brojTelefona");
//
//                try {
//                    SimKartica sim = new SimKartica();
//                    sim.setSerijskiBroj(sb);
//                    sim.setPin(pin);
//                    sim.setPuk(puk);
//                    sim.setTelefonskiBroj(bt);
//
//                    return sim;
//                } catch (Exception ex) {
//                    Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//
//    /**
//     * metoda ulazi u program ovisno o potvrdi tocnosti Serisjkog broja .
//     * @param conn - konekcija na bazu podataka
//     * @param serijskiBroj - unikatna vrijednost
//     * @return 
//     */
//    public static SimKartica findSim(Connection conn, int pin1) {
//
//        try {
//
//            String sql = "Select serijskiBroj, pin , puk, brojTelefona from simKartica where pin = ? ";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setInt(1 , pin1);
//
//            ResultSet rs = pstm.executeQuery();
//            if (rs.next()) {
//                int sb = rs.getInt("serijskiBroj");
//                int pin = rs.getInt("pin");
//                int puk = rs.getInt("puk");
//                String bt = rs.getString("brojTelefona");
//
//                try {
//                    SimKartica sim = new SimKartica();
//                    sim.setSerijskiBroj(sb);
//                    sim.setPin(pin);
//                    sim.setPuk(puk);
//                    sim.setTelefonskiBroj(bt);
//
//                    return sim;
//                } catch (Exception ex) {
//                    Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    public static boolean insertMessage(Connection conn,Poruka message){
//                try {
//                    /**
//                     * nesto nije u redu 
//                     */
//            String sql = "Insert into Poruke(Posiljatelj, TextPoruke, Primatelj, Procitano) values(?, ?, ?, ?)";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            
//            pstm.setString(1, message.getBrTelPosiljatelj());
//            pstm.setString(2, message.getTextMessage());
//            pstm.setString(3, message.getBrTelPrimatelj());
//            pstm.setBoolean(4, false);
//     
//            if (pstm.executeUpdate() == 1) {
//                return true;
//            }
//
//        } catch (Exception sqle) {
//            System.out.println(sqle.getMessage());
//        }
//return false;
//    }
//}