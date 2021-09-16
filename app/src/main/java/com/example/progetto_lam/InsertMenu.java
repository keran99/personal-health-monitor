package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InsertMenu extends AppCompatActivity implements View.OnClickListener{
    private CardView temperaturaCorporeaCard, pressioneSanguignaCard, indiceGlicemicoCard, oreSonnoCard, pesoCorporeoCard, homeCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_menu);

        // Defining Cards
        temperaturaCorporeaCard = (CardView) findViewById(R.id.temperatura_corporea_card);
        pressioneSanguignaCard = (CardView) findViewById(R.id.pressione_sanguigna_card);
        indiceGlicemicoCard = (CardView) findViewById(R.id.glicemia_card);
        oreSonnoCard = (CardView) findViewById(R.id.ore_sonno_card);
        pesoCorporeoCard = (CardView) findViewById(R.id.peso_card);
        homeCard = (CardView) findViewById(R.id.home_card);

        // Add Click listener to the cards
        temperaturaCorporeaCard.setOnClickListener(this);
        pressioneSanguignaCard.setOnClickListener(this);
        indiceGlicemicoCard.setOnClickListener(this);
        oreSonnoCard.setOnClickListener(this);
        pesoCorporeoCard.setOnClickListener(this);
        homeCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.temperatura_corporea_card:
                i = new Intent(this, TemperaturaCorporea.class);
                startActivity(i);
                break;
            case R.id.pressione_sanguigna_card:
                i = new Intent(this, PressioneSanguigna.class);
                startActivity(i);
                break;
            case R.id.glicemia_card:
                i = new Intent(this, IndiceGlicemico.class);
                startActivity(i);
                break;
            case R.id.ore_sonno_card:
                i = new Intent(this, OreSonno.class);
                startActivity(i);
                break;
            case R.id.peso_card:
                i = new Intent(this, PesoCorporeo.class);
                startActivity(i);
                break;
            case R.id.home_card:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            default:break;
        }
    }
}
