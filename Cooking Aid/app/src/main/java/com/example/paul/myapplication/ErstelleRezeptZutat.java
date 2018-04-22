package com.example.paul.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paul.myapplication.rezepterstellen_db.DatenbankRezept;
import com.example.paul.myapplication.rezepterstellen_db.DatenbankZutat;
import com.example.paul.myapplication.rezepterstellen_db.Zutat;

import java.util.List;


public class ErstelleRezeptZutat extends AppCompatActivity {

    private String schwierigkeitsgrad;
    private String dauer;
    private String rezeptTitel;
    private String rezeptId;
    private Button zurueck;

    private ListView listView;
    private Button weiter;
    private TextView maße;
    private AutoCompleteTextView zutaten;
    private EditText menge;
    DatenbankZutat datenbankZutat;
    DatenbankRezept datenbankRezept;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erstelle_rezept_zutaten);

        zurueck = findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });

        datenbankRezept = new DatenbankRezept(this);
        datenbankZutat =  new DatenbankZutat(this);
        menge = findViewById(R.id.menge);
        maße = findViewById(R.id.maßeinheit);
        zutaten = findViewById(R.id.zutaten);

        weiter = findViewById(R.id.zutaten_weiter);
        weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weiter();
            }
        });


        // Für das Dropdown feature der Zutatenauswahlliste
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.zutatenliste));
        zutaten.setAdapter(adapter);

        Intent intent = getIntent();

        String id = intent.getStringExtra("rezeptId");
        int id1 = Integer.parseInt(id);
        setRezeptId(String.valueOf(id1));

        listView = findViewById(R.id.zutaten_liste);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int loeschen = position;
                Zutat z1 = (Zutat) listView.getItemAtPosition(loeschen);
                datenbankZutat.deleteZutat(z1);
                zeigenAlleZutaten();
            }
        });

    }

    private void geheZurueck() {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        datenbankZutat.open();
        zeigenAlleZutaten();

    }

    @Override
    protected void onPause() {
        super.onPause();
        datenbankZutat.close();
    }

    public void maßeinheitDialog(View view){
        new AlertDialog.Builder(this)
                .setTitle(R.string.maßeinheit_waehlen)
                .setSingleChoiceItems(getResources().getStringArray(R.array.maßeinheit),0,null)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        ListView lw = ((AlertDialog)dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                        maße.setText(checkedItem.toString());
                        setSchwierigkeitsgrad(checkedItem.toString());
                    }

                })
                .setNegativeButton(R.string.abbrechen,null)
                .show();
    }

    public void mehr(View view){
        String neueZutat = zutaten.getText().toString();
        String neueMenge = menge.getText().toString();
        String neueMaßeinheit = maße.getText().toString();


        ContentValues values = new ContentValues();
        values.put("name", neueZutat);
        values.put("anzahl", neueMenge);
        values.put("maße", neueMaßeinheit);
        values.put("rezept_id", getRezeptId());
        datenbankZutat.erstelleZutat(values);

        zutaten.setText("");
        menge.setText("");
        maße.setText("");

        zeigenAlleZutaten();

    }

    private void zeigenAlleZutaten() {
        List<Zutat> liste = datenbankZutat.alleZutaten(Integer.parseInt(getRezeptId()));

        ArrayAdapter<Zutat> zutatAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, liste);

        listView.setAdapter(zutatAdapter);

    }

    private void weiter(){
        Intent intent = new Intent(ErstelleRezeptZutat.this, ErstelleRezeptAnweisung.class);
        intent.putExtra("rezeptId", getRezeptId());
        startActivity(intent);

    }




    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public String getDauer() {
        return dauer;
    }

    public void setDauer(String dauer) {
        this.dauer = dauer;
    }

    public String getRezeptTitel() {
        return rezeptTitel;
    }

    public void setRezeptTitel(String rezeptTitel) {
        this.rezeptTitel = rezeptTitel;
    }

    public String getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(String rezeptId) {
        this.rezeptId = rezeptId;
    }
}
