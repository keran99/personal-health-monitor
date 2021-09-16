package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ModificaReport extends AppCompatActivity implements View.OnClickListener {
    private CardView modificaTemperaturaCard, modificaPressioneCard, modificaGlicemiaCard, modificaSonnoCard, modificaPesoCard, modificaHomeCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_report);
        modificaTemperaturaCard = (CardView) findViewById(R.id.modifica_temperatura_corporea_card);
        modificaPressioneCard   = (CardView) findViewById(R.id.modifica_pressione_sanguigna_card);
        modificaGlicemiaCard    = (CardView) findViewById(R.id.modifica_indice_glicemico_card);
        modificaSonnoCard       = (CardView) findViewById(R.id.modifica_ore_sonno_card);
        modificaPesoCard        = (CardView) findViewById(R.id.modifica_peso_corporeo_card);
        modificaHomeCard        = (CardView) findViewById(R.id.modifica_home_card);

        modificaTemperaturaCard.setOnClickListener(this);
        modificaPressioneCard.setOnClickListener(this);
        modificaGlicemiaCard.setOnClickListener(this);
        modificaSonnoCard.setOnClickListener(this);
        modificaPesoCard.setOnClickListener(this);
        modificaHomeCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.modifica_temperatura_corporea_card:
                i = new Intent(this, ModificaTemperaturaCorporea.class);
                startActivity(i);
                break;
            case R.id.modifica_pressione_sanguigna_card:
                i = new Intent(this, ModificaPressioneSanguigna.class);
                startActivity(i);
                break;
            case R.id.modifica_indice_glicemico_card:
                i = new Intent(this, ModificaIndiceGlicemico.class);
                startActivity(i);
                break;
            case R.id.modifica_ore_sonno_card:
                i = new Intent(this, ModificaOreSonno.class);
                startActivity(i);
                break;
            case R.id.modifica_peso_corporeo_card:
                i = new Intent(this, ModificaPesoCorporeo.class);
                startActivity(i);
                break;
            case R.id.modifica_home_card:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            default:break;
        }
    }
}
