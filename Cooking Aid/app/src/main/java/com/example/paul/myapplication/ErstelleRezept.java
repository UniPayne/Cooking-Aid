package com.example.paul.myapplication;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.paul.myapplication.rezepterstellen_db.DatenbankRezept;

public class ErstelleRezept extends AppCompatActivity{

    TextView sgrad;
    TextView dauer;
    EditText titel;
    Button weiter;
    Button zurueck;

    private String schwierigkeitsgrad;
    private String rezeptdauer;
    private String rezeptTitel;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erstelle_rezept);

        sgrad = findViewById(R.id.rezepterstellen_schwierigkeitsgrad);
        titel = findViewById(R.id.rezept_titel);
        dauer = findViewById(R.id.dauer_textfeld);

        zurueck = findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geheZurueck();
            }
        });


        //Der listener für das Titelfeld, damit die neue Eingabe dort eingetragen wird
        titel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rezeptTitel = String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        weiter = findViewById(R.id.zutaten_hinzufuegen);
        weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAll();

            }
        });

    }


    public void checkAll(){
        if(TextUtils.isEmpty(titel.getText())){
            Toast.makeText(this, "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
            return; //mache nichts
        }
        else if (TextUtils.isEmpty(sgrad.getText())) {
            Toast.makeText(this, "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
            return; // machenichts
        }
        else if (TextUtils.isEmpty(dauer.getText())) {
            Toast.makeText(this, "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
            return; //mache nichts
        }
        else {
            naechsteSeite();
        }
    }

    public void naechsteSeite(){
        DatenbankRezept datenbankRezept = new DatenbankRezept(getApplicationContext());
        ContentValues values = new ContentValues();
        values.put("titel", rezeptTitel);
        values.put("dauer", getRezeptdauer());
        values.put("sgrad", getSchwierigkeitsgrad());
        long id = datenbankRezept.erstelleRezept(values);
        int id1 = (int) id;
        Toast.makeText(ErstelleRezept.this, "Id " + id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ErstelleRezept.this, ErstelleRezeptZutat.class);
        intent.putExtra("rezeptId", String.valueOf(id1));

        startActivity(intent);
    }

    public void sgradDialog(View v){
        new AlertDialog.Builder(this)
                .setTitle(R.string.rezepterstellen_schwierigkeitsgrad_dialogtitel)
                .setSingleChoiceItems(R.array.schwierigkeitsgrad,0,null)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        ListView lw = ((AlertDialog)dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                        sgrad.setText(checkedItem.toString());
                        setSchwierigkeitsgrad(checkedItem.toString());
                    }

                })
                .setNegativeButton(R.string.abbrechen,null)
                .show();
    }


    public void dauerDialog(View v){
        int hour = 0;
        int minute = 0;
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(ErstelleRezept.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                dauer.setText( selectedHour + ":" + selectedMinute);
                setRezeptdauer(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.show();

    }

    public void geheWeiter(){
        Intent intent = new Intent(ErstelleRezept.this, ErstelleRezeptZutat.class);
        intent.putExtra("sgrad", getSchwierigkeitsgrad());
        intent.putExtra("dauer", getRezeptdauer());
        startActivity(intent);
    }

    public void geheZurueck(){
        this.finish();
    }


    public String getRezeptdauer() {
        return rezeptdauer;
    }

    public void setRezeptdauer(String rezeptdauer) {
        this.rezeptdauer = rezeptdauer;
    }

    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public String getRezeptTitel() {
        return rezeptTitel;
    }

    public void setRezeptTitel(String rezeptTitel) {
        this.rezeptTitel = rezeptTitel;
    }
}
