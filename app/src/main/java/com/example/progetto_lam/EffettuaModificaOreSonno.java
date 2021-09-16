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

public class EffettuaModificaOreSonno extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText oreSonno, commento;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private String dataOreSonno;
    private int ID_int;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effettua_modifica_ore_sonno);

        oreSonno = (EditText) findViewById(R.id.valore_ore_sonno_modifica);
        commento = (EditText) findViewById(R.id.commento_ore_sonno_modifica);
        mDisplayDate = (TextView) findViewById(R.id.selectDateSonnoModifica);

        Intent receivedIntent = getIntent();
        ID = receivedIntent.getStringExtra("id");
        ID_int = Integer.parseInt(ID);

        Cursor data = helper.getOreSonnoID(ID_int);
        if (data.getCount()==1) {
            while(data.moveToNext()){
                mDisplayDate.setText(data.getString(1));
                oreSonno.setText(data.getString(2));
                commento.setText(data.getString(3));
            }
        } else {
            Toast.makeText(EffettuaModificaOreSonno.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }

        // set date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int anno = calendario.get(Calendar.YEAR);
                int mese = calendario.get(Calendar.MONTH);
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EffettuaModificaOreSonno.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anno, int mese, int giorno) {
                mese = mese + 1;
                dataOreSonno = giorno + "/" + mese + "/" + anno;
                mDisplayDate.setText(dataOreSonno);
            }
        };
    }

    public void salvaModificaOreSonno(View view) {

        if (mDisplayDate==null) {
            Toast.makeText(EffettuaModificaOreSonno.this, "Seleziona una data", Toast.LENGTH_SHORT).show();
        }else if (oreSonno.getText().toString().isEmpty()){
            Toast.makeText(EffettuaModificaOreSonno.this, "Inserisci le ore di sonno", Toast.LENGTH_SHORT).show();
        } else {
            OreSonnoManager OSM = new OreSonnoManager();
            OSM.setId(ID_int);
            OSM.setDataOreSonno(mDisplayDate.getText().toString());
            OSM.setOreSonno(oreSonno.getText().toString());
            OSM.setCommentoOreSonno(commento.getText().toString());

            boolean result = helper.modificaOreSonno(OSM);
            if (result == true){
                Toast.makeText(EffettuaModificaOreSonno.this, "La modifica è avvenuta con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EffettuaModificaOreSonno.this, "Si è verificato un errore durante la modifica", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminaOreSonnoBTN(View view) {
        boolean result = helper.eliminaOreSonno(ID_int);
        if (result == true){
            Toast.makeText(EffettuaModificaOreSonno.this, "Il report è stato eliminato con successo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EffettuaModificaOreSonno.this, "Si è verificato un errore durante l'eliminazione del report", Toast.LENGTH_SHORT).show();
        }
    }

    // onClick of home button
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
