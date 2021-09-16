package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class GraficoRadar extends AppCompatActivity {

    private Spinner spinnerTipoDato, spinnerRange;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Cursor data;
    private RadarChart radarChart;
    private String tipoDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_radar);

        spinnerTipoDato = findViewById(R.id.spinnertipoDatoRadar);
        ArrayAdapter<CharSequence> adapterTipoDato = ArrayAdapter.createFromResource(this, R.array.tipoDato, android.R.layout.simple_spinner_item);
        adapterTipoDato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDato.setAdapter(adapterTipoDato);

        spinnerRange = findViewById(R.id.spinnerRangeRadar);
        ArrayAdapter<CharSequence> adapterRange = ArrayAdapter.createFromResource(this, R.array.range, android.R.layout.simple_spinner_item);
        adapterRange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRange.setAdapter(adapterRange);

        radarChart = findViewById(R.id.graficoRadar);
    }

    public void visualizzaGraficoRadar(View view) {
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
            Toast.makeText(GraficoRadar.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }
    }

    private void compileArrayList1() {
        ArrayList<RadarEntry> radarInput = new ArrayList<>();
        ArrayList<String> lables = new ArrayList<>();
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
            Toast.makeText(GraficoRadar.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i = 0;
            data.moveToLast();
            do {
                radarInput.add(new RadarEntry((float)Double.parseDouble(data.getString(2))));
                lables.add(data.getString(1));
                i++;
            } while (data.moveToPrevious() && i < rangeInt);

            RadarDataSet radarDataSet = new RadarDataSet(radarInput, "grafico radar");
            radarDataSet.setColors(Color.BLUE);
            radarDataSet.setLineWidth(2f);
            radarDataSet.setValueTextColor(Color.BLUE);
            radarDataSet.setValueTextSize(14f);
            RadarData radarData = new RadarData();
            radarData.addDataSet(radarDataSet);
            XAxis xAxis = radarChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(lables));
            radarChart.getDescription().setText("Radar Chart");
            radarChart.setData(radarData);
            radarChart.animateY(2000);
            radarChart.invalidate();
        }
    }

    private void compileArrayList2() {
        ArrayList<RadarEntry> radarInput = new ArrayList<>();
        ArrayList<String> lables = new ArrayList<>();
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
            Toast.makeText(GraficoRadar.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i = 0;
            data.moveToLast();
            do {
                radarInput.add(new RadarEntry((float)Double.parseDouble(data.getString(3))));
                //radarInput.add(new RadarEntry(300));
                lables.add(data.getString(1));
                i++;

            } while (data.moveToPrevious() && i < rangeInt);

            RadarDataSet radarDataSet = new RadarDataSet(radarInput, "grafico radar");
            radarDataSet.setColors(Color.BLUE);
            radarDataSet.setLineWidth(2f);
            radarDataSet.setValueTextColor(Color.BLUE);
            radarDataSet.setValueTextSize(14f);

            RadarData radarData = new RadarData();
            radarData.addDataSet(radarDataSet);

            //String[] lables = {"1","2","3","5", "4"};
            XAxis xAxis = radarChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(lables));

            radarChart.getDescription().setText("Radar Chart");
            radarChart.setData(radarData);
            radarChart.animateY(2000);
            radarChart.invalidate();
        }
    }
}
