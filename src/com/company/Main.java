package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DBSQL dbsql = new DBSQL();
        dbsql.opretStuderendeTabel();
        dbsql.opretFagTabel();
        dbsql.opretStudFagTabel();
        Studerende std1 = new Studerende(1, "Allan", "Touring", "CPU Road", "4242", "12345678", "Dat1");
        dbsql.indsaetStuderende(std1);
        Studerende std2 = new Studerende(2, "Steve", "Wozniak", "MAC Road", "2001","87654321","Dat1");
        dbsql.indsaetStuderende(std2);
        Studerende std3 = new Studerende(3, "Nicklas", "Fiskarl", "Mofo Road", "6666", "76543218", "Dat 1");
        dbsql.indsaetStuderende(std3);
        Fag fag1 = new Fag(1,"Dat1");
        dbsql.indsaetFag(fag1);
        Fag fag2 = new Fag(2, "Mat1");
        dbsql.indsaetFag(fag2);
        Fag fag3 = new Fag(3, "Dan1");
        dbsql.opdaterstudklasse(1,"Dat2");
        dbsql.opdaterstudklasse(2,"Dan1");
        Studerende person = dbsql.soegstuderende(1);
        System.out.println(person);
        dbsql.soegEfterEfternavn("Touring");

        ArrayList<Studerende> studarr = new ArrayList<>();
        studarr.add(std1);
        studarr.add(std2);
        studarr.add(std3);
        for (int i = 0; i < studarr.size(); i++) {
            System.out.format("%s %s %s %s %s %s %s",studarr.get(i).getStdnr(),studarr.get(i).getFornavn(),studarr.get(i).getEfternavn(),studarr.get(i).getAdresse(),studarr.get(i).getPostnummer(),studarr.get(i).getMobilnummer(),studarr.get(i).getKlasse());
        }

        dbsql.soegAlleStuderende();

        ArrayList<Studfag> studFagListe;
        studFagListe = dbsql.soegStudFag();
        for (Studfag studfag : studFagListe) {
            System.out.printf("%-5s %-8s %-10s %-10s %-10s %-10s tilmeldt fag %s %s\n",
                    studfag.getStud().getStdnr(),
                    studfag.getStud().getFornavn(),
                    studfag.getStud().getEfternavn(),
                    studfag.getStud().getAdresse(),
                    studfag.getStud().getPostnummer(),
                    studfag.getStud().getKlasse(),
                    studfag.getFag().getFagnr(),
                    studfag.getFag().getFagnavn() + "\n");
        }
        //Opg.3 fra opgaveark 3/12/21
        Studerende s1 = dbsql.soegAltStdnr(2);
        System.out.println(s1.toString());
        System.out.println(s1.fagListe.size());
        for (int i = 0; i < s1.fagListe.size(); i++) {
            System.out.println(s1.fagListe.get(i));
        }

        dbsql.indsaetFagtilStuderende(3,7);
        System.out.println();

        

    }
}
