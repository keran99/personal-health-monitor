package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VisualizzaPesoCorporeo extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_peso_corporeo);

        ListView listView = (ListView) findViewById(R.id.ListView);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = helper.getReportsPesoCorporeo();

        if (data.getCount()==0){
            Toast.makeText(VisualizzaPesoCorporeo.this, "Non sono stati inseriri dati relativi al peso corporeo", Toast.LENGTH_SHORT).show();
        } else {
            while(data.moveToNext()){
                String lista = "\nData: " + data.getString(1) + "\nPeso corporeo: " + data.getString(2)+ "\nCommento: " + data.getString(3);
                theList.add(lista);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
