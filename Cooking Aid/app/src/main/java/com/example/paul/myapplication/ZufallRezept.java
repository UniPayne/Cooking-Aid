package com.example.paul.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class ZufallRezept extends AppCompatActivity {
    Button zurueck;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zufall_rezept);

        zurueck = findViewById(R.id.zufall_zur);
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
}
