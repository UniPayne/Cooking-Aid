package com.example.paul.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

public class EinkaufszettelMenue extends AppCompatActivity {

    Button weiter;
    Button zurueck;
    private String ekListe_Name;
    ExpandableListView liste;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkaufszettelmenue);

        zurueck = findViewById(R.id.zur);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });

        liste = findViewById(R.id.ekListe); //Hier noch herausfinden wie man das macht!


        weiter = findViewById(R.id.einkaufsliste_erstellen);
        weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EinkaufszettelMenue.this, ErstelleEinkaufszettel.class);
                startActivity(intent);
            }
        });
    }

    public void geheZurueck() {
        this.finish();
    }


}