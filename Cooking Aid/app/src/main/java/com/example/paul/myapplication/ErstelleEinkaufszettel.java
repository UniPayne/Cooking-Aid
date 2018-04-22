package com.example.paul.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paul.myapplication.einkaufszettel_db.Einkaufszettel;
import com.example.paul.myapplication.einkaufszettel_db.EinkaufszettelHilfe;

import java.util.List;


public class ErstelleEinkaufszettel extends AppCompatActivity {
    private EinkaufszettelHilfe dataSource;
    private AutoCompleteTextView zutaten;
    private Button plus;
    private EditText menge;
    private ListView ekZettelListView;
    private Button zurueck;


    private String produkt;
    private String mengeInhalt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erstelle_einkaufszettel);

        zutaten = findViewById(R.id.liste_zutat);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.zutatenliste));
        zutaten.setAdapter(adapter);

        menge = findViewById(R.id.menge);

        ekZettelListView = findViewById(R.id.listview_shopping_memos);
        ekZettelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int loeschen = position;
                Einkaufszettel shoppingmemo = (Einkaufszettel) ekZettelListView.getItemAtPosition(loeschen);
                dataSource.deleteShoppingMemo(shoppingmemo);
                zeigeAlles();
            }
        });

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schreibeInDB();

            }
        });
        dataSource = new EinkaufszettelHilfe(this);
        dataSource.open();
        zeigeAlles();
        dataSource.close();


        zurueck = findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });
    }

    public void geheZurueck(){
        this.finish();
    }

    private void schreibeInDB() {
        produkt = zutaten.getText().toString();
        mengeInhalt = menge.getText().toString();
        if(TextUtils.isEmpty(produkt)){
            Toast.makeText(ErstelleEinkaufszettel.this, "Beide Felder müssen ausgefüllt sein", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mengeInhalt)){
            Toast.makeText(ErstelleEinkaufszettel.this, "Beide Felder müssen ausgefüllt sein", Toast.LENGTH_SHORT).show();
            return;
        }
        int quantity = Integer.parseInt(mengeInhalt);
        menge.setText("");
        zutaten.setText("");

        dataSource.erstelleEinkaufszettel(produkt, quantity);

        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

        zeigeAlles();

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
        zeigeAlles();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }



    private void zeigeAlles() {
        List<Einkaufszettel> einkaufszettelList = dataSource.getAllShoppingMemos();

        ArrayAdapter<Einkaufszettel> shoppingMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                einkaufszettelList);

        ListView liste = findViewById(R.id.listview_shopping_memos);
        liste.setAdapter(shoppingMemoArrayAdapter);
    }








}





