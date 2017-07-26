/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.LocalDatabase;

import Logic.Imenik;
import Logic.SimKartica;
import java.sql.Connection;
import java.sql.DriverManager;
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
     * metoda koja ubacuje sim karticu u bazu .
     *
     * @param conn
     * @param kontakt
     * @return true ako je kontakt uspjesno ubacen u bazu
     */
    public static boolean insertSim(Connection conn, SimKartica sim) {

        try {
            String sql = "Insert into Kartica(Pin, Puk, BrojTelefona) values(?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sim.getPin());
            pstm.setInt(2, sim.getPuk());
            pstm.setString(3, sim.getTelefonskiBroj());

//            if (pstm.executeUpdate() == 1) {
//                return true;
//            }
            pstm.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }

        return false;
    }

    /**
     * Kreiranje tablice Imenik za novu sim karticu
     */
    public static void createNewTable(int serijskiBrojKartice) {
        try {
            //"jdbc:mysql://localhost:3306/Mobitel?characterEncoding=UTF-8&useSSL=false"

            Statement st = ConnectToDatabase.getConnection().createStatement();
            String url = "jdbc:mysql://localhost:3306/mobitel?characterEncoding=UTF-8&useSSL=false";
            String imenik = "Imenik" + serijskiBrojKartice;
            // SQL statement for creating a new table
            String sql = "CREATE TABLE IF NOT EXISTS " + imenik + " (\n"
                    + "	ID int(11) AUTO_INCREMENT,\n"
                    + "	ime varchar(255) NOT NULL,\n"
                    + "     prezime varchar(255) NOT NULL,\n"
                    + "     brojTelefona varchar(255) NOT NULL,\n"
                    + "     email varchar(255) NOT NULL,\n"
                    + "     PRIMARY KEY  (ID),\n"
                    + "	capacity real\n"
                    + ");";
            st.execute(sql);
        } catch (SQLException ex) {
       
            Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
           
            Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

    }

    /**
     * metoda koja ubacuje kontakt u bazu .
     *
     * @param conn
     * @param kontakt
     * @return true ako je kontakt uspjesno ubacen u bazu
     */
    public static boolean insertKontakt(Connection conn, Imenik kontakt, int serijskiBrojKartice) {

        String imenik = "Imenik" + serijskiBrojKartice;
        try {
            String sql = "Insert into " + imenik + "  (Ime, Prezime, BrojTelefona, email) values(?, ?, ? ,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, kontakt.getIme());
            pstm.setString(2, kontakt.getPrezime());
            pstm.setString(3, kontakt.getBrojTelefona());
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
    public static void viewTable(int serijskiBrojKartice) {
  String imenik = "imenik" + serijskiBrojKartice;
        try {
            Statement st = ConnectToDatabase.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT ime ,prezime , brojTelefona , email FROM  " + imenik);
            while (rs.next()) {
                Imenik k = new Imenik();
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setBrojTelefona(rs.getString("brojTelefona"));
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
     *
     * @param conn
     * @return true ukoliko je sve izbrisano
     */
    public static boolean resetTable(Connection conn, int serijskiBrojKartice) {
  String imenik = "Imenik" + serijskiBrojKartice;
        try {
            String sql = "TRUNCATE  " + imenik;

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
     *
     * @param conn
     * @param ime
     * @return true ako je kontakt izbrisan iz tablice
     */
    public static boolean deleteKontakt(Connection conn, String ime, int serijskiBrojKartice) {
  String imenik = "Imenik" + serijskiBrojKartice;
        try {
            String sql = "DELETE FROM " + imenik + "  WHERE Ime=?";
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
     *
     * @param conn
     * @param userName - ime koje pretrazujemo
     * @return kontakt koji je baza pronasla ukoliko se taj kontakt nalazi u
     * tablici
     * @return false ukoliko nema tog kontakta
     */
    public static Imenik findKontakt(Connection conn, String userName, int serijskiBrojKartice) {
  String imenik = "Imenik" + serijskiBrojKartice;
        try {

            String sql = "Select Ime, Prezime ,  BrojTelefona , email FROM " + imenik + "  where Ime = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String broj = rs.getString("BrojTelefona");
                String email = rs.getString("email");
                Imenik kont = new Imenik();
                kont.setIme(ime);
                kont.setPrezime(prezime);
                kont.setBrojTelefona(broj);
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
     *
     * @param conn
     * @param ime - ime koje pretrazujemo
     * @param noviBroj - broj koji uredujemo
     * @return true ukoliko je kontakt naden i izmjenjen
     */
    public static boolean updateKontakt(Connection conn, String ime, String noviBroj , int serijskiBrojKartice) {
  String imenik = "Imenik" + serijskiBrojKartice;
        try {
            String sql = "Update " + imenik + " set BrojTelefona = ?  where Ime=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(2, ime);
            pstm.setString(1, noviBroj);

            if (pstm.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getStackTrace());
        }

        return false;

    }

}