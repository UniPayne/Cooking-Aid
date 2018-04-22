package com.example.paul.myapplication.rezepterstellen_db;

/**
 * Created by Payne on 27.01.2018.
 */

public class Rezept {

    private int id;
    private String titel;
    private String dauer;
    private String sgrad;

    public Rezept(int id, String titel, String dauer, String sgrad){
        this.id = id;
        this.titel = titel;
        this.dauer = dauer;
        this.sgrad = sgrad;
    }

    public Rezept(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDauer() {
        return dauer;
    }

    public void setDauer(String dauer) {
        this.dauer = dauer;
    }

    public String getSgrad() {
        return sgrad;
    }

    public void setSgrad(String sgrad) {
        this.sgrad = sgrad;
    }

    @Override
    public String toString(){
        String a = this.titel;
        return a;
    }

}
