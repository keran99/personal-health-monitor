package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView inserisciReportCard, visualizzaReportCard, modificaReportCard, visualizzaGraficiCard, impostazioniCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Defining Cards
        inserisciReportCard   = (CardView) findViewById(R.id.inserisciReport_card);
        visualizzaReportCard  = (CardView) findViewById(R.id.visualizzaReport_card);
        modificaReportCard    = (CardView) findViewById(R.id.modificaReport_card);
        visualizzaGraficiCard = (CardView) findViewById(R.id.visualizzaGrafici_card);
        impostazioniCard      = (CardView) findViewById(R.id.impostazioni_card);

        // Add Click listener to the cards
        inserisciReportCard.setOnClickListener(this);
        visualizzaReportCard.setOnClickListener(this);
        modificaReportCard.setOnClickListener(this);
        visualizzaGraficiCard.setOnClickListener(this);
        impostazioniCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.inserisciReport_card : i = new Intent(this,InsertMenu.class);startActivity(i); break;
            case R.id.visualizzaReport_card : i = new Intent(this,VisualizzaReport.class);startActivity(i); break;
            case R.id.modificaReport_card : i = new Intent(this,ModificaReport.class);startActivity(i); break;
            case R.id.visualizzaGrafici_card : i = new Intent(this,VisualizzaGrafici.class);startActivity(i); break;
            case R.id.impostazioni_card : i = new Intent(this,Impostazioni.class);startActivity(i); break;
            default:break;
        }

    }

    public void closeApp(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
