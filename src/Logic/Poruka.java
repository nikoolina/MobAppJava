package Logic;


import Logic.Imenik;
import Logic.SimKartica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Klasa koja sadrzi Tekstualne Poruke i njene podatke o primljenim i poslanim porukama.
 * @author tin
 */
public class Poruka {
    
    private String textMessage;
    private String brTelPrimatelj;
    private String brTelPosiljatelj;
    private boolean porukaProcitana;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getBrTelPrimatelj() {
        return brTelPrimatelj;
    }

    public void setBrTelPrimatelj(String brTelPrimatelj) {
        this.brTelPrimatelj = brTelPrimatelj;
    }

    public String getBrTelPosiljatelj() {
        return brTelPosiljatelj;
    }

    public void setBrTelPosiljatelj(String brTelPosiljatelj) {
        this.brTelPosiljatelj = brTelPosiljatelj;
    }

    public boolean isPorukaProcitana() {
        return porukaProcitana;
    }

    public void setPorukaProcitana(boolean porukaProcitana) {
        this.porukaProcitana = porukaProcitana;
    }

    @Override
    public String toString() {
        return "Poruka{" + "textMessage=" + textMessage + ", brTelPrimatelj=" + brTelPrimatelj + ", brTelPosiljatelj=" + brTelPosiljatelj + ", porukaProcitana=" + porukaProcitana + '}';
    }
    
    

    
}