package com.example.progetto_lam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class VisualizzaGiorno extends AppCompatActivity {

    private static final String TAG = "VisualizzaGiorno";
    DatabaseHelper helper = new DatabaseHelper(this);
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_giorno);


        CalendarView caledarView = (CalendarView) findViewById(R.id.calendarView);
        caledarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ListView listView = (ListView) findViewById(R.id.listViewGiorno);
                ListAdapter listAdapter = null;
                listView.setAdapter(null);
                list.clear();
                month = month + 1;
                String SearchDate = dayOfMonth + "/" + month + "/" + year;
                int i = 0;
                double val0 = 0;
                double val1 = 0;
                Cursor dataTemperatura = helper.getReportsDataTemperatura(SearchDate);
                if (dataTemperatura.getCount() != 0) {
                    i = 0;
                    val0 = 0;
                    while (dataTemperatura.moveToNext()) {
                        i++;
                        val0 = val0 + Double.parseDouble(dataTemperatura.getString(2));
                    }
                    String lista = "\nData: " + SearchDate + "\nTemperatura: " + val0 / i;
                    list.add(lista);
                    listAdapter = new ArrayAdapter<>(VisualizzaGiorno.this, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(listAdapter);
                }

                Cursor dataPressione = helper.getReportsDataPressione(SearchDate);
                if (dataPressione.getCount() != 0) {
                    i = 0;
                    val0 = 0;
                    val1 = 0;
                    while (dataPressione.moveToNext()) {
                        i++;
                        val0 = val0 + Double.parseDouble(dataPressione.getString(2));
                        val1 = val1 + Double.parseDouble(dataPressione.getString(3));
                    }
                    String lista = "\nData: " + SearchDate + "\nPressione minima: " + val0 / i + "\nPressione massima: " + val1 / i;
                    list.add(lista);
                    listAdapter = new ArrayAdapter<>(VisualizzaGiorno.this, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(listAdapter);
                }

                Cursor dataGlicemia = helper.getReportsDataGlicemia(SearchDate);
                if (dataGlicemia.getCount() != 0) {
                    i = 0;
                    val0 = 0;
                    while (dataGlicemia.moveToNext()) {
                        i++;
                        val0 = val0 + Double.parseDouble(dataGlicemia.getString(2));
                    }

                    String lista = "\nData: " + SearchDate + "\nIndiceGlicemico " + val0 / i;
                    list.add(lista);
                    listAdapter = new ArrayAdapter<>(VisualizzaGiorno.this, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(listAdapter);
                }

                Cursor dataSonno = helper.getReportsDataSonno(SearchDate);
                if (dataSonno.getCount() != 0) {
                    i = 0;
                    val0 = 0;
                    while (dataSonno.moveToNext()) {
                        i++;
                        val0 = val0 + Double.parseDouble(dataSonno.getString(2));
                    }
                    String lista = "\nData: " + SearchDate + "\nOre di sonno: " + val0 / i;
                    list.add(lista);
                    listAdapter = new ArrayAdapter<>(VisualizzaGiorno.this, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(listAdapter);
                }

                Cursor dataPeso = helper.getReportsDataPeso(SearchDate);
                if (dataPeso.getCount() != 0) {
                    i = 0;
                    val0 = 0;
                    while (dataPeso.moveToNext()) {
                        i++;
                        val0 = val0 + Double.parseDouble(dataPeso.getString(2));
                    }
                    String lista = "\nData: " + SearchDate + "\nPeso corporeo: " + val0 / i;
                    list.add(lista);
                    listAdapter = new ArrayAdapter<>(VisualizzaGiorno.this, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(listAdapter);
                }

                if (dataTemperatura.getCount() == 0 && dataPressione.getCount() == 0 && dataGlicemia.getCount() == 0 &&
                        dataSonno.getCount() == 0 && dataPeso.getCount() == 0) {
                    Toast.makeText(VisualizzaGiorno.this, "Non sono presenti dati nella giornata selezionata",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
