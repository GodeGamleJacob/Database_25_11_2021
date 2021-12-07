package com.company;

import java.util.ArrayList;

public class Studerende {
    private int stdnr;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private String postnummer;
    private String mobilnummer;
    private String klasse;

    public ArrayList<Fag> fagListe = new ArrayList<Fag>();

    //Tom Constructor
    public Studerende() {
    }

    @Override
    public String toString() {
        return "Studerende{" +
                "stdnr=" + stdnr +
                ", fornavn='" + fornavn + '\'' +
                ", efternavn='" + efternavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postnummer='" + postnummer + '\'' +
                ", mobilnummer='" + mobilnummer + '\'' +
                ", klasse='" + klasse + '\'' +
                '}';
    }
    //Constructor
    public Studerende(int stdnr, String fornavn, String efternavn, String adresse, String postnummer, String mobilnummer, String klasse) {
        this.stdnr = stdnr;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.mobilnummer = mobilnummer;
        this.klasse = klasse;
    }
    //Getters and Setters
    public int getStdnr() {
        return stdnr;
    }

    public void setStdnr(int stdnr) {
        this.stdnr = stdnr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getMobilnummer() {
        return mobilnummer;
    }

    public void setMobilnummer(String mobilnummer) {
        this.mobilnummer = mobilnummer;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }
}

