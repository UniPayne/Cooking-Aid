package com.example.paul.myapplication.rezepterstellen_db;

import android.content.ContentValues;
import android.content.Context;
import java.util.ArrayList;
import android.database.Cursor;
/**
 * Created by Payne on 30.12.2017.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatenbankRezept extends SQLiteOpenHelper {


    private SQLiteDatabase datenbank;

    public static final String dbName = "datenbank";
    public static final int version = 1;
    public static String tabellenName = "rezept";
    public static final String id = "_id";
    public static final String tb_name_zutat ="zutat";
    public static final String tb_name_anweisung="anweisung";
    private String[] alleSpalten = {
            "_id",
            "titel",
            "dauer",
            "sgrad",
    };


    public String createRezept =
            "CREATE TABLE rezept(" +
                    "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "titel VARCHAR(255) NOT NULL, " +
                    "dauer VARCHAR(10) NOT NULL, " +
                    "sgrad VARCHAR(15) NOT NULL)";

    public String createZutat =
            "CREATE TABLE zutat(" +
                    "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(50) NOT NULL, " +
                    "anzahl VARCHAR(5) NOT NULL, " +
                    "maße VARCHAR(15) NOT NULL," +
                    "rezept_id INTEGER NOT NULL, " +
                    "FOREIGN KEY(rezept_id) REFERENCES zutat(_id))";

    public String createAnweisung =
            "CREATE TABLE anweisung(" +
                    "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "schritt VARCHAR(3), "+
                    "anweisung VARCHAR(255), "+
                    "rezept_id INTEGER NOT NULL, "+
                    "FOREIGN KEY (rezept_id) REFERENCES anweisung(_id))";


    public DatenbankRezept(Context context) {
        super(context, dbName, null, version);
    }

    public void open() {
        datenbank = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createRezept);
        db.execSQL(createZutat);
        db.execSQL(createAnweisung);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long erstelleRezept(ContentValues values) {
        long lastInsert = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        lastInsert= db.insert(tabellenName, null, values);
        return lastInsert;
    }



    public ArrayList<Rezept> listeRezepte(String s){
        ArrayList<Rezept> erg = new ArrayList<>();

        Cursor cursor = datenbank.query(tabellenName,alleSpalten,"titel =?",new String[]{
                s
        },null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Rezept rezept =fuegeTitelZurListeHinzu(cursor);
            erg.add(rezept);
            cursor.moveToNext();
            System.out.println(erg);
        }
        cursor.close();
        return erg;
    }

    private Rezept fuegeTitelZurListeHinzu(Cursor cursor){
        Rezept rezept = new Rezept();
        rezept.setId(cursor.getInt(0));
        rezept.setTitel(cursor.getString(1));
        rezept.setDauer(cursor.getString(2));
        rezept.setSgrad(cursor.getString(3));
        return rezept;
    }

}
