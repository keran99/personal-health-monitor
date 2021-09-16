package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PressioneSanguigna extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    private static final String TAG = "PressioneSanguigna";
    private TextView mDisplayDatePressione;
    private DatePickerDialog.OnDateSetListener mDataSetListenerPressione;
    private String dataPressione;
    private EditText pressioneMinimaET, pressioneMassimaET, commentoPressioneET;
    private Editable pressioneMinimaVal, pressioneMassimaVal, commentoPressioneVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressione_sanguigna);

        // set data
        mDisplayDatePressione = (TextView) findViewById(R.id.selectDatePressioneSanguigna);
        mDisplayDatePressione.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PressioneSanguigna.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListenerPressione, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListenerPressione = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1 ;
                dataPressione = giorno + "/" + mese + "/" + anno;
                mDisplayDatePressione.setText(dataPressione);
            }
        };
    }

    // click on save button
    public void salvaPressione(View view) {
        pressioneMinimaET   = findViewById(R.id.valore_pressione_minima);
        pressioneMassimaET  = findViewById(R.id.valore_pressione_massima);
        commentoPressioneET = findViewById(R.id.commento_pressione);
        pressioneMinimaVal  = pressioneMinimaET.getText();
        pressioneMassimaVal  = pressioneMassimaET.getText();
        commentoPressioneVal = commentoPressioneET.getText();

        if (dataPressione==null){
            Toast.makeText(PressioneSanguigna.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        } else if (pressioneMinimaVal.toString().isEmpty()){
            Toast.makeText(PressioneSanguigna.this, "Inserire la pressione minima", Toast.LENGTH_SHORT).show();
        } else if (pressioneMassimaVal.toString().isEmpty()){
            Toast.makeText(PressioneSanguigna.this, "Inserire la pressione massima", Toast.LENGTH_SHORT).show();
        } else {
            PressioneSanguignaManager PSM = new PressioneSanguignaManager();
            PSM.setData(dataPressione);
            PSM.setPressioneMinima(pressioneMinimaVal.toString());
            PSM.setPressioneMassima(pressioneMassimaVal.toString());
            PSM.setCommentoPressionea(commentoPressioneVal.toString());

            boolean result = helper.insertPressione(PSM);
            if (result == true){
                Toast.makeText(PressioneSanguigna.this, "Il salvataggio è avvenuto con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PressioneSanguigna.this, "Si è verificato un errore durante il salvataggio", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // onClick of home button
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}
