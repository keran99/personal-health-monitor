package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class VisualizzaImportanza extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_importanza);

        Spinner spinner = findViewById(R.id.spinnerImportanza);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.valoreImportanza, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String imp = parent.getItemAtPosition(position).toString();

        ListAdapter listAdapter = null;


        ListView listView = (ListView) findViewById(R.id.listViewImportanza);
        ArrayList<String> theList = new ArrayList<>();

        listView.setAdapter(null);
        theList.clear();

        Cursor dataimp = helper.getTableNameByImportance(imp);
        Cursor data;

        if (dataimp.getCount() == 0) {
            Toast.makeText(VisualizzaImportanza.this, "Non sono presenti reports con l'importanza selezionata", Toast.LENGTH_SHORT).show();
        } else {
            while (dataimp.moveToNext()) {
                if (dataimp.getString(1).equals("TemperaturaCorporeaDB")) {
                    data = helper.getReportsTemperatura();

                    if (dataimp.getCount() == 0) {
                        Toast.makeText(VisualizzaImportanza.this, "Non sono stati inseriri dati relativi alla temperatura corporea", Toast.LENGTH_SHORT).show();
                    } else {
                        while (data.moveToNext()) {
                            String lista = "\nData: " + data.getString(1) + "\nTemperatura: " + data.getString(2) + "\nCommento: " + data.getString(3);
                            theList.add(lista);
                            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                            listView.setAdapter(listAdapter);
                        }
                    }
                } else if (dataimp.getString(1).equals("PressioneSanguignaDB")) {
                    data = helper.getReportsPressioneSanguigna();
                    if (data.getCount() == 0) {
                        Toast.makeText(VisualizzaImportanza.this, "Non sono stati inseriri dati relativi alla pressione sanguigna", Toast.LENGTH_SHORT).show();
                    } else {
                        while (data.moveToNext()) {
                            String lista = "\nData: " + data.getString(1) + "\nPressione minima: " + data.getString(2) + "\nPressione massima: " + data.getString(3) + "\nCommento: " + data.getString(4);
                            theList.add(lista);
                            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                            listView.setAdapter(listAdapter);
                        }
                    }


                } else if (dataimp.getString(1).equals("IndiceGlicemicoDB")) {
                    data = helper.getReportsGlicemia();

                    if (data.getCount() == 0) {
                        Toast.makeText(VisualizzaImportanza.this, "Non sono stati inseriri dati relativi alla glicemia", Toast.LENGTH_SHORT).show();
                    } else {
                        while (data.moveToNext()) {
                            String lista = "\nData: " + data.getString(1) + "\nIndice glicemico: " + data.getString(2) + "\nCommento: " + data.getString(3);
                            theList.add(lista);
                            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                            listView.setAdapter(listAdapter);
                        }
                    }

                } else if (dataimp.getString(1).equals("OreSonnoDB")) {
                    data = helper.getReportsOreSonno();
                    if (data.getCount() == 0) {
                        Toast.makeText(VisualizzaImportanza.this, "Non sono stati inseriri dati relativi alle ore di sonno", Toast.LENGTH_SHORT).show();
                    } else {
                        while (data.moveToNext()) {
                            String lista = "\nData: " + data.getString(1) + "\nOre di sonno: " + data.getString(2) + "\nCommento: " + data.getString(3);
                            theList.add(lista);
                            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                            listView.setAdapter(listAdapter);
                        }
                    }


                } else if (dataimp.getString(1).equals("PesoCorporeoDB")) {
                    data = helper.getReportsPesoCorporeo();


                    if (data.getCount() == 0) {
                        Toast.makeText(VisualizzaImportanza.this, "Non sono stati inseriri dati relativi al peso corporeo", Toast.LENGTH_SHORT).show();
                    } else {
                        while (data.moveToNext()) {
                            String lista = "\nData: " + data.getString(1) + "\nPeso corporeo: " + data.getString(2) + "\nCommento: " + data.getString(3);
                            theList.add(lista);
                            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                            listView.setAdapter(listAdapter);
                        }
                    }


                } else {
                    Toast.makeText(VisualizzaImportanza.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}