package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Estendo la classe Persona e descrivo il lavoro dell'impiegato
 *
 */

public class Impiegato extends Persona implements Lavoratori{

    private String url="jdbc:mysql://localhost/persone?user=root&password=";
    private String dataAssunzione;
    private float retribuzioneLorda;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int id;
    private Scanner scanner= new Scanner(System.in);
    private String sql;
    private ArrayList<String> listaPersone = new ArrayList<>();
    /**
     Metodo Impiegato: Oltre ai parametri inclusi dalla classe persona aggiunge
     la paga lorda e la data di assunzione.
     @author mattiacerullo
     */
    public Impiegato(String nome, String cognome, int eta, String indirizzo, String dataAssunzione, float retribuzioneLorda) {
        super(nome, cognome, eta, indirizzo);
        this.dataAssunzione = dataAssunzione;
        this.retribuzioneLorda = retribuzioneLorda;
        nuovaPersona();
        scelta();
    }

    private void scelta() {
        int scelta=0;
        while (scelta!=-1){
            menu();
            scelta=scanner.nextInt();
            switch (scelta){
                case 1: mostraPersone();
                    break;
                case 2: cancellaPersona();
                    break;
                case 3: terminazioneProgramma();
                    break;
                default:
                    System.out.println("inserisci un numero corretto!");
                    System.out.println();
            }
        }
    }
    public void terminazioneProgramma() {

        try {
            connection.close();
            statement.close();
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("Errore in fase di disconnessione" + e.getMessage());
        }
    }
    private void menu() {
        System.out.println("1) Mostra tabella persona");
        System.out.println("2) Cancella persona");
        System.out.println("3) Terminazione programma");
        System.out.println("Inserisci una scelta");
    }

    @Override
    public String descrizione() {
       return "Come lavoro faccio l'impiegato";
    }

    @Override
    public void nuovaPersona() {




        String tipo="impiegato";
        float stipendio=this.retribuzioneLorda;
        try {
        connection = DriverManager.getConnection(url);

        statement = connection.createStatement();
        String sql = "USE persone";


            statement.executeUpdate(sql);
            sql = "INSERT INTO persona VALUES (null," + "'" + getNome() + "'" + "," + "'" + getCognome() +
                    "'," + getEta() + ",'" + getIndirizzo() + "','" + tipo + "',"+stipendio+")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void mostraPersone() {
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String sql = "USE persone";
            statement.executeUpdate(sql);
            sql = "SELECT * from persona";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print("ID: " +resultSet.getInt(1));
                System.out.print(" Nome: " + resultSet.getString(2));
                System.out.print(" Cognome: " + resultSet.getString(3));
                System.out.print(" Età: " + resultSet.getInt(4));
                System.out.print(" Indirizzo: " + resultSet.getString(5));
                System.out.print(" Tipo: " +resultSet.getString(6));
                System.out.print(" Stipendio: " +resultSet.getFloat(7));
                System.out.println();
                listaPersone.add(resultSet.getString(2));
            }
            System.out.println("lista completa dei nomi delle persone contenute in tabella");
            for (int i=0; i<listaPersone.size();i++){
                System.out.print((i+1)+")"+ listaPersone.get(i) + " ");

            }
            System.out.println();
            System.out.println("------------------------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void cancellaPersona() {
        mostraPersone();
        System.out.println(" Elimina persona in base all'id: INSERISCI PER FAVORE ID ");
        id = scanner.nextInt();
        if (id != 0) {
            sql = "DELETE FROM persona WHERE id=" + id;
            System.out.println("---------------------------------------------------------------------------------");
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("impossibile cancellare l'id");
            }
        }
    }


    @Override
    public String dataAssunzione() {

        return "è stato assunto in data" + dataAssunzione;
    }

    @Override
    public String retribuzioneLorda() {

        return "guadagna " + retribuzioneLorda;
    }
}
