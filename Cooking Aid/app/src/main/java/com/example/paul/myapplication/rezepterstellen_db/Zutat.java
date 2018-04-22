package com.example.paul.myapplication.rezepterstellen_db;

/**
 * Created by Payne on 30.12.2017.
 */

public class Zutat {

    private int id;
    private String name;
    private String anzahl;
    private String maße;
    private int rezeptId;

    public Zutat(int id, String name, String anzahl, String maße, int rezeptId) {
        this.id = id;
        this.name = name;
        this.anzahl = anzahl;
        this.maße = maße;
        this.rezeptId = rezeptId;
    }

    public Zutat() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(String anzahl) {
        this.anzahl = anzahl;
    }

    public String getMaße() {
        return maße;
    }

    public void setMaße(String maße) {
        this.maße = maße;
    }

    public int getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(int rezeptId) {
        this.rezeptId = rezeptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return this.anzahl +" "+ this.maße+" "+this.name;
    }
}
