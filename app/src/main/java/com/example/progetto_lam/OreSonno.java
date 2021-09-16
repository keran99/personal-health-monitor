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

public class OreSonno extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private static final String TAG = "OreSonno";
    private TextView mDisplayDateOreSonno;
    private DatePickerDialog.OnDateSetListener mDataSetListenerOreSonno;
    private String dataOreSonno;
    private Editable oreSonnoVal, commentoOreSonnoVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ore_sonno);

        mDisplayDateOreSonno = (TextView) findViewById(R.id.selectDateSonno);

        mDisplayDateOreSonno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(OreSonno.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListenerOreSonno, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDataSetListenerOreSonno = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1 ;
                dataOreSonno = giorno + "/" + mese + "/" + anno;
                mDisplayDateOreSonno.setText(dataOreSonno);
            }
        };
    }

    public void salvaOreSonno(View view) {
        EditText oreSonnoET, commentoOreSonnoET;
        oreSonnoET = findViewById(R.id.valore_ore_sonno);
        commentoOreSonnoET = findViewById(R.id.commento_ore_sonno);
        oreSonnoVal = oreSonnoET.getText();
        commentoOreSonnoVal = commentoOreSonnoET.getText();

        if (dataOreSonno==null){
            Toast.makeText(OreSonno.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        } else if (oreSonnoVal.toString().isEmpty()){
            Toast.makeText(OreSonno.this, "Inserire le ore di sonno", Toast.LENGTH_SHORT).show();
        } else {
            OreSonnoManager OSM = new OreSonnoManager();
            OSM.setDataOreSonno(dataOreSonno);
            OSM.setOreSonno(oreSonnoVal.toString());
            OSM.setCommentoOreSonno(commentoOreSonnoVal.toString());

            boolean result = helper.insertOreSonno(OSM);
            if (result == true){
                Toast.makeText(OreSonno.this, "Il salvataggio è avvenuto con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(OreSonno.this, "Si è verificato un errore durante il salvataggio", Toast.LENGTH_SHORT).show();
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