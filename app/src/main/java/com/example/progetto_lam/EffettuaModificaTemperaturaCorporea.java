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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EffettuaModificaTemperaturaCorporea extends AppCompatActivity {
    private static final String TAG = "EffettuaModificaTemp";
    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText temperatura, commento;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private String dataTemperatura;
    private int ID_int;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effettua_modifica_temperatura_corporea);

        temperatura = (EditText) findViewById(R.id.valore_temperatura_modifica);
        commento = (EditText) findViewById(R.id.commento_temperatura_modifica);
        mDisplayDate = (TextView) findViewById(R.id.selectDateModifica);

        Intent receivedIntent = getIntent();
        ID = receivedIntent.getStringExtra("id");
        ID_int = Integer.parseInt(ID);

        Cursor data = helper.getTemperaturaID(ID_int);

        if (data.getCount()==1) {
            while(data.moveToNext()){
                mDisplayDate.setText(data.getString(1));
                temperatura.setText(data.getString(2));
                commento.setText(data.getString(3));
            }
        } else {
            Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }

        // set date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EffettuaModificaTemperaturaCorporea.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1;
                dataTemperatura = giorno + "/" + mese + "/" + anno;
                mDisplayDate.setText(dataTemperatura);
            }
        };

    }

    public void salvaModificaTemperatura(View view) {
        if (mDisplayDate==null) {
            Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        }else if (temperatura.getText().toString().isEmpty()){
            Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "Inserisci la temperatura corporea", Toast.LENGTH_SHORT).show();
        } else {
            TemperaturaCorporeaManager TCM = new TemperaturaCorporeaManager();
            TCM.setId(ID_int);
            TCM.setData(mDisplayDate.getText().toString());
            TCM.setTemperatura(temperatura.getText().toString());
            TCM.setCommento(commento.getText().toString());

            boolean result = helper.modificaTemperatura(TCM);
            if (result == true){
                Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "La modifica è avvenuta con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "Si è verificato un errore durante la modifica", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminaTemperaturaBTN(View view) {
        boolean result = helper.eliminaTemperatura(ID_int);
        if (result == true){
            Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "Il report è stato eliminato con successo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EffettuaModificaTemperaturaCorporea.this, "Si è verificato un errore durante l'eliminazione del report", Toast.LENGTH_SHORT).show();
        }
    }

    // onClick of home button
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
