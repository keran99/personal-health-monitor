package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SetSoglia extends AppCompatActivity {
    private DatabaseHelper helper = new DatabaseHelper(this);
    EditText temperaturaMax, temperaturaMin, pressioneMinimaMin, pressioneMinimaMax, pressioneMassimaMin, pressioneMassimaMax, glicemiaMin, glicemiaMax, oreSonnoMin, oreSonnoMax, pesoMin, pesoMax;
    private Spinner spinnerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_soglia);

        temperaturaMax = findViewById(R.id.maxTemperatura);
        temperaturaMin = findViewById(R.id.minTemperatura);
        Cursor dataTemperatura = helper.getsogliaTemperatura();
        if (dataTemperatura.getCount() == 1){
            while(dataTemperatura.moveToNext()) {
                temperaturaMin.setText(dataTemperatura.getString(0));
                temperaturaMax.setText(dataTemperatura.getString(1));
            }
        }

        pressioneMassimaMax = findViewById(R.id.maxPressioneMassima);
        pressioneMassimaMin = findViewById(R.id.minPressioneMassima);
        Cursor dataPressioneMassima = helper.getsogliaPressioneMassima();
        if (dataPressioneMassima.getCount() ==1){
            while(dataPressioneMassima.moveToNext()) {
                pressioneMassimaMin.setText(dataPressioneMassima.getString(0));
                pressioneMassimaMax.setText(dataPressioneMassima.getString(1));
            }
        }

        pressioneMinimaMax = findViewById(R.id.maxPressioneMinima);
        pressioneMinimaMin = findViewById(R.id.minPressioneMinima);
        Cursor dataPressioneMinima = helper.getsogliaPressioneMinima();
        if (dataPressioneMinima.getCount() ==1){
            while(dataPressioneMinima.moveToNext()) {
                pressioneMinimaMin.setText(dataPressioneMinima.getString(0));
                pressioneMinimaMax.setText(dataPressioneMinima.getString(1));
            }
        }

        glicemiaMax = findViewById(R.id.maxGlicemia);
        glicemiaMin = findViewById(R.id.minGlicemia);
        Cursor dataGlicemia = helper.getsogliaIndiceGlicemico();
        if (dataGlicemia.getCount() ==1){
            while(dataGlicemia.moveToNext()) {
                glicemiaMin.setText(dataGlicemia.getString(0));
                glicemiaMax.setText(dataGlicemia.getString(1));
            }
        }

        oreSonnoMax = findViewById(R.id.maxOreSonno);
        oreSonnoMin = findViewById(R.id.minOreSonno);
        Cursor dataOreSonno = helper.getsogliaOreSonno();
        if (dataOreSonno.getCount() ==1){
            while(dataOreSonno.moveToNext()) {
                oreSonnoMin.setText(dataOreSonno.getString(0));
                oreSonnoMax.setText(dataOreSonno.getString(1));
            }
        }

        pesoMax = findViewById(R.id.maxPeso);
        pesoMin = findViewById(R.id.minPeso);
        Cursor dataPeso = helper.getsogliaPeso();
        if (dataPeso.getCount() ==1){
            while(dataPeso.moveToNext()) {
                pesoMin.setText(dataPeso.getString(0));
                pesoMax.setText(dataPeso.getString(1));
            }
        }

        spinnerTime = findViewById(R.id.spinnerControllo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.range, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapter);

        Cursor periodo = helper.getSogliaPeriodo();
        if (periodo.getCount() == 1){
            while(periodo.moveToNext()) {
                String periodoCheck = periodo.getString(0);
                if (periodoCheck.equals("Ultimi 5 report")) {
                    spinnerTime.setSelection(0);
                } else if (periodoCheck.equals("Ultimi 10 report") ){
                    spinnerTime.setSelection(1);
                } else if (periodoCheck.equals("Ultimi 20 report")) {
                    spinnerTime.setSelection(2);
                } else if (periodoCheck.equals("Ultimi 30 report")){
                    spinnerTime.setSelection(3);
                } else {
                    spinnerTime.setSelection(4);
                }
            }
        }
    }

    public void salvaSoglie(View view) {
        if (temperaturaMax.getText().toString().isEmpty() || temperaturaMin.getText().toString().isEmpty() ||
                pressioneMassimaMax.getText().toString().isEmpty() || pressioneMassimaMin.getText().toString().isEmpty() ||
                pressioneMinimaMax.getText().toString().isEmpty() || pressioneMinimaMin.getText().toString().isEmpty() ||
                glicemiaMax.getText().toString().isEmpty() || glicemiaMin.getText().toString().isEmpty() ||
                oreSonnoMax.getText().toString().isEmpty() || oreSonnoMin.getText().toString().isEmpty() ||
                pesoMax.getText().toString().isEmpty() || pesoMin.getText().toString().isEmpty()){
            Toast.makeText(SetSoglia.this, "ERRORE: riempire tutti i campi", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(temperaturaMax.getText().toString()) < Double.parseDouble(temperaturaMin.getText().toString())) {
            Toast.makeText(SetSoglia.this, "ERRORE: il valore max di temperatura non può essere inferiore al valore min", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(pressioneMassimaMax.getText().toString()) < Double.parseDouble(pressioneMassimaMin.getText().toString())) {
            Toast.makeText(SetSoglia.this, "ERRORE: il valore max di pressione massima non può essere inferiore al valore min", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(pressioneMinimaMax.getText().toString()) < Double.parseDouble(pressioneMinimaMin.getText().toString())){
            Toast.makeText(SetSoglia.this, "ERRORE: il valore max di pressione minima non può essere inferiore al valore min", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(glicemiaMax.getText().toString()) < Double.parseDouble(glicemiaMin.getText().toString())) {
            Toast.makeText(SetSoglia.this, "ERRORE: il valore max di indice glicemico non può essere inferiore al valore min", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(oreSonnoMax.getText().toString()) < Double.parseDouble(oreSonnoMin.getText().toString())) {
            Toast.makeText(SetSoglia.this, "ERRORE: il valore max di ore di sonno non può essere inferiore al valore min", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(pesoMax.getText().toString()) < Double.parseDouble(pesoMin.getText().toString())) {
            Toast.makeText(SetSoglia.this, "ERRORE: il valore max di peso corporeo non può essere inferiore al valore min", Toast.LENGTH_SHORT).show();
        } else {
            boolean resultTemperatura = helper.setSogliaTemperatura(Double.parseDouble(temperaturaMax.getText().toString()), Double.parseDouble(temperaturaMin.getText().toString()));
            boolean resultPressioneMassima = helper.setSogliaPressioneMassima(Double.parseDouble(pressioneMassimaMax.getText().toString()), Double.parseDouble(pressioneMassimaMin.getText().toString()));
            boolean resultPressioneMinima = helper.setSogliaPressioneMinima(Double.parseDouble(pressioneMinimaMax.getText().toString()), Double.parseDouble(pressioneMinimaMin.getText().toString()));
            boolean resultIndiceGlicemico = helper.setSogliaIndiceGlicemico(Double.parseDouble(glicemiaMax.getText().toString()), Double.parseDouble(glicemiaMin.getText().toString()));
            boolean resultOreSonno = helper.setSogliaOreSonno(Double.parseDouble(oreSonnoMax.getText().toString()), Double.parseDouble(oreSonnoMin.getText().toString()));
            boolean resultPeso = helper.setSogliaPeso(Double.parseDouble(pesoMax.getText().toString()), Double.parseDouble(pesoMin.getText().toString()));
            boolean resultPeriodo = helper.setSogliaPeriodo(spinnerTime.getSelectedItem().toString());
            Toast.makeText(SetSoglia.this, "Salvataggio avvenuto con successo", Toast.LENGTH_SHORT).show();
        }
    }

    public void homeSoglie(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void backSoglie(View view) {
        finish();
    }
}
