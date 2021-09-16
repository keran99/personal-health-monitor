package com.example.progetto_lam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;
import android.util.Log;

import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PHM.db";
    private static final String TABLE_NAME_TEMPERATURA = "TemperaturaCorporeaDB";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_TEMPERATURA = "temperatura";
    private static final String COLUMN_COMMENTO = "commento";

    private static final String TABLE_NAME_PRESSIONE = "PressioneSanguignaDB";
    private static final String COLUMN_PRESSIONE_MINIMA = "pressioneMinima";
    private static final String COLUMN_PRESSIONE_MASSIMA = "pressioneMassima";

    private static final String TABLE_NAME_GLICEMIA = "IndiceGlicemicoDB";
    private static final String COLUMN_GLICEMIA = "glicemia";

    private static final String TABLE_NAME_ORE_SONNO = "OreSonnoDB";
    private static final String COLUMN_ORE_SONNO = "oreSonno";

    private static final String TABLE_NAME_PESO = "PesoCorporeoDB";
    private static final String COLUMN_PESO_CORPOREO = "pesoCorporeo";

    private static final String TABLE_NAME_IMPORTANZA = "ImportanzaDB";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_IMPORTANZA = "importanza";

    private static final String TABLE_NAME_SOGLIA = "SogliaDB";
    private static final String COLUMN_MIN = "min";
    private static final String COLUMN_MAX = "max";

    private static final String TABLE_NAME_SINGLE_DATA = "SingleDataDB";
    private static final String COLUMN_CHIAVE = "chiave";
    private static final String COLUMN_VALORE = "valore";

    SQLiteDatabase db;
    private static final String TABLE_CREATE_TEMPERATURA = "create table " + TABLE_NAME_TEMPERATURA + " (id integer primary key autoincrement not null  , data date not null, temperatura text not null, commento text)";
    private static final String TABLE_CREATE_PRESSIONE = "create table " + TABLE_NAME_PRESSIONE + " (id integer primary key autoincrement not null, data date not null, pressioneMinima text not null, pressioneMassima text not null, commento text)";
    private static final String TABLE_CREATE_GLICEMIA = "create table " + TABLE_NAME_GLICEMIA + "(id integer primary key autoincrement not null, data date not null, glicemia text not null, commento text)";
    private static final String TABLE_CREATE_ORE_SONNO = "create table " + TABLE_NAME_ORE_SONNO + "(id integer primary key autoincrement not null, data date not null, oreSonno text not null, commento text)";
    private static final String TABLE_CREATE_PESO = "create table " + TABLE_NAME_PESO + "(id integer primary key autoincrement not null, data date not null, pesoCorporeo text not null, commento text)";
    private static final String TABLE_CREATE_IMPORTANZA = "create table " + TABLE_NAME_IMPORTANZA + "(id integer primary key autoincrement not null, tipo text not null, importanza integer not null)";
    private static final String TABLE_CREATE_SOGLIA = "create table " + TABLE_NAME_SOGLIA + "(id integer primary key autoincrement not null,tipo text not null, min double not null, max double not null)";
    private static final String TABLE_CREATE_SINGLE_DATA = "create table " + TABLE_NAME_SINGLE_DATA + "(id integer primary key autoincrement not null, chiave text not null, valore text not null)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_TEMPERATURA);
        db.execSQL(TABLE_CREATE_PRESSIONE);
        db.execSQL(TABLE_CREATE_GLICEMIA);
        db.execSQL(TABLE_CREATE_ORE_SONNO);
        db.execSQL(TABLE_CREATE_PESO);
        db.execSQL(TABLE_CREATE_IMPORTANZA);
        db.execSQL(TABLE_CREATE_SOGLIA);
        db.execSQL(TABLE_CREATE_SINGLE_DATA);
        String inserimentoImportanza = "REPLACE INTO " + TABLE_NAME_IMPORTANZA +
                " VALUES (1,'" + TABLE_NAME_TEMPERATURA + "',1), " +
                "(2,'" + TABLE_NAME_PRESSIONE + "',1), " +
                "(3,'" + TABLE_NAME_GLICEMIA + "',3), " +
                "(4,'" + TABLE_NAME_ORE_SONNO + "',4), " +
                "(5,'" + TABLE_NAME_PESO + "',5)";
        db.execSQL(inserimentoImportanza);

        String inserimentoSoglieDefault = "REPLACE INTO " + TABLE_NAME_SOGLIA +
                " VALUES(1,'TemperaturaCorporea', 36, 37)," +
                "(2, 'PressioneSanguignaMinima', 76, 90)," +
                "(3, 'PressioneSanguignaMassima', 115, 140)," +
                "(4, 'IndiceGlicemico', 60, 110), " +
                "(5, 'OreSonno', 6, 10), " +
                "(6, 'PesoCorporeo', 60, 75)";
        db.execSQL(inserimentoSoglieDefault);

        String singleDataDefault = "REPLACE INTO " + TABLE_NAME_SINGLE_DATA +
                " VALUES (1, 'periodo', 'Ultimi 10 report'), " +
                "(2, 'oraNotifica', '15:00')";
        db.execSQL(singleDataDefault);
        this.db = db;
    }

    public Boolean insertTemperatura(TemperaturaCorporeaManager TCM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, TCM.getData());
        values.put(COLUMN_TEMPERATURA, TCM.getTemperatura());
        values.put(COLUMN_COMMENTO, TCM.getCommento());

        long result = db.insert(TABLE_NAME_TEMPERATURA, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertPressione(PressioneSanguignaManager PSM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, PSM.getData());
        values.put(COLUMN_PRESSIONE_MINIMA, PSM.getPressioneMinima());
        values.put(COLUMN_PRESSIONE_MASSIMA, PSM.getPressioneMassima());
        values.put(COLUMN_COMMENTO, PSM.getCommentoPressione());

        long result = db.insert(TABLE_NAME_PRESSIONE, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertGlicemia(IndiceGlicemicoManager IGM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, IGM.getData());
        values.put(COLUMN_GLICEMIA, IGM.getGlicemia());
        values.put(COLUMN_COMMENTO, IGM.getCommentoGlicemia());

        long result = db.insert(TABLE_NAME_GLICEMIA, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertOreSonno(OreSonnoManager OSM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, OSM.getDataOreSonno());
        values.put(COLUMN_ORE_SONNO, OSM.getOreSonno());
        values.put(COLUMN_COMMENTO, OSM.getCommentoOreSonno());
        long result = db.insert(TABLE_NAME_ORE_SONNO, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertPesoCorporeo(PesoCorporeoManager PCM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, PCM.getDataPeso());
        values.put(COLUMN_PESO_CORPOREO, PCM.getPesoCorporeo());
        values.put(COLUMN_COMMENTO, PCM.getCommentoPeso());
        long result = db.insert(TABLE_NAME_PESO, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getReportsTemperatura() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_TEMPERATURA, null);
        return data;
    }

    public Cursor getReportsPressioneSanguigna() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_PRESSIONE, null);
        return data;
    }

    public Cursor getReportsGlicemia() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_GLICEMIA, null);
        return data;
    }

    public Cursor getReportsOreSonno() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_ORE_SONNO, null);
        return data;
    }

    public Cursor getReportsPesoCorporeo() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_PESO, null);
        return data;
    }

    public Cursor getReportsDataTemperatura(String searchDate) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_TEMPERATURA + " WHERE " + COLUMN_DATA + " = '" + searchDate + "'", null);
        return data;
    }

    public Cursor getReportsDataPressione(String searchDate) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_PRESSIONE + " WHERE " + COLUMN_DATA + " = '" + searchDate + "'", null);
        return data;
    }

    public Cursor getReportsDataGlicemia(String searchDate) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_GLICEMIA + " WHERE " + COLUMN_DATA + " = '" + searchDate + "'", null);
        return data;
    }

    public Cursor getReportsDataSonno(String searchDate) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_ORE_SONNO + " WHERE " + COLUMN_DATA + " = '" + searchDate + "'", null);
        return data;
    }

    public Cursor getReportsDataPeso(String searchDate) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_PESO + " WHERE " + COLUMN_DATA + " = '" + searchDate + "'", null);
        return data;
    }

    public Cursor getTemperaturaID(int id) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_TEMPERATURA + " WHERE " + COLUMN_ID + " = " + id, null);
        return data;
    }

    public boolean modificaTemperatura(TemperaturaCorporeaManager TCM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, TCM.getId());
        values.put(COLUMN_DATA, TCM.getData());
        values.put(COLUMN_TEMPERATURA, TCM.getTemperatura());
        values.put(COLUMN_COMMENTO, TCM.getCommento());

        long result = db.update(TABLE_NAME_TEMPERATURA, values, "id=" + TCM.getId(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getPressioneID(int id) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_PRESSIONE + " WHERE " + COLUMN_ID + " = " + id, null);
        return data;
    }

    public boolean modificaPressione(PressioneSanguignaManager PSM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, PSM.getId());
        values.put(COLUMN_DATA, PSM.getData());
        values.put(COLUMN_PRESSIONE_MINIMA, PSM.getPressioneMinima());
        values.put(COLUMN_PRESSIONE_MASSIMA, PSM.getPressioneMassima());
        values.put(COLUMN_COMMENTO, PSM.getCommentoPressione());

        long result = db.update(TABLE_NAME_PRESSIONE, values, "id=" + PSM.getId(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getGlicemiaID(int id) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_GLICEMIA + " WHERE " + COLUMN_ID + " = " + id, null);
        return data;
    }

    public boolean modificaGlicemia(IndiceGlicemicoManager IGM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, IGM.getId());
        values.put(COLUMN_DATA, IGM.getData());
        values.put(COLUMN_GLICEMIA, IGM.getGlicemia());
        values.put(COLUMN_COMMENTO, IGM.getCommentoGlicemia());

        long result = db.update(TABLE_NAME_GLICEMIA, values, "id=" + IGM.getId(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getOreSonnoID(int id) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_ORE_SONNO + " WHERE " + COLUMN_ID + " = " + id, null);
        return data;
    }

    public boolean modificaOreSonno(OreSonnoManager OSM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, OSM.getId());
        values.put(COLUMN_DATA, OSM.getDataOreSonno());
        values.put(COLUMN_ORE_SONNO, OSM.getOreSonno());
        values.put(COLUMN_COMMENTO, OSM.getCommentoOreSonno());

        long result = db.update(TABLE_NAME_ORE_SONNO, values, "id=" + OSM.getId(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getPesoID(int id) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_PESO + " WHERE " + COLUMN_ID + " = " + id, null);
        return data;
    }

    public boolean modificaPeso(PesoCorporeoManager PCM) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, PCM.getId());
        values.put(COLUMN_DATA, PCM.getDataPeso());
        values.put(COLUMN_PESO_CORPOREO, PCM.getPesoCorporeo());
        values.put(COLUMN_COMMENTO, PCM.getCommentoPeso());

        long result = db.update(TABLE_NAME_PESO, values, "id=" + PCM.getId(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean eliminaTemperatura(int id) {
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_TEMPERATURA, COLUMN_ID + " = " + id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean eliminaPressione(int id) {
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_PRESSIONE, COLUMN_ID + " = " + id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean eliminaGlicemia(int id) {
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_GLICEMIA, COLUMN_ID + " = " + id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean eliminaOreSonno(int id) {
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_ORE_SONNO, COLUMN_ID + " = " + id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean eliminaPeso(int id) {
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_PESO, COLUMN_ID + " = " + id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getTableNameByImportance(String imp) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME_IMPORTANZA + " WHERE " + COLUMN_IMPORTANZA + " = " + imp, null);
        return data;
    }

    public Cursor getImportanceByTipoName(String tipoName) {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_IMPORTANZA + " FROM " + TABLE_NAME_IMPORTANZA + " WHERE " + COLUMN_TIPO + " = " + tipoName, null);
        return data;
    }

    public Boolean setImportanzaTemperatura(int importanzaTemperatura) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMPORTANZA, importanzaTemperatura);
        long result = db.update(TABLE_NAME_IMPORTANZA, values, COLUMN_TIPO + " = '" + TABLE_NAME_TEMPERATURA + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setImportanzaPressione(int importanzaPressione) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMPORTANZA, importanzaPressione);
        long result = db.update(TABLE_NAME_IMPORTANZA, values, COLUMN_TIPO + " = '" + TABLE_NAME_PRESSIONE + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setImportanzaGlicemia(int importanzaGlicemia) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMPORTANZA, importanzaGlicemia);
        long result = db.update(TABLE_NAME_IMPORTANZA, values, COLUMN_TIPO + " = '" + TABLE_NAME_GLICEMIA + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setImportanzaOreSonno(int importanzaOreSonno) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMPORTANZA, importanzaOreSonno);
        long result = db.update(TABLE_NAME_IMPORTANZA, values, COLUMN_TIPO + " = '" + TABLE_NAME_ORE_SONNO + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setImportanzaPeso(int importanzaPeso) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMPORTANZA, importanzaPeso);
        long result = db.update(TABLE_NAME_IMPORTANZA, values, COLUMN_TIPO + " = '" + TABLE_NAME_PESO + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getsogliaTemperatura() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_MIN + "," + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = 'TemperaturaCorporea'", null);
        return data;
    }

    public Cursor getsogliaPressioneMassima() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_MIN + "," + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = 'PressioneSanguignaMassima'", null);
        return data;
    }

    public Cursor getsogliaPressioneMinima() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_MIN + "," + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = 'PressioneSanguignaMinima'", null);
        return data;
    }

    public Cursor getsogliaIndiceGlicemico() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_MIN + "," + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = 'IndiceGlicemico'", null);
        return data;
    }

    public Cursor getsogliaOreSonno() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_MIN + "," + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = 'OreSonno'", null);
        return data;
    }

    public Cursor getsogliaPeso() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_MIN + "," + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = 'PesoCorporeo'", null);
        return data;
    }


    public Boolean setSogliaTemperatura(double temperaturaMax, double temperaturaMin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAX, temperaturaMax);
        values.put(COLUMN_MIN, temperaturaMin);
        long result = db.update(TABLE_NAME_SOGLIA, values, COLUMN_TIPO + " = 'TemperaturaCorporea'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setSogliaPressioneMassima(double pressioneMassimaMax, double pressioneMassimaMin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAX, pressioneMassimaMax);
        values.put(COLUMN_MIN, pressioneMassimaMin);
        long result = db.update(TABLE_NAME_SOGLIA, values, COLUMN_TIPO + " = 'PressioneSanguignaMassima'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setSogliaPressioneMinima(double pressioneMinimaMax, double pressioneMinimaMin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAX, pressioneMinimaMax);
        values.put(COLUMN_MIN, pressioneMinimaMin);
        long result = db.update(TABLE_NAME_SOGLIA, values, COLUMN_TIPO + " = 'PressioneSanguignaMinima'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Boolean setSogliaIndiceGlicemico(double glicemiaMax, double glicemiaMin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAX, glicemiaMax);
        values.put(COLUMN_MIN, glicemiaMin);
        long result = db.update(TABLE_NAME_SOGLIA, values, COLUMN_TIPO + " = 'IndiceGlicemico'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setSogliaOreSonno(double oreSonnoMax, double oreSonnoMin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAX, oreSonnoMax);
        values.put(COLUMN_MIN, oreSonnoMin);
        long result = db.update(TABLE_NAME_SOGLIA, values, COLUMN_TIPO + " = 'OreSonno'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean setSogliaPeso(double pesoMax, double pesoMin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAX, pesoMax);
        values.put(COLUMN_MIN, pesoMin);
        long result = db.update(TABLE_NAME_SOGLIA, values, COLUMN_TIPO + " = 'PesoCorporeo'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getSogliaPeriodo() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_VALORE + " FROM " + TABLE_NAME_SINGLE_DATA + " WHERE " + COLUMN_CHIAVE + " = 'periodo'", null);
        return data;
    }

    public Boolean setSogliaPeriodo(String periodo) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VALORE, periodo);
        long result = db.update(TABLE_NAME_SINGLE_DATA, values, COLUMN_CHIAVE + " = 'periodo'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getOraNotifica() {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_VALORE + " FROM " + TABLE_NAME_SINGLE_DATA + " WHERE " + COLUMN_CHIAVE + " = 'oraNotifica'", null);
        return data;
    }

    public Boolean setOraNotifica(String ora) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VALORE, ora);
        long result = db.update(TABLE_NAME_SINGLE_DATA, values,
                COLUMN_CHIAVE + " = 'oraNotifica'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public long getCountRows(String tableName) {
        db = this.getWritableDatabase();
        Calendar calendar = Calendar.getInstance();
        String today = new SimpleDateFormat("d/M/yyyy", Locale.getDefault()).format(new Date());
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE " + COLUMN_DATA + " = '" + today + "'", null);
        long result = cursor.getCount();
        return result;
    }

    public float getAVG1(String tableName) {
        db = this.getWritableDatabase();
        int rangeInt = 0;
        int i = 1;
        float media;

        Cursor cursor1 = db.rawQuery("SELECT " + COLUMN_VALORE + " FROM " + TABLE_NAME_SINGLE_DATA + " WHERE " + COLUMN_CHIAVE + " = 'periodo'", null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM " + tableName, null);

        if (cursor1.getCount() == 1) {
            cursor1.moveToNext();
            String range = cursor1.getString(0);

            if (range.equals("Ultimi 5 report")) {
                rangeInt = 5;
            } else if (range.equals("Ultimi 10 report")) {
                rangeInt = 10;
            } else if (range.equals("Ultimi 20 report")) {
                rangeInt = 20;
            } else if (range.equals("Ultimi 30 report")) {
                rangeInt = 30;
            } else if (range.equals("Tutti i report")) {
                rangeInt = cursor2.getCount();
            }
            if (cursor2.getCount() > 0) {
                i = 0;
                media = 0;
                cursor2.moveToLast();
                do {
                    i++;
                    media = (float) (media + Double.parseDouble(cursor2.getString(2)));
                } while (cursor2.moveToPrevious() && i < rangeInt);
                media = media / i;
                return media;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public float getAVG2(String tableName) {
        db = this.getWritableDatabase();
        int rangeInt = 0;
        int i = 1;
        float media;

        Cursor cursor1 = db.rawQuery("SELECT " + COLUMN_VALORE + " FROM " + TABLE_NAME_SINGLE_DATA + " WHERE " + COLUMN_CHIAVE + " = 'periodo'", null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM " + tableName, null);

        if (cursor1.getCount() == 1) {
            cursor1.moveToNext();
            String range = cursor1.getString(0);

            if (range.equals("Ultimi 5 report")) {
                rangeInt = 5;
            } else if (range.equals("Ultimi 10 report")) {
                rangeInt = 10;
            } else if (range.equals("Ultimi 20 report")) {
                rangeInt = 20;
            } else if (range.equals("Ultimi 30 report")) {
                rangeInt = 30;
            } else if (range.equals("Tutti i report")) {
                rangeInt = cursor2.getCount();
            }
            if (cursor2.getCount() > 0) {
                i = 0;
                media = 0;
                cursor2.moveToLast();
                do {
                    i++;
                    media = (float) (media + Double.parseDouble(cursor2.getString(3)));
                } while (cursor2.moveToPrevious() && i < rangeInt);
                media = media / i;
                return media;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public float getSogliaMin(String tipoDato) {
        db = this.getWritableDatabase();
        float result = -1;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MIN + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = '" + tipoDato + "'", null);
        if (cursor.getCount() == 1) {
            cursor.moveToNext();
            result = Float.parseFloat(cursor.getString(0));
        }
        return result;
    }

    public float getSogliaMax(String tipoDato) {
        db = this.getWritableDatabase();
        float result = -1;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MAX + " FROM " + TABLE_NAME_SOGLIA + " WHERE " + COLUMN_TIPO + " = '" + tipoDato + "'", null);
        if (cursor.getCount() == 1) {
            cursor.moveToNext();
            result = Float.parseFloat(cursor.getString(0));
        }
        return result;
    }


    public int getImportanza(String tipoDato) {
        db = this.getWritableDatabase();
        int result = -1;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_IMPORTANZA + " FROM " + TABLE_NAME_IMPORTANZA + " WHERE " + COLUMN_TIPO + " = '" + tipoDato + "'", null);
        if (cursor.getCount() == 1) {
            cursor.moveToNext();
            result = cursor.getInt(0);
        }
        return result;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TEMPERATURA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRESSIONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GLICEMIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORE_SONNO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PESO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_IMPORTANZA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SOGLIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SINGLE_DATA);
        this.onCreate(db);
    }


}
