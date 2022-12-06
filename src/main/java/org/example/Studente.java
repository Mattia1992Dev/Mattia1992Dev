package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Classe studente. Estendo la classe Persona e inoltre aggiungo l'indirizzo di studio dello studente.
 @author mattiacerullo
 */
public class Studente extends Persona{

    private Connection connection;
    private Statement statement;
    private String indirizzoStudio;
    private ResultSet resultSet;
    private int id;
    private String url="jdbc:mysql://localhost/persone?user=root&password=";
    private String sql;
    private Scanner scanner= new Scanner(System.in);
    private ArrayList<String> listaPersone = new ArrayList<>();

    /**
     Metodo Studente: Oltre ai parametri inclusi dalla classe persona aggiunge
     l'indirizzo di studio.
     @author mattiacerullo
     */
    public Studente(String nome, String cognome, int eta, String indirizzo, String indirizzoStudio) {
        super(nome, cognome, eta, indirizzo);
        this.indirizzoStudio = indirizzoStudio;
        nuovaPersona();
        scelta();
    }

    /**
     * Dopo la creazione dell oggetto posso effettuare alcune scelte
     */
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

    private void menu() {
        System.out.println("1) Mostra tabella persona");
        System.out.println("2) Cancella persona");
        System.out.println("3) Terminazione programma");
        System.out.println("Inserisci una scelta");
    }


    @Override
    public String descrizione() {
        return "Sono uno studente";
    }

    @Override
    public void nuovaPersona() {



        String tipo="studente: "+ indirizzoStudio ;
        try {
            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();
            String sql = "USE persone";


            statement.executeUpdate(sql);
            sql = "INSERT INTO persona VALUES (null," + "'" + getNome() + "'" + "," + "'" + getCognome() +
                    "'," + getEta() + ",'" + getIndirizzo() + "','" + tipo + "',null)";
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
                System.out.println(" Tipo: " +resultSet.getString(6));
                System.out.println(" Stipendio: " +resultSet.getFloat(7));
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

    /**
     * Chiude le connessioni
     */
    public void terminazioneProgramma() {

        try {
            connection.close();
            statement.close();
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("Errore in fase di disconnessione" + e.getMessage());
        }
    }

}
