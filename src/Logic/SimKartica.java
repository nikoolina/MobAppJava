/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.ServerDataBasePackage.ConnectToServerBase;
import Data.ServerDataBasePackage.DataTransferServer;
import java.io.Serializable;
import java.sql.Connection;

/**
 *
 * @author tin
 */
public class SimKartica implements Serializable
{

    private String telefonskiBroj;
    private String serijskiBroj;
    private String pin;
    private String puk;

    public SimKartica() {

    }

    
    /**
     * konstruktor koji kreira objekt sim kartice ako postoji u bazi
     * @param serijskiBroj - unikatna vrijednost
     * @throws Exception 
     */
    public SimKartica(String serijskiBroj) throws Exception {
        
        Connection conn = ConnectToServerBase.getConnection();
        
        SimKartica userSim = DataTransferServer.findSim(conn, serijskiBroj);
        
        if(userSim != null) {
           this.pin = userSim.pin;
           this.puk = userSim.puk;
           this.telefonskiBroj = userSim.telefonskiBroj;
           this.serijskiBroj = userSim.serijskiBroj;
        } 
        else {
            System.out.println("SERIJSKI BROJ NE POSTOJI");
            
        }
        
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPuk() {
        return puk;
    }

    public void setPuk(String puk) {
        this.puk = puk;
    }

    public void setTelefonskiBroj(String telefonskiBroj) {
        this.telefonskiBroj = telefonskiBroj;
    }

    public void setSerijskiBroj(String serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }

    public String getTelefonskiBroj() {
        return telefonskiBroj;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }
 /**
  * metoda za provjeru tocnosti pina
  * @param pin
  * @return true ukoliko je pin tocan
  */
    public boolean provjeriPin(String pin) {
        if (this.pin == pin) {
            System.out.println("pin je tocan");
            return true;
        } else {
            System.out.println("pin nije tocan");
            return false;
        }
    }
 /**
  * metoda za promjenu pina
  * @param noviPin 
  * @param stariPin - provjera starog pina 
  * @return 
  */
    public boolean promjenaPina(String noviPin, String stariPin) {
        if (stariPin.equals(this.pin)) {
            pin = noviPin;
            System.out.println("pin promijenjen , novi pin : " + noviPin);
            return true;
        } else {
            System.out.println("pin nije promijenjen");
            return false;
        }
    }
    /**
     * metoda za provjeru tocnosti puka
     * @param puk
     * @return 
     */
    public boolean provjeriPuk(String puk) {
        if (this.puk.equals(puk)) {
            System.out.println("puk je tocan");
            return true;
        } else {
            System.out.println("puk nije tocan");
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
    public boolean resetPina(String noviPin, String puk) {
        if (puk.equals(this.puk) && pin.equals(this.pin)) {

            this.pin = noviPin;
            System.out.println("puk i pin su valjani ,pin je vracen na tvornicku postavku :" + noviPin);

            return true;
        } else {
            System.out.println("puk ili pin nisu valjani, pin nije vracen na staro ");
        }
        return false;
    }

    @Override
    public String toString() {
        return "SimKartica{" + "telefonskiBroj=" + telefonskiBroj + ", serijskiBroj=" + serijskiBroj + ", pin=" + pin + ", puk=" + puk + '}';
    }
    


}