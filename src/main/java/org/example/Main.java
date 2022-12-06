package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int scelta = 0;
        String nome, cognome, indirizzo, dataAssunzione, indirizzoStudio, nomeOggetto;
        float retribuzione;
        int eta;

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1= new Scanner(System.in);
        GestioneDB gestioneDB = new GestioneDB();
        gestioneDB.creazioneDatabase(); //creazione database e tabella se non esiste
        gestioneDB.GestioneDB(); // verifica se possibile connettersi al database estraendo i dati dal file  db.properties
        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("Scegli che oggetto vuoi creare  ----  1) Impiegato ------- 2) Studente");
        scelta = scanner.nextInt();

        if (scelta == 1) {
            System.out.println("Inserisci il nome");
            nome = scanner1.nextLine();
            System.out.println("Inserisci il cognome");
            cognome = scanner1.nextLine();
            System.out.println("Introduci l'età");
            eta = scanner.nextInt();
            System.out.println("Introduci indirizzo");
            indirizzo = scanner1.nextLine();
            System.out.println("Data assunzione");
            dataAssunzione = scanner1.nextLine();
            System.out.println("Retribuzione");
            retribuzione = scanner.nextFloat();
            Persona persona = new Impiegato(nome, cognome, eta, indirizzo, dataAssunzione, retribuzione);

        }
        else if (scelta==2){
            System.out.println("Inserisci il nome");
            nome = scanner1.nextLine();
            System.out.println("Inserisci il cognome");
            cognome = scanner1.nextLine();
            System.out.println("Introduci l'età");
            eta = scanner.nextInt();
            System.out.println("Introduci indirizzo");
            indirizzo = scanner1.nextLine();
            System.out.println("Percorso studi:");
            indirizzoStudio=scanner1.nextLine();
            Persona studente= new Studente(nome,cognome,eta,indirizzo,indirizzoStudio);
        }
        else{
            System.out.println("Scelta sbagliata terminazione programma");

        }


    }
}













