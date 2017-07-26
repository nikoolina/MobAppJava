package Logic;


import Logic.Kontakt;
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
public class Message {
    
    private String textMessage;
    private SimKartica simKartica;
    private String isMessageRead;
    private Kontakt reciever;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
       
        this.textMessage = textMessage;
    }

    public SimKartica getSimKartica() {
        return simKartica;
    }

    public void setSimKartica(SimKartica simKartica) {
        this.simKartica = simKartica;
    }

    public String getIsMessageRead() {
        return isMessageRead;
    }

    public void setIsMessageRead(String isMessageRead) {
        this.isMessageRead = isMessageRead;
    }

    public Kontakt getReciever() {
        return reciever;
    }

    public void setReciever(Kontakt reciever) {
        this.reciever = reciever;
    }

    @Override
    public String toString() {
        return "Message{" + "textMessage=" + textMessage + ", simKartica=" + simKartica + ", isMessageRead=" + isMessageRead + ", reciever=" + reciever + ", id=" + id + '}';
    }

  
    
    
}