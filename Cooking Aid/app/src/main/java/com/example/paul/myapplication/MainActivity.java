package com.example.paul.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import android.content.Intent;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity {


    private TextView wilkommen_cooking_aid;
    private Button zufall;
    private Button suche_nach_rezept;
    private Button suche_durch_lebensmittel;
    private Button einkaufszettel;
    private Button erstellen;
    private Button hilfe;
    private Button einstellungen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        zufall = (Button) findViewById(R.id.zufall);
        zufall.setText(R.string.zufall);

        suche_nach_rezept = (Button) findViewById(R.id.suche_nach_rezept);
        suche_nach_rezept.setText(R.string.suche_nach_rezept);

        suche_durch_lebensmittel = (Button) findViewById(R.id.suche_durch_lebensmittel);
        suche_durch_lebensmittel.setText(R.string.suche_durch_lebensmittel);

        einkaufszettel = (Button) findViewById(R.id.einkaufszettel);
        einkaufszettel.setText(R.string.einkaufszettel);

        erstellen = (Button) findViewById(R.id.erstellen);
        erstellen.setText(R.string.erstellen);


        hilfe = findViewById(R.id.infoHilfe);
        hilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Hilfe.class);
                startActivity(intent);
            }
        });

        einstellungen = findViewById(R.id.einstellungen);
        einstellungen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Einstellung.class);
                startActivity(intent);

            }
        });


        zufall = (Button) findViewById(R.id.zufall);
        zufall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ZufallRezept.class);

                startActivity(intent);
            }
        });


        suche_nach_rezept = (Button) findViewById(R.id.suche_nach_rezept);
        suche_nach_rezept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SucheRezept.class);



                startActivity(intent);
            }
        });



        suche_durch_lebensmittel = (Button) findViewById(R.id.suche_durch_lebensmittel);
        suche_durch_lebensmittel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SucheDurchLebensmittel.class);
                startActivity(intent);
            }
        });


        einkaufszettel = (Button) findViewById(R.id.einkaufszettel);
        einkaufszettel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ErstelleEinkaufszettel.class);
                startActivity(intent);
            }
        });


        erstellen = (Button) findViewById(R.id.erstellen);
        erstellen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ErstelleRezept.class);


                startActivity(intent);
            }
        });

        //Zum initiieren der Datenbanküberwachung
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }





}
