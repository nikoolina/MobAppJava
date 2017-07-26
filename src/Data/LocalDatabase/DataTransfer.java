/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.LocalDatabase;

import Logic.Kontakt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja sluzi za rad sa bazom .
 * 
 * @author tin
 */
public class DataTransfer {

    /**
     *  metoda koja ubacuje kontakt u bazu .
     * 
     * @param conn
     * @param kontakt
     * @return true ako je kontakt uspjesno ubacen u bazu 
     */
    public static boolean insertKontakt(Connection conn, Kontakt kontakt) {

        try {
            String sql = "Insert into MobitelTable(Ime, Prezime, BrojTelefona, email) values(?, ?, ? ,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, kontakt.getIme());
            pstm.setString(2, kontakt.getPrezime());
            pstm.setInt(3, kontakt.getBroj());
            pstm.setString(4, kontakt.getEmail());
            if (pstm.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getStackTrace());
        }

        return false;
    }
 /**
  * metoda za pregled tablice baze podataka .
  */
    public static void viewTable() {

        try {
            Statement st = ConnectToDatabase.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT Ime ,Prezime , BrojTelefona , email FROM MobitelTable");
            while (rs.next()) {
                Kontakt k = new Kontakt();
                k.setIme(rs.getString("Ime"));
                k.setPrezime(rs.getString("Prezime"));
                k.setBroj(rs.getInt("BrojTelefona"));
                k.setEmail(rs.getString("email"));
                System.out.println(k);
            }

        } catch (SQLException e) {
            System.out.println(" sqlException | nesto nije u redu !");
            e.printStackTrace();
        } catch (TimeoutException to) {
            System.out.println("Timeout Exception | nesto nije u redu");
            to.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception | nesto nije u redu");

        }
    }
 /**
  * metoda koja resetira tablicu u bazi . Brise sve podatke iz tablice.
  * @param conn
  * @return true ukoliko je sve izbrisano
  */
    public static boolean resetTable(Connection conn) {

        try {
            String sql = "TRUNCATE MobitelTable ";

            PreparedStatement pstm = conn.prepareStatement(sql);
            if (pstm.executeUpdate() == 0) {
                return true;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getStackTrace());
        }

        return false;
    }
    /**
     * metoda koja brise kontakt iz tablice baze.
     * @param conn
     * @param ime
     * @return true ako je kontakt izbrisan iz tablice
     */
    public static boolean deleteKontakt(Connection conn, String ime) {

        try {
            String sql = "DELETE FROM MobitelTable WHERE Ime=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ime);

            if (pstm.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getStackTrace());
        }

        return false;
    }
    /**
     * metoda koja nalazi kontakt u tablici koji smo upisali.
     * @param conn
     * @param userName - ime koje pretrazujemo 
     * @return kontakt koji je baza pronasla ukoliko se taj kontakt nalazi u tablici
     * @return false ukoliko nema tog kontakta
     */
    public static Kontakt findKontakt(Connection conn, String userName) {

        try {

            String sql = "Select Ime, Prezime ,  BrojTelefona , email from MobitelTable  where Ime = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                int broj = rs.getInt("BrojTelefona");

                String email = rs.getString("email");
                Kontakt kont = new Kontakt();
                kont.setIme(ime);
                kont.setPrezime(prezime);
                kont.setBroj(broj);
                kont.setEmail(email);

                return kont;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * metoda koja ureduje kontakt putem pretrazivanja po imenu.
     * @param conn
     * @param ime - ime koje pretrazujemo
     * @param noviBroj - broj koji uredujemo
     * @return  true ukoliko je kontakt naden i izmjenjen
     */
    public static boolean updateKontakt(Connection conn, String ime, int noviBroj) {

        try {
            String sql = "Update MobitelTable set BrojTelefona = ?  where Ime=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(2, ime);
            pstm.setInt(1, noviBroj);

            if (pstm.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getStackTrace());
        }

        return false;

    }

}