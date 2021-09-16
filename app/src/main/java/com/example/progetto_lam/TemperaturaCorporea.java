package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TemperaturaCorporea extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private static final String TAG = "TemperaturaCorporea";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private String data;
    private EditText TemperaturaET, commentoTemperaturaET;
    private Editable temperatura_val, commentoTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura_corporea);

        // set date
        mDisplayDate = (TextView) findViewById(R.id.selectDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TemperaturaCorporea.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1;
                data = giorno + "/" + mese + "/" + anno;
                mDisplayDate.setText(data);
            }
        };
    }

    // click on save button
    public void salvaTemperatura(View view) {
        TemperaturaET = findViewById(R.id.valore_temperatura);
        commentoTemperaturaET = findViewById(R.id.commento_temperatura);
        temperatura_val = TemperaturaET.getText();
        commentoTemperatura = commentoTemperaturaET.getText();


        if (data==null){
            Toast.makeText(TemperaturaCorporea.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        } else if(temperatura_val.toString().isEmpty()){
            Toast.makeText(TemperaturaCorporea.this, "Inserisci la temperatura corporea", Toast.LENGTH_SHORT).show();
        } else {
            TemperaturaCorporeaManager TCM = new TemperaturaCorporeaManager();
            TCM.setData(data);
            TCM.setTemperatura(temperatura_val.toString());
            TCM.setCommento(commentoTemperatura.toString());

            boolean result = helper.insertTemperatura(TCM);
            if (result == true){
                Toast.makeText(TemperaturaCorporea.this, "Il salvataggio è avvenuto con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TemperaturaCorporea.this, "Si è verificato un errore durante il salvataggio", Toast.LENGTH_SHORT).show();
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
