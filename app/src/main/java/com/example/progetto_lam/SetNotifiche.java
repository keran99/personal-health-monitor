package com.example.progetto_lam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class SetNotifiche extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private DatabaseHelper helper = new DatabaseHelper(this);
    TextView textViewOrario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notifiche);

        Button button = (Button) findViewById(R.id.BtnOrario);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        textViewOrario = (TextView) findViewById(R.id.textViewOrario);
        Cursor oraNotifica = helper.getOraNotifica();
        if (oraNotifica.getCount()==1){
            while(oraNotifica.moveToNext()) {
                textViewOrario.setText(oraNotifica.getString(0));
            }
        } else {
                Toast.makeText(SetNotifiche.this, "ERRORE: si è verificato un errore durante il caricamento dell'orario di notifica", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeTextDatabese(hourOfDay, minute);
        startNotification(c);


    }



    private void updateTimeTextDatabese (int hourOfDay, int minute) {
        String timeText = hourOfDay +":"+ minute;
        textViewOrario.setText(timeText);

        Boolean resultOraNotifica = helper.setOraNotifica(timeText);
        if (resultOraNotifica==true){
            Toast.makeText(SetNotifiche.this, "L'orario di notifica è stato impostato con successo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SetNotifiche.this, "ERRORE: si è verificato un errore durante l'impostazione dell'orario di notifica", Toast.LENGTH_SHORT).show();
        }
    }

    private void startNotification(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(alarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}
