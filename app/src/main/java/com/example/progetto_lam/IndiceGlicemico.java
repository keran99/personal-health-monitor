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

public class IndiceGlicemico extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private static final String TAG = "IndiceGlicemico";
    private TextView mDisplayDateGlicemia;
    private DatePickerDialog.OnDateSetListener mDataSetListenerGlicemia;
    private String dataGlicemia;
    private Editable glicemiaVal, commentoGlicemiaVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indice_glicemico);

        mDisplayDateGlicemia = (TextView) findViewById(R.id.selectDateGlicemia);

        mDisplayDateGlicemia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(IndiceGlicemico.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListenerGlicemia, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDataSetListenerGlicemia = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1 ;
                dataGlicemia = giorno + "/" + mese + "/" + anno;
                mDisplayDateGlicemia.setText(dataGlicemia);
            }
        };
    }

    public void salvaGlicemia(View view) {
        EditText glicemiaET, commentoGlicemiaET;
        glicemiaET = findViewById(R.id.valore_glicemia);
        commentoGlicemiaET = findViewById(R.id.commento_glicemia);
        glicemiaVal = glicemiaET.getText();
        commentoGlicemiaVal = commentoGlicemiaET.getText();

        if (dataGlicemia==null){
            Toast.makeText(IndiceGlicemico.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        } else if (glicemiaVal.toString().isEmpty()){
            Toast.makeText(IndiceGlicemico.this, "Inserire il valore della glicemia", Toast.LENGTH_SHORT).show();
        } else {
            IndiceGlicemicoManager IGM = new IndiceGlicemicoManager();
            IGM.setData(dataGlicemia);
            IGM.setGlicemia(glicemiaVal.toString());
            IGM.setCommentoGlicemia(commentoGlicemiaVal.toString());

            boolean result = helper.insertGlicemia(IGM);
            if (result == true){
                Toast.makeText(IndiceGlicemico.this, "Il salvataggio è avvenuto con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(IndiceGlicemico.this, "Si è verificato un errore durante il salvataggio", Toast.LENGTH_SHORT).show();
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
