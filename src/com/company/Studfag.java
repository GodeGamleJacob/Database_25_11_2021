package com.company;

public class Studfag {
    private int stdnr;
    private int fagnr;
    private Fag fag = new Fag();
    private Studerende stud = new Studerende();

    public Studfag() {
    }

    public Studfag(int stdnr, int fagnr) {
        this.stdnr = stdnr;
        this.fagnr = fagnr;
    }

    public Fag getFag() {
        return fag;
    }

    public void setFag(Fag fag) {
        this.fag = fag;
    }

    public Studerende getStud() {
        return stud;
    }

    public void setStud(Studerende stud) {
        this.stud = stud;
    }

    public int getStdnr() {
        return stdnr;
    }

    public void setStdnr(int stdnr) {
        this.stdnr = stdnr;
    }

    public int getFagnr() {
        return fagnr;
    }

    public void setFagnr(int fagnr) {
        this.fagnr = fagnr;
    }
}
