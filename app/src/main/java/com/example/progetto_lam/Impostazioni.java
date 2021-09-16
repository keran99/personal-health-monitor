package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Impostazioni extends AppCompatActivity implements View.OnClickListener {

    private CardView setImportanzaCard, setSoglieCard, setNotificheCard, homeCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);

        setImportanzaCard = (CardView) findViewById(R.id.imposta_importanza_card);
        setSoglieCard =  (CardView) findViewById(R.id.imposta_soglia_card);
        setNotificheCard = (CardView) findViewById(R.id.imposta_notifiche_card);
        homeCard = (CardView) findViewById(R.id.home_impostazioni_card);

        setImportanzaCard.setOnClickListener(this);
        setSoglieCard.setOnClickListener(this);
        setNotificheCard.setOnClickListener(this);
        homeCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.imposta_importanza_card : i = new Intent(this,SetImportanza.class);startActivity(i); break;
            case R.id.imposta_soglia_card : i = new Intent(this,SetSoglia.class);startActivity(i); break;
            case R.id.imposta_notifiche_card : i = new Intent(this,SetNotifiche.class);startActivity(i); break;
            case R.id.home_impostazioni_card : i = new Intent(this,MainActivity.class);startActivity(i); break;
            default:break;
        }
    }
}
