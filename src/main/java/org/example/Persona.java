
package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
/**
 * Classe astratta persona implementa il nome, età e indirizzo
 * Creazione classe astratta Descrizione
 * @author Mattia
 * */

public abstract class Persona {



    private String nome;
    private String cognome;
    private int eta;
    private String indirizzo;

    public abstract String descrizione();
    /**
     * Stampa solo l'indirizzo della persona con una printf()
     */
    public void stampaIndirizzo(){
        System.out.printf("L'indirizzo di %s %s è %s%n", cognome,nome,indirizzo);
    }

    /**
     * Stampa i dati della persona con una printf()
     */
    public  void stampaDati(){
        System.out.printf("%nCognome: %s%n Nome: %s%n età: %d%n Indirizzo: %s%n ", cognome,nome,eta,indirizzo);

    }

    /**
     * Inserisce i dati inseriti dal costruttore nel database
     */
    public abstract void nuovaPersona();
    /**
     Metodo mostraPersone: Visualizza tutte le persone in generale
     presenti nel database della tabella persona. Inoltre attraverso un arrayList indica tutti i nomi
     presenti in tabella.
     @author mattiacerullo
     */
    public abstract void mostraPersone();

}




