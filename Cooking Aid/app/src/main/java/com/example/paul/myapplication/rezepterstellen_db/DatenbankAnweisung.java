package com.example.paul.myapplication.rezepterstellen_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Payne on 27.01.2018.
 */

public class DatenbankAnweisung {
    public String tabellenName ="anweisung";
    private SQLiteDatabase datenbank;

    private DatenbankRezept datenbankHelfer;
    private String[] alleSpalten = {
            "_id",
            "schritt",
            "anweisung",
            "rezept_id"
    };

    public DatenbankAnweisung(Context context) {
        datenbankHelfer = new DatenbankRezept(context);
    }

    public void open(){
        datenbank = datenbankHelfer.getWritableDatabase();
    }

    public void close() {
        datenbankHelfer.close();
    }

    public void erstelleAnweisung(ContentValues values){
        datenbank.insert(tabellenName, null, values);
    }

    public List<Anweisung> alleAnweisungen(int anweisungId){
        List<Anweisung> liste = new ArrayList<>();
        Cursor cursor = datenbank.query(tabellenName,alleSpalten, "rezept_id =?", new String[]{
                String.valueOf(anweisungId)},null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Anweisung anweisung = fuegeZurListeHinzu(cursor);
            liste.add(anweisung);
            cursor.moveToNext();
            System.out.println(liste);
        }
        return liste;
    }

    public void deleteAnweisung(Anweisung anweisung){
        long id = anweisung.getId();
        datenbank.delete(DatenbankRezept.tb_name_anweisung, DatenbankRezept.id +"="+id,null);
    }

    private Anweisung fuegeZurListeHinzu(Cursor cursor){
        Anweisung anweisung = new Anweisung();
        anweisung.setId(cursor.getInt(0));
        anweisung.setSchritt(cursor.getString(1));
        anweisung.setAnweisung(cursor.getString(2));
        anweisung.setRezeptid(cursor.getInt(3));
        return anweisung;
    }

}
