/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 * klasa koja sluzi za spremanje kontakata u imenik.
 * @author tin
 */
public class Imenik {

    private Kontakt[] kontakt = new Kontakt[5];

    public Kontakt[] getKontakt() {
        return kontakt;
    }
/**
 * postavljanje kontakata.
 * @param kon 
 */
    public void setKontakt(Kontakt kon) {
        if (this.kapacitet(kontakt) > 0) {
            for (int i = 0; i < kontakt.length; i++) {
                if (kontakt[i] == null) {
                    kontakt[i] = kon;
                    System.out.println("kontakt spremljen");
                    break;
                }
            }
        } else {
            System.out.println("Nema mjesta!");
        }
    }
/**
 * provjera kapaciteta u polju kontakata
 * @param kontakt
 * @return 
 */
    public int kapacitet(Kontakt[] kontakt) {
        int brslm = 0;
        for (int i = 0; i < kontakt.length; i++) {

            if (kontakt[i] == null) {
                brslm++;

            }
        }
        return brslm;
    }

    /**
     * Metoda koja pretrazuje kontakte u imeniku po imenu
     *
     * @param ime parametar za koji pretrazuje da li se nalazi u imeniku
     * @return Kontakt iz imenika kojem argument ime odgovara upaznom parametru
     */
    public Kontakt pretraziPoImenu(String ime) {

        Kontakt nadjeniKontakt = new Kontakt();

        for (Kontakt kontakt : kontakt) {
            if (kontakt.getIme().equals(ime)) {
                nadjeniKontakt = kontakt;
                return nadjeniKontakt;
            }
        }
        return nadjeniKontakt;
    }
    
    public void ispisImenika(){
        for (Kontakt kontakt1 : kontakt) {
            if(kontakt1 != null){
                
            
            System.out.println(kontakt1.getIme());
            }
        }
        
        
    }

}