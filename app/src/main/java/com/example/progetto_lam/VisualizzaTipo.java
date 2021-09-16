package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VisualizzaTipo extends AppCompatActivity implements View.OnClickListener {
    private CardView visualizzaTemperaturaCorporeaCard, visualizzaPressioneSanguignaCard, visualizzaIndiceGlicemicoCard, visualizzaOreSonnoCard, visualizzaPesoCorporeoCard, visualizzaHomeCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_tipo);

        visualizzaTemperaturaCorporeaCard = (CardView) findViewById(R.id.visualizza_temperatura_corporea_card);
        visualizzaPressioneSanguignaCard  = (CardView) findViewById(R.id.visualizza_pressione_sanguigna_card);
        visualizzaIndiceGlicemicoCard     = (CardView) findViewById(R.id.visualizza_indice_glicemico_card);
        visualizzaOreSonnoCard            = (CardView) findViewById(R.id.visualizza_ore_sonno_card);
        visualizzaPesoCorporeoCard        = (CardView) findViewById(R.id.visualizza_peso_corporeo_card);
        visualizzaHomeCard                = (CardView) findViewById(R.id.visualizza_home_card);

        visualizzaTemperaturaCorporeaCard.setOnClickListener(this);
        visualizzaPressioneSanguignaCard.setOnClickListener(this);
        visualizzaIndiceGlicemicoCard.setOnClickListener(this);
        visualizzaOreSonnoCard.setOnClickListener(this);
        visualizzaPesoCorporeoCard.setOnClickListener(this);
        visualizzaHomeCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.visualizza_temperatura_corporea_card:
                i = new Intent(this, VisualizzaTemperaturaCorporea.class);
                startActivity(i);
                break;
            case R.id.visualizza_pressione_sanguigna_card:
                i = new Intent(this, VisualizzaPressioneSanguigna.class);
                startActivity(i);
                break;
            case R.id.visualizza_indice_glicemico_card:
                i = new Intent(this, VisualizzaIndiceGlicemico.class);
                startActivity(i);
                break;
            case R.id.visualizza_ore_sonno_card:
                i = new Intent(this, VisualizzaOreSonno.class);
                startActivity(i);
                break;
            case R.id.visualizza_peso_corporeo_card:
                i = new Intent(this, VisualizzaPesoCorporeo.class);
                startActivity(i);
                break;
            case R.id.visualizza_home_card:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            default:break;
        }
    }
}
