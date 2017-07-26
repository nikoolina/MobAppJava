/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.ServerDataBasePackage;

import Logic.Message;
import Logic.SimKartica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja sluzi za rad sa bazom putem ukljucivanja servera.
 *
 * @author tin
 */
public class DataTransferServer {

    /**
     * metoda ulazi u program ovisno o potvrdi tocnosti Serisjkog broja .
     * @param conn - konekcija na bazu podataka
     * @param serijskiBroj - unikatna vrijednost
     * @return 
     */
    public static SimKartica findSim(Connection conn, String serijskiBroj) {

        try {

            String sql = "Select SerijskiBroj, Pin ,  Puk, BrojTelefona from SimData  where SerijskiBroj = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, serijskiBroj);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String sb = rs.getString("SerijskiBroj");
                String pin = rs.getString("Pin");
                String puk = rs.getString("Puk");
                String bt = rs.getString("BrojTelefona");

                try {
                    SimKartica sim = new SimKartica();
                    sim.setSerijskiBroj(sb);
                    sim.setPin(pin);
                    sim.setPuk(puk);
                    sim.setTelefonskiBroj(bt);

                    return sim;
                } catch (Exception ex) {
                    Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static boolean insertMessage(Connection conn,Message message){
                try {
                    /**
                     * nesto nije u redu sa ovom varijablom
                     */
            String sql = "Insert into MessageData(text, Sender, readMessage, reciever ) values(?,?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            
            pstm.setString(1, message.getTextMessage());
            pstm.setString(2, message.getSimKartica().getTelefonskiBroj() );
                    
            pstm.setString(3, message.getIsMessageRead());
            pstm.setString(4, message.getReciever().getIme());
//            pstm.setInt(5, message.getId());
           
            
            if (pstm.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception sqle) {
            System.out.println(sqle.getMessage());
        }
return false;
    }
}