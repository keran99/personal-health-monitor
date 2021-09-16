package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EffettuaModificaIndiceGlicemia extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText glicemia, commento;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private String dataGlicemia;
    private int ID_int;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effettua_modifica_indice_glicemia);

        glicemia = (EditText) findViewById(R.id.valore_glicemia_modifica);
        commento = (EditText) findViewById(R.id.commento_glicemia_modifica);
        mDisplayDate = (TextView) findViewById(R.id.selectDateGlicemiaModifica);

        Intent receivedIntent = getIntent();
        ID = receivedIntent.getStringExtra("id");
        ID_int = Integer.parseInt(ID);

        Cursor data = helper.getGlicemiaID(ID_int);

        if (data.getCount()==1) {
            while(data.moveToNext()){
                mDisplayDate.setText(data.getString(1));
                glicemia.setText(data.getString(2));
                commento.setText(data.getString(3));
            }
        } else {
            Toast.makeText(EffettuaModificaIndiceGlicemia.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }
        // set date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EffettuaModificaIndiceGlicemia.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1;
                dataGlicemia = giorno + "/" + mese + "/" + anno;
                mDisplayDate.setText(dataGlicemia);
            }
        };
    }

    public void salvaModificaGlicemia(View view) {
        if (mDisplayDate==null) {
            Toast.makeText(EffettuaModificaIndiceGlicemia.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        }else if (glicemia.getText().toString().isEmpty()){
            Toast.makeText(EffettuaModificaIndiceGlicemia.this, "Inserisci la il valore della glicemia", Toast.LENGTH_SHORT).show();
        } else {
            IndiceGlicemicoManager IGM = new IndiceGlicemicoManager();
            IGM.setId(ID_int);
            IGM.setData(mDisplayDate.getText().toString());
            IGM.setGlicemia(glicemia.getText().toString());
            IGM.setCommentoGlicemia(commento.getText().toString());

            boolean result = helper.modificaGlicemia(IGM);
            if (result == true){
                Toast.makeText(EffettuaModificaIndiceGlicemia.this, "La modifica è avvenuta con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EffettuaModificaIndiceGlicemia.this, "Si è verificato un errore durante la modifica", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminaGlicemiaBTN(View view) {
        boolean result = helper.eliminaGlicemia(ID_int);
        if (result == true){
            Toast.makeText(EffettuaModificaIndiceGlicemia.this, "Il report è stato eliminato con successo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EffettuaModificaIndiceGlicemia.this, "Si è verificato un errore durante l'eliminazione del report", Toast.LENGTH_SHORT).show();
        }
    }

    // onClick of home button
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
