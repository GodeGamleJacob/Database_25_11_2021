package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DBSQL {
    //Man opretter en instans af klassen Connection, og den bruger man så senere til at oprette forbindelse til databasen
    //Man opretter en instans af klassen Statement, og den bruger man så til at
    private Connection connection;
    private Statement statement;
    //Forbindelsesstrengen, der angiver databasens adresse på computeren.
    private final String URL = "jdbc:sqlite:DBSQL_25_11_2021.sqlite";

    DBSQL() {
        connection = null;
        statement = null;
        try {
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(true);
            if (connection != null) {
                System.out.println("Der er forbindelse til DBSQL");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretStuderendeTabel() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS studerende (\n"
                    + "stdnr integer PRIMARY KEY, \n"
                    + "fornavn TEXT NOT NULL, \n"
                    + "efternavn TEXT NOT NULL, \n"
                    + "adresse TEXT NOT NULL, \n"
                    + "postnummer TEXT NOT NULL, \n"
                    + "mobilnummer TEXT NOT NULL, \n"
                    + "klasse TEXT NOT NULL \n"
                    + ");";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Connection to opretStuderendeTabel has been established.");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretFagTabel() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS fag (\n"
                    + "fagnr integer NOT NULL, \n"
                    + "fagnavn TEXT NOT NULL \n"
                    + ");";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Connection to opetFagTabel has been established.");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretStudFagTabel() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS studfag (\n"
                    + "stdnr integer NOT NULL, \n"
                    + "fagnr integer NOT NULL, \n"
                    + "PRIMARY KEY (fagnr, stdnr),"
                    + "FOREIGN KEY (stdnr) REFERENCES studerende(stdnr), "
                    + "FOREIGN KEY (fagnr) REFERENCES fag(fagnr)"
                    + ");";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Connection to opretStudFagTabel has been established.");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void indsaetStuderende(Studerende std) {
        String sql = "INSERT INTO studerende (stdnr, fornavn, efternavn, adresse, postnummer, mobilnummer, klasse) VALUES ("
                + std.getStdnr() +
                ", '" + std.getFornavn() +
                "', '" + std.getEfternavn() +
                "', '" + std.getAdresse() +
                "', '" + std.getPostnummer() +
                "', '" + std.getMobilnummer() +
                "', '" + std.getKlasse() +
                "');";
        try {

            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            statement.execute(sql);
            connection.setAutoCommit(true);
            if (connection != null) {
                System.out.println("insætStuderende har stadig forbindelse");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void indsaetFag(Fag f) {
        String sql = "INSERT INTO fag (fagnr, fagnavn) VALUES ("
                + f.getFagnr()
                + ", '" + f.getFagnavn()
                + "');";
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            statement.execute(sql);
            connection.setAutoCommit(true);
            if (connection != null) {
                System.out.println("indsætFag har stadig forbindelse");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opdaterstudklasse(int stdnr, String nyklasse) {
        String sql = "UPDATE studerende SET klasse = '" + nyklasse + "' WHERE stdnr = " + stdnr + ";";
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            statement.execute(sql);
            connection.setAutoCommit(true);
            if (connection != null) {
                System.out.println("opdaterstudklasse har stadig forbindelse");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Studerende soegstuderende(int stdnr) {
        Studerende studerende = new Studerende();
        try {
            String sql = "SELECT * FROM studerende WHERE stdnr = " + stdnr;
            Statement Statement = connection.createStatement();
            ResultSet result = Statement.executeQuery(sql);
            while (result.next()) {
                studerende.setStdnr(result.getInt(1));
                studerende.setFornavn(result.getString(2));
                studerende.setEfternavn(result.getString(3));
                studerende.setAdresse(result.getString(4));
                studerende.setPostnummer(result.getString(5));
                studerende.setMobilnummer(result.getString(6));
                studerende.setKlasse(result.getString(7));

                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println(result.getString(5));
                System.out.println(result.getString(6));
                System.out.println(result.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studerende;
    }

    public ArrayList<Studerende> soegEfterEfternavn(String enavn) {
        ArrayList<Studerende> tabel = new ArrayList<>();
        String sql = "SELECT * from studerende WHERE efternavn='" + enavn + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Studerende s = new Studerende();
            while (rs.next()) {
                s.setStdnr(rs.getInt("stdnr"));
                s.setEfternavn(rs.getString("efternavn"));
                tabel.add(s);
            }
            System.out.println("søgEfternavn virker.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tabel;
    }

    public ArrayList<Studerende> soegAlleStuderende() {
        ArrayList<Studerende> alleStuderende = new ArrayList<>();
        String sql = "SELECT * FROM studerende INNER JOIN studfag ON studerende.stdnr = studfag.stdnr INNER JOIN Fag ON FAG.fagnr=Studfag.fagnr";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            int i=0;
            while (rs.next()) {
                Studerende s = new Studerende();
                s.setStdnr(rs.getInt("stdnr"));
                s.setFornavn(rs.getString("fornavn"));
                s.setEfternavn(rs.getString("efternavn"));
                s.setAdresse(rs.getString("adresse"));
                s.setPostnummer(rs.getString("postnummer"));
                s.setMobilnummer(rs.getString("mobilnummer"));
                s.setKlasse(rs.getString("klasse"));
                alleStuderende.add(s);
                i++;
            }

            System.out.println("\nsøgAlleStuderende virker sgu.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alleStuderende;
    }
    //OPGAVE 1 fra opgavearket d. 3/12/21.
    public ArrayList<Studfag> soegStudFag() {
        ArrayList<Studfag> alleStuderende = new ArrayList<>();
        String sql = "SELECT * FROM studerende INNER JOIN studfag ON studerende.stdnr = studfag.stdnr INNER JOIN Fag ON Fag.fagnr=Studfag.fagnr";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Studfag sf = new Studfag();
                sf.getStud().setStdnr(rs.getInt("stdnr"));
                sf.getStud().setFornavn(rs.getString("fornavn"));
                sf.getStud().setEfternavn(rs.getString("efternavn"));
                sf.getStud().setAdresse(rs.getString("adresse"));
                sf.getStud().setPostnummer(rs.getString("postnummer"));
                sf.getStud().setMobilnummer(rs.getString("mobilnummer"));
                sf.getStud().setKlasse(rs.getString("klasse"));
                sf.getFag().setFagnr(rs.getInt("fagnr"));
                sf.getFag().setFagnavn(rs.getString("fagnavn"));
                alleStuderende.add(sf);
            }
            System.out.println("søgStudFag virker for pokker.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alleStuderende;
    }
    //Opgave 2 fra opgavearket fra d. 3/12/21.
    public Studerende soegAltStdnr(int stdnr) {
        Studerende studerende = new Studerende();
        String sql = "SELECT * FROM studerende LEFT JOIN studfag ON studerende.stdnr = studfag.stdnr LEFT JOIN fag on studfag.fagnr = fag.fagnr WHERE Studerende.stdnr = " +stdnr;
        try {
            Statement Statement = connection.createStatement();
            ResultSet result = Statement.executeQuery(sql);
            while (result.next()) {
                studerende.setStdnr(result.getInt(1));
                studerende.setFornavn(result.getString(2));
                studerende.setEfternavn(result.getString(3));
                studerende.setAdresse(result.getString(4));
                studerende.setPostnummer(result.getString(5));
                studerende.setMobilnummer(result.getString(6));
                studerende.setKlasse(result.getString(7));
                Fag f = new Fag(result.getInt("fagnr"),result.getString("fagnavn"));
                studerende.fagListe.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

        /*
        System.out.println(result.getString(1));
        System.out.println(result.getString(2));
        System.out.println(result.getString(3));
        System.out.println(result.getString(4));
        System.out.println(result.getString(5));
        System.out.println(result.getString(6));
        System.out.println(result.getString(7));
        System.out.println(result.getInt("fagnr"));
        System.out.println(result.getString("fagnavn"));
         */

        System.out.println("soegAltStdnr virker skidsme.");
        return studerende;
    }

    //Opgave 3 fra opgavearket d. 3/12/21.
    public void indsaetFagtilStuderende (int stdnr, int fagnr){
        String sql = "INSERT INTO studfag (stdnr, fagnr) VALUES ("+stdnr+","+fagnr+")";
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
