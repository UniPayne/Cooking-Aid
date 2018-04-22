package com.example.paul.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class SucheDurchLebensmittel extends AppCompatActivity {

    // Hier soll man sich ein Rezept mithilfe von Lebensmitteln suchen können


    private Button zurueck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_durch_lebensmittel);

        zurueck = findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });
    }

    private void geheZurueck() {
        this.finish();
    }
}
