package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficoTorta extends AppCompatActivity {
    private Spinner spinnerTipoDato, spinnerRange;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Cursor data;
    private PieChart graficoTorta;
    private String tipoDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_torta);

        spinnerTipoDato = findViewById(R.id.spinnertipoDatoTorta);
        ArrayAdapter<CharSequence> adapterTipoDato = ArrayAdapter.createFromResource(this, R.array.tipoDato, android.R.layout.simple_spinner_item);
        adapterTipoDato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDato.setAdapter(adapterTipoDato);

        spinnerRange = findViewById(R.id.spinnerRangeTorta);
        ArrayAdapter<CharSequence> adapterRange = ArrayAdapter.createFromResource(this, R.array.range, android.R.layout.simple_spinner_item);
        adapterRange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRange.setAdapter(adapterRange);

        graficoTorta = findViewById(R.id.graficoTorta);
    }

    public void visualizzaGraficoTorta(View view) {
        tipoDato = spinnerTipoDato.getSelectedItem().toString();

        if (tipoDato.equals("Temperatura corporea")){
            data = helper.getReportsTemperatura();
            compileArrayList1();
        } else if (tipoDato.equals("Pressione sanguigna - massimo")) {
            data = helper.getReportsPressioneSanguigna();
            compileArrayList2();
        } else if (tipoDato.equals("Pressione sanguigna - minimo")){
            data = helper.getReportsPressioneSanguigna();
            compileArrayList1();
        } else if (tipoDato.equals("Indice glicemia")){
            data = helper.getReportsGlicemia();
            compileArrayList1();
        } else if (tipoDato.equals("Ore sonno")){
            data = helper.getReportsOreSonno();
            compileArrayList1();
        } else if (tipoDato.equals("Peso corporeo")){
            data = helper.getReportsPesoCorporeo();
            compileArrayList1();
        } else {
            Toast.makeText(GraficoTorta.this, "Si è verificato un errore", Toast.LENGTH_SHORT).show();
        }
    }

    private void compileArrayList1() {
        ArrayList<PieEntry> graficoTortaInput = new ArrayList<>();
        String range = spinnerRange.getSelectedItem().toString();
        int rangeInt=0;
        int i=0;

        if (range.equals("Ultimi 5 report")){
            rangeInt = 5;
        } else if (range.equals("Ultimi 10 report")){
            rangeInt = 10;
        } else if (range.equals("Ultimi 20 report")){
            rangeInt = 20;
        } else if (range.equals("Ultimi 30 report")){
            rangeInt = 30;
        } else if (range.equals("Tutti i report")){
            rangeInt = data.getCount();
        }

        if (data.getCount()==0){
            Toast.makeText(GraficoTorta.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i=0;
            data.moveToLast();
            do {
                i++;
                graficoTortaInput.add(new PieEntry((float) Double.parseDouble(data.getString(2)), data.getString(1)));
            } while(data.moveToPrevious() && i<rangeInt);

            PieDataSet pieDataSet = new PieDataSet(graficoTortaInput,"grafico a Torta");
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            pieDataSet.setValueTextColor(Color.BLACK);
            pieDataSet.setValueTextSize(16f);

            PieData pieData = new PieData(pieDataSet);

            graficoTorta.setData(pieData);
            graficoTorta.getDescription().setEnabled(false);
            graficoTorta.setCenterText(tipoDato);
            graficoTorta.animateY(2000);
        }
    }


    private void compileArrayList2() {
        ArrayList<PieEntry> graficoTortaInput = new ArrayList<>();
        String range = spinnerRange.getSelectedItem().toString();
        int rangeInt=0;
        int i=0;

        if (range.equals("Ultimi 5 report")){
            rangeInt = 5;
        } else if (range.equals("Ultimi 10 report")){
            rangeInt = 10;
        } else if (range.equals("Ultimi 20 report")){
            rangeInt = 20;
        } else if (range.equals("Ultimi 30 report")){
            rangeInt = 30;
        } else if (range.equals("Tutti i report")){
            rangeInt = data.getCount();
        }

        if (data.getCount()==0){
            Toast.makeText(GraficoTorta.this, "Non sono stati inseriri dati relativi all'elemento selezionato", Toast.LENGTH_SHORT).show();
        } else {
            i=0;
            data.moveToLast();
            do {
                i++;
                graficoTortaInput.add(new PieEntry((float) Double.parseDouble(data.getString(3)), data.getString(1)));
            } while(data.moveToPrevious() && i<rangeInt);

            PieDataSet pieDataSet = new PieDataSet(graficoTortaInput,"grafico a Torta");
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            pieDataSet.setValueTextColor(Color.BLACK);
            pieDataSet.setValueTextSize(16f);

            PieData pieData = new PieData(pieDataSet);

            graficoTorta.setData(pieData);
            graficoTorta.getDescription().setEnabled(false);
            graficoTorta.setCenterText(tipoDato);
            graficoTorta.animateY(2000);
        }
    }


}
