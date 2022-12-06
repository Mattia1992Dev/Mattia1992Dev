package org.example;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

/**
 * Classe per creazione e collegamento a Database.
 */
public class GestioneDB {

        Impiegato impiegato;
        Studente studente;



        private Properties properties;
        private FileReader fileReader;
        private String url;
        private Connection connection;
        private PreparedStatement preparedStatement;
        Statement statement;
        ResultSet resultSet;
        private Scanner scanner;
        private FileOutputStream outputStream;
        private String nome, cognome, indirizzo, tipo;
        private int eta;
        private float stipendio;

    /**
     * Crea il database persone in caso non esiste e inoltre crea la tabella persone
     */
    public void creazioneDatabase(){
            String db_Url ="jdbc:mysql://localhost:3306";
            String User = "root";
            String password = "";
            try {
                Connection con = DriverManager.getConnection(db_Url,User,password); // tre parametri
                System.out.println("Connessione con mysql riuscita");
                Statement statement = con.createStatement();
                String sql = "CREATE DATABASE IF NOT EXISTS persone";
                statement.executeUpdate(sql);
                statement.executeUpdate(sql);

                statement.executeUpdate("USE persone");
                String createTable = "CREATE TABLE IF NOT EXISTS persona" +
                        "(id INT primary key auto_increment," +
                        "nome varchar(50) not null," +
                        "cognome varchar(50) not null," +
                        "età int not null," +
                        "indirizzo varchar(50) not null," +
                        "tipo varchar(50) not null," +
                        "stipendio float) ";

                statement.executeUpdate(createTable);
        } catch (SQLException e) {
                throw new RuntimeException(e);
            }}

    /**
     * Attraverso l'utilizzo del file db.properties e attraverso la HashMap legge le properties chiave:valore
     * che permetteranno di connettersi al DB. In caso di effettiva riuscita ritornerà una Stampa con scritto che la
     * connessione è riuscita. Inoltre ritornerà il nome del DB a cui si è collegati.
     */

    public void GestioneDB()
        {


            try {

                scanner= new Scanner (System.in);
                fileReader= new FileReader("db.properties");
                properties= new Properties();
                properties.load(fileReader);

                //Inserisco le properties relative al database in una HashMap e le estraggo con ciclo foreach


                HashMap<String,String > datiConnessione = new HashMap();
                for (String key : properties.stringPropertyNames()) {
                    datiConnessione.put(key, properties.getProperty(key));
                }

                String driver =  datiConnessione.get("driver");
                String database = datiConnessione.get("database");
                String user =  datiConnessione.get("user");
                String password =  datiConnessione.get("password");


                //Mi connetto al DB passando  con metodo connetti()

                url = driver+"://localhost/"+database+"?user="+user+"&password="+password;

                connetti();





            } catch (FileNotFoundException e) {
                System.out.println("Impossibile leggere il file: " + e.getMessage());


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    private void connetti() throws SQLException {
        try {
            connection= DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Collegamento riuscito! Sei collegato al database --> " + properties.getProperty("database").toUpperCase());
        System.out.println();
    }




}








