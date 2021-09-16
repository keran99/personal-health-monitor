package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModificaIndiceGlicemico extends AppCompatActivity {

    private String ItemID;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_indice_glicemico);

        ListView listView = (ListView) findViewById(R.id.listViewModificaGlicemia);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = helper.getReportsGlicemia();

        if (data.getCount()==0){
            Toast.makeText(ModificaIndiceGlicemico.this, "Non sono stati inseriri dati relativi alla glicemia", Toast.LENGTH_SHORT).show();
        } else {
            while(data.moveToNext()){
                String lista = "\nID: " + data.getString(0) + "\nData: " + data.getString(1) + "\nIndice glicemico: " + data.getString(2)+ "\nCommento: " + data.getString(3);
                theList.add(lista);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elementoCliccato = parent.getItemAtPosition(position).toString();
                String[] separated = elementoCliccato.split("\nData");
                String[] separated2 = separated[0].split(": ");
                ItemID = separated2[1].trim();
                Intent editScreenIntent = new Intent(ModificaIndiceGlicemico.this, EffettuaModificaIndiceGlicemia.class);
                editScreenIntent.putExtra("id",ItemID);
                startActivity(editScreenIntent);
            }
        });
    }
}
