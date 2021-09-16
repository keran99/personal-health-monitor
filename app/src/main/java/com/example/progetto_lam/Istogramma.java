package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Istogramma extends AppCompatActivity {
    private Spinner spinnerTipoDato, spinnerRange;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Cursor data;
    private BarChart istogramma;
    private String tipoDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istogramma);

        spinnerTipoDato = findViewById(R.id.spinnertipoDato);
        ArrayAdapter<CharSequence> adapterTipoDato = ArrayAdapter.createFromResource(this, R.array.tipoDato, android.R.layout.simple_spinner_item);
        adapterTipoDato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDato.setAdapter(adapterTipoDato);

        spinnerRange = findViewById(R.id.spinnerRange);
        ArrayAdapter<CharSequence> adapterRange = ArrayAdapter.createFromResource(this, R.array.range, android.R.layout.simple_spinner_item);
        adapterRange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRange.setAdapter(adapterRange);

        istogramma = findViewById(R.id.istogramma);

    }

    public void visualizzaIstogramma(View view) {
        tipoDato = spinnerTipoDato.getSelectedItem().toString();

        if (tipoDato.equals("Temperatura corporea")) {
            data = helper.getReportsTemperatura();
            compileArrayList1();
        } else if (tipoDato.equals("Pressione sanguigna - massimo")) {
            data = helper.getReportsPressioneSanguigna();
            compileArrayList2();
        } else if (tipoDato.equals("Pressione sanguigna - minimo")) {
            data = helper.getReportsPressioneSanguigna();
            compileArrayList1();
        } else if (tipoDato.equals("Indice glicemia")) {
            data = helper.getReportsGlicemia();
            compileArrayList1();
        } else if (tipoDato.equals("Ore sonno")) {
            data = helper.getReportsOreSonno();
            compileArrayList1();
        } else if (tipoDato.equals("Peso corporeo")) {
            data = helper.getReportsPesoCorporeo();
            compileArrayList1();
        } else {
            Toast.makeText(Istogramma.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }
    }

    private void compileArrayList1() {
        ArrayList<BarEntry> istogrammaInput = new ArrayList<>();
        String range = spinnerRange.getSelectedItem().toString();
        int rangeInt = 0;
        int i = 0;

        if (range.equals("Ultimi 5 report")) {
            rangeInt = 5;
        } else if (range.equals("Ultimi 10 report")) {
            rangeInt = 10;
        } else if (range.equals("Ultimi 20 report")) {
            rangeInt = 20;
        } else if (range.equals("Ultimi 30 report")) {
            rangeInt = 30;
        } else if (range.equals("Tutti i report")) {
            rangeInt = data.getCount();
        }

        if (data.getCount() == 0) {
            Toast.makeText(Istogramma.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i = 0;
            data.moveToLast();
            do {
                i++;
                istogrammaInput.add(new BarEntry(i, (float) Double.parseDouble(data.getString(2))));
            } while (data.moveToPrevious() && i < rangeInt);

            BarDataSet barDataSet = new BarDataSet(istogrammaInput, "istogramma");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);
            BarData barData = new BarData(barDataSet);
            istogramma.setFitBars(true);
            istogramma.setData(barData);
            istogramma.getDescription().setText(tipoDato);
            istogramma.animateY(2000);
        }
    }

    private void compileArrayList2() {
        ArrayList<BarEntry> istogrammaInput = new ArrayList<>();
        String range = spinnerRange.getSelectedItem().toString();
        int rangeInt = 0;
        int i = 0;

        if (range.equals("Ultimi 5 report")) {
            rangeInt = 5;
        } else if (range.equals("Ultimi 10 report")) {
            rangeInt = 10;
        } else if (range.equals("Ultimi 20 report")) {
            rangeInt = 20;
        } else if (range.equals("Ultimi 30 report")) {
            rangeInt = 30;
        } else if (range.equals("Tutti i report")) {
            rangeInt = data.getCount();
        }

        if (data.getCount() == 0) {
            Toast.makeText(Istogramma.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i = 0;
            data.moveToLast();
            do {
                i++;
                istogrammaInput.add(new BarEntry(i, (float) Double.parseDouble(data.getString(3))));
            } while (data.moveToPrevious() && i < rangeInt);

            BarDataSet barDataSet = new BarDataSet(istogrammaInput, "istogramma");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);
            BarData barData = new BarData(barDataSet);
            istogramma.setFitBars(true);
            istogramma.setData(barData);
            istogramma.getDescription().setText("Istogramma");
            istogramma.animateY(2000);
        }
    }
}
