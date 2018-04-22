package com.example.paul.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paul.myapplication.rezepterstellen_db.DatenbankAnweisung;
import com.example.paul.myapplication.rezepterstellen_db.DatenbankRezept;


public class ErstelleRezeptAnweisung extends AppCompatActivity {
    private String rezeptId;
    DatenbankAnweisung datenbankAnweisung;
    DatenbankRezept datenbankRezept;


    private Button plus;
    private Button fertig;
    private EditText schritt;
    private EditText anweisungen;
    private Button zurueck;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erstelle_rezept_anweisung);
        datenbankAnweisung = new DatenbankAnweisung(this);
        datenbankRezept = new DatenbankRezept(this);


        zurueck = findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });
        schritt = findViewById(R.id.anweisung_schritt);

        anweisungen = findViewById(R.id.anweisung);

        plus = findViewById(R.id.anweisung_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neueAnweisung();
            }
        });


        Intent intent = getIntent();
        String id = intent.getStringExtra("rezeptId");
        int id1 = Integer.parseInt(id);
        setRezeptId(String.valueOf(id1));
        fertig = findViewById(R.id.anweisung_speichern);
        fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zurueckZurUebersicht();
            }
        });

    }

    private void geheZurueck() {
        this.finish();
    }

    private void zurueckZurUebersicht() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void neueAnweisung() {
        String schrittString = schritt.getText().toString();
        String anweisungString = anweisungen.getText().toString();
        ContentValues values = new ContentValues();

        values.put("schritt", schrittString);
        values.put("anweisung",anweisungString);
        values.put("rezept_id",getRezeptId());
        datenbankAnweisung.erstelleAnweisung(values);

        System.out.println("Schritt: "+schrittString);
        System.out.println("Anweisung: "+anweisungString);
        System.out.println("RezeptID: "+ getRezeptId());



        schritt.setText("");
        anweisungen.setText("");

    }

    @Override
    protected void onResume() {
        super.onResume();
        datenbankAnweisung.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datenbankAnweisung.close();
    }

    public void setRezeptId(String rezeptId) {
        this.rezeptId = rezeptId;
    }

    public String getRezeptId(){
        return this.rezeptId;
    }
}
