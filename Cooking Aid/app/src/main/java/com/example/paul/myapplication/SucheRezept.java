package com.example.paul.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.ArrayAdapter;

import com.example.paul.myapplication.rezepterstellen_db.DatenbankRezept;
import com.example.paul.myapplication.rezepterstellen_db.Rezept;

import java.util.ArrayList;

public class SucheRezept extends AppCompatActivity {
    private ListView liste;
    private EditText titel;
    private Button zurueck;
    private ArrayList<Rezept> erg = new ArrayList<>();
    DatenbankRezept dbRezept;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_rezept);
        liste = findViewById(R.id.suche_rezept_liste);
        zurueck = findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });

        titel = findViewById(R.id.suche_rezept_titel);
        titel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sucheDurchRezeptTitel(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void geheZurueck() {
        this.finish();
    }


    private void sucheDurchRezeptTitel(CharSequence s) {
        String abfrageString = s.toString();
        erg = new ArrayList<>();
        dbRezept = new DatenbankRezept(this);
        dbRezept.open();
        erg = dbRezept.listeRezepte(abfrageString);
        System.out.println(erg.toString());
        ArrayAdapter<Rezept> adapter = new ArrayAdapter<Rezept>(this, android.R.layout.simple_list_item_1,erg);
        liste.setAdapter(adapter);
        dbRezept.close();
    }

}
