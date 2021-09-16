package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VisualizzaReport extends AppCompatActivity implements View.OnClickListener {

    private CardView visualizzaTipoCard, visualizzaGiornoCard, visualizzaImportanzaCard, homeCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_report);

        visualizzaTipoCard = (CardView) findViewById(R.id.visualizzaTipo);
        visualizzaGiornoCard = (CardView) findViewById(R.id.visualizzaCalendario);
        visualizzaImportanzaCard = (CardView) findViewById(R.id.visualizzaImportanza);
        homeCard = (CardView) findViewById(R.id.visualizzaHome);


        visualizzaTipoCard.setOnClickListener(this);
        visualizzaGiornoCard.setOnClickListener(this);
        visualizzaImportanzaCard.setOnClickListener(this);
        homeCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.visualizzaTipo : i = new Intent(this,VisualizzaTipo.class);startActivity(i); break;
            case R.id.visualizzaCalendario : i = new Intent(this,VisualizzaGiorno.class);startActivity(i); break;
            case R.id.visualizzaImportanza : i = new Intent(this,VisualizzaImportanza.class);startActivity(i); break;
            case R.id.visualizzaHome : i = new Intent(this,MainActivity.class);startActivity(i); break;
            default:break;
        }
    }
}
