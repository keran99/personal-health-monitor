package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VisualizzaGrafici extends AppCompatActivity implements View.OnClickListener {

    private CardView istogrammaCard, graficoTortaCard, graficoRadarCard, graficoLineeCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_grafici);

        // Defining Cards
        istogrammaCard   = (CardView) findViewById(R.id.istogramma_card);
        graficoTortaCard = (CardView) findViewById(R.id.graficoTorta_card);
        graficoRadarCard = (CardView) findViewById(R.id.graficoRadar_card);
        graficoLineeCard = (CardView) findViewById(R.id.grafico_linee_card) ;

        // Add Click listener to the cards
        istogrammaCard.setOnClickListener(this);
        graficoTortaCard.setOnClickListener(this);
        graficoRadarCard.setOnClickListener(this);
        graficoLineeCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.istogramma_card:
                i = new Intent(this, Istogramma.class);
                startActivity(i);
                break;
            case R.id.graficoTorta_card:
                i = new Intent(this, GraficoTorta.class);
                startActivity(i);
                break;
            case R.id.graficoRadar_card:
                i = new Intent(this, GraficoRadar.class);
                startActivity(i);
                break;
            case R.id.grafico_linee_card:
                i = new Intent(this, GraficoLinee.class);
                startActivity(i);
                break;
            default:break;
        }

    }
}
