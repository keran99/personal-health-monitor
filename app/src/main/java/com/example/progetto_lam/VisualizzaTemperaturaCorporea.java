package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VisualizzaTemperaturaCorporea extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_temperatura_corporea);

        ListView listView = (ListView) findViewById(R.id.ListView);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = helper.getReportsTemperatura();

        if (data.getCount()==0){
            Toast.makeText(VisualizzaTemperaturaCorporea.this, "Non sono stati inseriri dati relativi alla temperatura corporea", Toast.LENGTH_SHORT).show();
        } else {
            while(data.moveToNext()){
                String lista = "\nData: " + data.getString(1) + "\nTemperatura: " + data.getString(2)+ "\nCommento: " + data.getString(3);
                theList.add(lista);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
