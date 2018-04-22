package com.example.paul.myapplication.rezepterstellen_db;

public class Anweisung {

    private int id;
    private String schritt;
    private String anweisung;
    private int rezeptid;

    public Anweisung(int id, String schritt, String anweisung, int rezeptid) {
        this.id = id;
        this.schritt = schritt;
        this.anweisung = anweisung;
        this.rezeptid = rezeptid;
    }

    public Anweisung(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchritt() {
        return schritt;
    }

    public void setSchritt(String schritt) {
        this.schritt = schritt;
    }

    public String getAnweisung() {
        return anweisung;
    }

    public void setAnweisung(String anweisung) {
        this.anweisung = anweisung;
    }

    public int getRezeptid() {
        return rezeptid;
    }

    public void setRezeptid(int rezeptid) {
        this.rezeptid = rezeptid;
    }

}
