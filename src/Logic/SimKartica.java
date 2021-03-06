/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.LocalDatabase.ConnectToDatabase;
import Data.ServerDataBasePackage.*;
import java.io.Serializable;
import java.sql.Connection;

/**
 *
 * @author tin
 */
public class SimKartica implements Serializable
{

    private String brojTelefona;
    private int serijskiBroj;
    private int pin;
    private int puk;

    public SimKartica() {

    }

    
    /**
     * konstruktor koji kreira objekt sim kartice ako postoji u bazi
     * @param serijskiBroj - unikatna vrijednost
     * @throws Exception 
     */
    public SimKartica(int pin) throws Exception {
        
        Connection conn = ConnectToDatabase.getConnection();
        
        SimKartica userSim = DataTransferServer.findSim(conn, pin);
        
        if(userSim != null) {
           this.pin = userSim.pin;
           this.puk = userSim.puk;
           this.brojTelefona = userSim.brojTelefona;
           this.serijskiBroj = userSim.serijskiBroj;
        } 
        else {
            System.out.println("Serial number does not exist");
            
        }
        
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPuk() {
        return puk;
    }

    public void setPuk(int puk) {
        this.puk = puk;
    }

    public void setTelefonskiBroj(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setSerijskiBroj(int serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }

    public String getTelefonskiBroj() {
        return brojTelefona;
    }

    public int getSerijskiBroj() {
        return serijskiBroj;
    }
 /**
  * metoda za provjeru tocnosti pina
  * @param pin
  * @return true ukoliko je pin tocan
  */
    public boolean provjeriPin(int pin) {
        if (this.pin == pin) {
            System.out.println("pin is ok");
            return true;
        } else {
            System.out.println("pin is not ok");
            return false;
        }
    }
 /**
  * metoda za promjenu pina
  * @param noviPin 
  * @param stariPin - provjera starog pina 
  * @return 
  */
    public boolean promjenaPina(int noviPin, int stariPin) {
        if (stariPin == this.pin) {
            pin = noviPin;
            System.out.println("pin changed , new pin : " + noviPin);
            return true;
        } else {
            System.out.println("pin was not changed");
            return false;
        }
    }
    /**
     * metoda za provjeru tocnosti puka
     * @param puk
     * @return 
     */
    public boolean provjeriPuk(int puk) {
        if (this.puk == puk) {
            System.out.println("puk is ok");
            return true;
        } else {
            System.out.println("puk is not ok");
            return false;
        }
    }
 /**
  * metoda za reset PINa.
  * ukoliko su novi postavljeni pin i puk valjani ,Pin ce se vratiti na tvornicke postavke.
  * @param noviPin
  * @param puk
  * @return 
  */
    public boolean resetPina(int noviPin, int puk) {
        if (puk == this.puk && pin == this.pin) {

            this.pin = noviPin;
            System.out.println("puk & pin are ok ,pin is returned to the factory setting :" + noviPin);

            return true;
        } else {
            System.out.println("puk or pin are not good ");
        }
        return false;
    }

    @Override
    public String toString() {
        return "SimKartica{" + "telefonskiBroj=" + brojTelefona + ", serijskiBroj=" + serijskiBroj + ", pin=" + pin + ", puk=" + puk + '}';
    }
    


}