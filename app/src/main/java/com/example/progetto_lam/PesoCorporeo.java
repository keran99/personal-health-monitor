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

public class PesoCorporeo extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    private static final String TAG = "PesoCorporeo";
    private TextView mDisplayDatePeso;
    private DatePickerDialog.OnDateSetListener mDataSetListenerPeso;
    private String dataPeso;
    private Editable pesoCorporeoVal, commentoPesoCorporeoVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso_corporeo);

        mDisplayDatePeso = (TextView) findViewById(R.id.selectDatePeso);

        mDisplayDatePeso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PesoCorporeo.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListenerPeso, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDataSetListenerPeso = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1 ;
                dataPeso = giorno + "/" + mese + "/" + anno;
                mDisplayDatePeso.setText(dataPeso);
            }
        };
    }

    public void salvaPesoCorporeo(View view) {
        EditText pesoCorporeoET, commentoPesoCorporeoET;
        pesoCorporeoET = findViewById(R.id.valore_peso);
        commentoPesoCorporeoET = findViewById(R.id.commento_peso);
        pesoCorporeoVal = pesoCorporeoET.getText();
        commentoPesoCorporeoVal = commentoPesoCorporeoET.getText();

        if (dataPeso==null){
            Toast.makeText(PesoCorporeo.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        } else if (pesoCorporeoVal.toString().isEmpty()){
            Toast.makeText(PesoCorporeo.this, "Inserire il peso corporeo", Toast.LENGTH_SHORT).show();
        } else {
            PesoCorporeoManager PCM= new PesoCorporeoManager();
            PCM.setDataPeso(dataPeso);
            PCM.setPesoCorporeo(pesoCorporeoVal.toString());
            PCM.setCommentoPeso(commentoPesoCorporeoVal.toString());

            boolean result = helper.insertPesoCorporeo(PCM);
            if (result == true){
                Toast.makeText(PesoCorporeo.this, "Il salvataggio è avvenuto con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PesoCorporeo.this, "Si è verificato un errore durante il salvataggio", Toast.LENGTH_SHORT).show();
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
