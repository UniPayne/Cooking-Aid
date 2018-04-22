package com.example.paul.myapplication.einkaufszettel_db;

/**
 * Klasse EinkaufszettelHilfe besitzt die Operatoren, welche hilft, auf die Einkaufszettel -
 * Datenbank zuzugreifen
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;



public class EinkaufszettelHilfe {


    private SQLiteDatabase database;
    private DatenbankEinkaufszettel dbHelper;

    private String[] alleSpalten = {
            DatenbankEinkaufszettel.SPALTE_ID,
            DatenbankEinkaufszettel.SPALTE_PRODUKT,
            DatenbankEinkaufszettel.SPALTE_MENGE
    };


    public EinkaufszettelHilfe(Context context) {
        dbHelper = new DatenbankEinkaufszettel(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    /**
     *
     */
    public Einkaufszettel erstelleEinkaufszettel(String produkt, int menge) {
        ContentValues values = new ContentValues();
        values.put(DatenbankEinkaufszettel.SPALTE_PRODUKT, produkt);
        values.put(DatenbankEinkaufszettel.SPALTE_MENGE, menge);

        long insertId = database.insert(DatenbankEinkaufszettel.TABLE_EINKAUFSZETTEL, null, values);

        Cursor cursor = database.query(DatenbankEinkaufszettel.TABLE_EINKAUFSZETTEL,
                alleSpalten, DatenbankEinkaufszettel.SPALTE_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Einkaufszettel einkaufszettel = cursorToShoppingMemo(cursor);
        cursor.close();

        return einkaufszettel;
    }

    public void deleteShoppingMemo(Einkaufszettel einkaufszettel) {
        long id = einkaufszettel.getId();

        database.delete(DatenbankEinkaufszettel.TABLE_EINKAUFSZETTEL,
                DatenbankEinkaufszettel.SPALTE_ID + "=" + id,
                null);

    }

    private Einkaufszettel cursorToShoppingMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatenbankEinkaufszettel.SPALTE_ID);
        int idProduct = cursor.getColumnIndex(DatenbankEinkaufszettel.SPALTE_PRODUKT);
        int idQuantity = cursor.getColumnIndex(DatenbankEinkaufszettel.SPALTE_MENGE);
        String product = cursor.getString(idProduct);
        int quantity = cursor.getInt(idQuantity);
        long id = cursor.getLong(idIndex);
        Einkaufszettel einkaufszettel = new Einkaufszettel(product, quantity, id);
        return einkaufszettel;
    }

    public List<Einkaufszettel> getAllShoppingMemos() {
        List<Einkaufszettel> einkaufszettelList = new ArrayList<>();

        Cursor cursor = database.query(DatenbankEinkaufszettel.TABLE_EINKAUFSZETTEL,
                alleSpalten, null, null, null, null, null);

        cursor.moveToFirst();
        Einkaufszettel einkaufszettel;

        while(!cursor.isAfterLast()) {
            einkaufszettel = cursorToShoppingMemo(cursor);
            einkaufszettelList.add(einkaufszettel);
            cursor.moveToNext();
        }

        cursor.close();

        return einkaufszettelList;
    }
}

