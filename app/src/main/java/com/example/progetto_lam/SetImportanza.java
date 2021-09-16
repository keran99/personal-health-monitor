package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SetImportanza extends AppCompatActivity {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Spinner spinnerPeso, spinnerOreSonno, spinnerGlicemia, spinnerPressione, spinnerTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_importanza);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.valoreImportanza, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTemperatura = findViewById(R.id.spinnerTemperatura);
        spinnerTemperatura.setAdapter(adapter);

        spinnerPressione = findViewById(R.id.spinnerPressione);
        spinnerPressione.setAdapter(adapter);

        spinnerGlicemia = findViewById(R.id.spinnerGlicemia);
        spinnerGlicemia.setAdapter(adapter);

        spinnerOreSonno = findViewById(R.id.spinnerOreSonno);
        spinnerOreSonno.setAdapter(adapter);

        spinnerPeso = findViewById(R.id.spinnerPeso);
        spinnerPeso.setAdapter(adapter);

        Cursor importanza = helper.getImportanceByTipoName("'TemperaturaCorporeaDB'");
        if (importanza.getCount() == 1) {
            while (importanza.moveToNext()) {
                spinnerTemperatura.setSelection(importanza.getInt(0) - 1);
            }
        }

        importanza = helper.getImportanceByTipoName("'PressioneSanguignaDB'");
        if (importanza.getCount() == 1) {
            while (importanza.moveToNext()) {
                spinnerPressione.setSelection(importanza.getInt(0) - 1);
            }
        }

        importanza = helper.getImportanceByTipoName("'IndiceGlicemicoDB'");
        if (importanza.getCount() == 1) {
            while (importanza.moveToNext()) {
                spinnerGlicemia.setSelection(importanza.getInt(0) - 1);
            }
        }

        importanza = helper.getImportanceByTipoName("'OreSonnoDB'");
        if (importanza.getCount() == 1) {
            while (importanza.moveToNext()) {
                spinnerOreSonno.setSelection(importanza.getInt(0) - 1);
            }
        }

        importanza = helper.getImportanceByTipoName("'PesoCorporeoDB'");
        if (importanza.getCount() == 1) {
            while (importanza.moveToNext()) {
                spinnerPeso.setSelection(importanza.getInt(0) - 1);
            }
        }
    }

    public void salvaImportanza(View view) {
        int importanzaTemperatura = Integer.parseInt(spinnerTemperatura.getSelectedItem().toString());
        int importanzaPressione = Integer.parseInt(spinnerPressione.getSelectedItem().toString());
        int importanzaGlicemia = Integer.parseInt(spinnerGlicemia.getSelectedItem().toString());
        int importanzaOreSonno = Integer.parseInt(spinnerOreSonno.getSelectedItem().toString());
        int importanzaPeso = Integer.parseInt(spinnerPeso.getSelectedItem().toString());

        Boolean resultTemperatura = helper.setImportanzaTemperatura(importanzaTemperatura);
        Boolean resultPressione = helper.setImportanzaPressione(importanzaPressione);
        Boolean resulGlicemia = helper.setImportanzaGlicemia(importanzaGlicemia);
        Boolean resulOreSonno = helper.setImportanzaOreSonno(importanzaOreSonno);
        Boolean resultPeso = helper.setImportanzaPeso(importanzaPeso);

        if (resultTemperatura == true && resultPressione == true && resulGlicemia == true && resulOreSonno == true && resultPeso == true) {
            Toast.makeText(SetImportanza.this, "Il salvataggio è avvenuto con successo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SetImportanza.this, "Si è verificato un errore durante il salvataggio", Toast.LENGTH_SHORT).show();
        }
    }

    public void homeImportanza(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void backImportanza(View view) {
        finish();
    }
}
