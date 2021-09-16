package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficoLinee extends AppCompatActivity {
    private Spinner spinnerTipoDato, spinnerRange;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Cursor data;
    private String tipoDato;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_linee);

        spinnerTipoDato = findViewById(R.id.spinnertipoDatoLinee);
        ArrayAdapter<CharSequence> adapterTipoDato = ArrayAdapter.createFromResource(this, R.array.tipoDato, android.R.layout.simple_spinner_item);
        adapterTipoDato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDato.setAdapter(adapterTipoDato);

        spinnerRange = findViewById(R.id.spinnerRangeLinee);
        ArrayAdapter<CharSequence> adapterRange = ArrayAdapter.createFromResource(this, R.array.range, android.R.layout.simple_spinner_item);
        adapterRange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRange.setAdapter(adapterRange);
        
        lineChart = (LineChart) findViewById(R.id.graficoLinee);
    }

    public void visualizzaGraficoLinee(View view) {
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
            Toast.makeText(GraficoLinee.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }
        
    }

    private void compileArrayList1() {
        ArrayList<Entry> graficoLineeInput = new ArrayList<>();
        String range = spinnerRange.getSelectedItem().toString();
        ArrayList<String> labelsName = new ArrayList<>();
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
            Toast.makeText(GraficoLinee.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i = 0;
            data.moveToLast();
            do {
                i++;
                graficoLineeInput.add(new BarEntry(i, (float) Double.parseDouble(data.getString(2))));
                labelsName.add(data.getString(1));
            } while (data.moveToPrevious() && i < rangeInt);

            LineDataSet lineDataSet = new LineDataSet(graficoLineeInput,"Grafico a linee");
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
            lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            lineDataSet.setCircleRadius(10);
            lineDataSet.setLineWidth(15);
            lineDataSet.setValueTextSize(15);
            //formattiamo x
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsName));
            xAxis.setPosition(XAxis.XAxisPosition.TOP);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawAxisLine(false);
            xAxis.setGranularity(1f);
            xAxis.setLabelCount(labelsName.size());
            xAxis.setLabelRotationAngle(270);
            lineChart.animateY(2000);
            lineChart.invalidate();
        }
    }

    private void compileArrayList2() {
        ArrayList<Entry> graficoLineeInput = new ArrayList<>();
        String range = spinnerRange.getSelectedItem().toString();
        ArrayList<String> labelsName = new ArrayList<>();
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
            Toast.makeText(GraficoLinee.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i = 0;
            data.moveToLast();
            do {
                i++;
                graficoLineeInput.add(new BarEntry(i, (float) Double.parseDouble(data.getString(3))));
                labelsName.add(data.getString(1));
            } while (data.moveToPrevious() && i < rangeInt);

            LineDataSet lineDataSet = new LineDataSet(graficoLineeInput,"Grafico a linee");
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
            lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            lineDataSet.setCircleRadius(10);
            lineDataSet.setLineWidth(15);
            lineDataSet.setValueTextSize(15);

            //formattiamo x
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsName));
            xAxis.setPosition(XAxis.XAxisPosition.TOP);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawAxisLine(false);
            xAxis.setGranularity(1f);
            xAxis.setLabelCount(labelsName.size());
            xAxis.setLabelRotationAngle(270);
            lineChart.animateY(2000);
            lineChart.invalidate();
        }
    }
}
