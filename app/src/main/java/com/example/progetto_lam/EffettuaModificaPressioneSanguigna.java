package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EffettuaModificaPressioneSanguigna extends AppCompatActivity {

    private static final String TAG = "EffettuaModificaPress";
    DatabaseHelper helper = new DatabaseHelper(this);
    private EditText pressioneMinima, pressioneMassima, commento;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private String dataPressione;
    private int ID_int;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effettua_modifica_pressione_sanguigna);

        pressioneMinima = (EditText) findViewById(R.id.valore_pressione_minima_modifica);
        pressioneMassima = (EditText) findViewById(R.id.valore_pressione_massima_modifica);
        commento = (EditText) findViewById(R.id.commento_pressione_modifica);
        mDisplayDate = (TextView) findViewById(R.id.selectDatePressioneModifica);

        Intent receivedIntent = getIntent();
        ID = receivedIntent.getStringExtra("id");
        ID_int = Integer.parseInt(ID);

        Cursor data = helper.getPressioneID(ID_int);

        if (data.getCount()==1) {
            while(data.moveToNext()){
                mDisplayDate.setText(data.getString(1));
                pressioneMinima.setText(data.getString(2));
                pressioneMassima.setText(data.getString(3));
                commento.setText(data.getString(4));
            }
        } else {
            Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }

        // set date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EffettuaModificaPressioneSanguigna.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1;
                dataPressione = giorno + "/" + mese + "/" + anno;
                mDisplayDate.setText(dataPressione);
            }
        };

    }

    public void salvaModificaPressione(View view) {
        if (mDisplayDate==null) {
            Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        }else if (pressioneMinima.getText().toString().isEmpty()){
            Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Inserisci la pressione minima", Toast.LENGTH_SHORT).show();  }
        else if (pressioneMassima.getText().toString().isEmpty()){
            Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Inserisci la pressione massima", Toast.LENGTH_SHORT).show();
        } else {
            PressioneSanguignaManager PSM = new PressioneSanguignaManager();
            PSM.setId(ID_int);
            PSM.setData(mDisplayDate.getText().toString());
            PSM.setPressioneMinima(pressioneMinima.getText().toString());
            PSM.setPressioneMassima(pressioneMassima.getText().toString());
            PSM.setCommentoPressionea(commento.getText().toString());

            boolean result = helper.modificaPressione(PSM);
            if (result == true){
                Toast.makeText(EffettuaModificaPressioneSanguigna.this, "La modifica è avvenuta con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Si è verificato un errore durante la modifica", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminaPressioneBTN(View view) {
        boolean result = helper.eliminaPressione(ID_int);
        if (result == true){
            Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Il report è stato eliminato con successo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EffettuaModificaPressioneSanguigna.this, "Si è verificato un errore durante l'eliminazione del report", Toast.LENGTH_SHORT).show();
        }
    }

    // onClick of home button
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
