package com.example.paul.myapplication.einkaufszettel_db;

/**
 *  Klasse wo die Datenbank der Einkaufszettel erstellt wird.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatenbankEinkaufszettel extends SQLiteOpenHelper{

    public static final String DB_NAME = "Einkaufszettel";
    public static final int DB_VERSION = 1;

    public static final String TABLE_EINKAUFSZETTEL = "einkaufszettel";

    public static final String SPALTE_ID = "_id";
    public static final String SPALTE_PRODUKT = "produkt";
    public static final String SPALTE_MENGE = "menge";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_EINKAUFSZETTEL +
                    "(" + SPALTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SPALTE_PRODUKT + " TEXT(50) NOT NULL, " +
                    SPALTE_MENGE + " INTEGER(3) NOT NULL);";


    public DatenbankEinkaufszettel(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQL_CREATE);
        }catch (Exception ex){
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}