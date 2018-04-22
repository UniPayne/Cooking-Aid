package com.example.paul.myapplication.einkaufszettel_db;

/**
 * Klasse des Einkaufszettel-Objekts
 */

public class Einkaufszettel {

    private String produkt;
    private int menge;
    private long id;
    private String x = "x";


    public Einkaufszettel(String produkt, int menge, long id) {
        this.produkt = produkt;
        this.menge = menge;
        this.id = id;
    }


    public String getProdukt() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt = produkt;
    }


    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        String erg = menge + x + produkt;
        return erg;
    }
}
