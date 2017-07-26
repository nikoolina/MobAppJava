/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 * Klasa koja sadrzi podatke o kontaktu.
 * @author tin
 */
public class Imenik {
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }


    
    @Override
    public String toString() {
        return "Ime : " + this.ime + ", Prezime : " + prezime + "\n broj telefona : " + this.getBrojTelefona() + "\n e-mail : " + this.getEmail() + "\n";
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }
    
}
 