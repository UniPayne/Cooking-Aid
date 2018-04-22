package com.example.paul.myapplication.rezepterstellen_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;



public class DatenbankZutat{


    public String tabellenName = "zutat";

    private DatenbankRezept datenbankHelfer;
    private SQLiteDatabase datenbank;
    private String[] alleSpalten = {
            "_id",
            "name",
            "anzahl",
            "maße",
            "rezept_id"
    };


    public DatenbankZutat(Context context) {

        datenbankHelfer = new DatenbankRezept(context);
    }

    public void open() {
        datenbank = datenbankHelfer.getWritableDatabase();
    }

    public void close() {
        datenbankHelfer.close();
    }

    public void erstelleZutat(ContentValues values) {
        datenbank.insert(tabellenName, null, values);
    }

    public List<Zutat> alleZutaten(int rezeptId) {
        List<Zutat> liste = new ArrayList<>();
        Cursor cursor = datenbank.query(tabellenName, alleSpalten, "rezept_id =?", new String[] {
                String.valueOf(rezeptId)
        }, null, null, null);


        cursor.moveToFirst();
        System.out.println(cursor);
        while(!cursor.isAfterLast()) {
            Zutat zutat = fuegeZurListeHinzu(cursor);
            liste.add(zutat);
            cursor.moveToNext();

            System.out.println(liste);
        }
        cursor.close();
        return liste;
    }

    public void deleteZutat(Zutat zutat){
        long id = zutat.getId();
        datenbank.delete(DatenbankRezept.tb_name_zutat, DatenbankRezept.id + "="+id, null);
    }


    private Zutat fuegeZurListeHinzu(Cursor cursor) {
        Zutat zutat = new Zutat();
        zutat.setId(cursor.getInt(0));
        zutat.setName(cursor.getString(1));
        zutat.setAnzahl(cursor.getString(2));
        zutat.setMaße(cursor.getString(3));
        zutat.setRezeptId(cursor.getInt(4));
        return zutat;
    }
}
