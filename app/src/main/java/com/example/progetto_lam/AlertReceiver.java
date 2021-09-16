package com.example.progetto_lam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHelper helper = new DatabaseHelper(context);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("Inserisci i report");
        long count = helper.getCountRows("TemperaturaCorporeaDB") + helper.getCountRows("PressioneSanguignaDB") + helper.getCountRows("IndiceGlicemicoDB") + helper.getCountRows("OreSonnoDB") + helper.getCountRows("PesoCorporeoDB");
        if (count == 0) {
            notificationHelper.getManager().notify(1, nb.build());
        }


        int temperaturaImportanza = helper.getImportanza("TemperaturaCorporeaDB");
        if (temperaturaImportanza > 3) {
            NotificationHelper notificationHelperTemperatura = new NotificationHelper(context);
            NotificationCompat.Builder temperatura = notificationHelperTemperatura.getChannel2Notification("Temperatura");
            float temperaturaAVG = helper.getAVG1("TemperaturaCorporeaDB");
            float temperaturaMin = helper.getSogliaMin("TemperaturaCorporea");
            float temperaturaMax = helper.getSogliaMax("TemperaturaCorporea");
            if ((temperaturaAVG < temperaturaMin || temperaturaAVG > temperaturaMax) && temperaturaAVG != -1 && temperaturaMax != -1 && temperaturaMin != -1) {
                notificationHelperTemperatura.getManager().notify(2, temperatura.build());
            }
        }

        int pressioneSanguignaImportanza = helper.getImportanza("PressioneSanguignaDB");
        if (pressioneSanguignaImportanza > 3) {
            NotificationHelper notificationHelperPressioneMinima = new NotificationHelper(context);
            NotificationCompat.Builder pressioneMinima = notificationHelperPressioneMinima.getChannel2Notification("Pressione minima");
            float pressioneMinimaAVG = helper.getAVG1("PressioneSanguignaDB");
            float pressioneMinimaMin = helper.getSogliaMin("PressioneSanguignaMinima");
            float pressioneMinimaMax = helper.getSogliaMax("PressioneSanguignaMinima");
            if ((pressioneMinimaAVG < pressioneMinimaMin || pressioneMinimaAVG > pressioneMinimaMax) && pressioneMinimaAVG != -1 && pressioneMinimaMax != -1 && pressioneMinimaMin != -1) {
                notificationHelperPressioneMinima.getManager().notify(3, pressioneMinima.build());
            }


            NotificationHelper notificationHelperPressioneMassima = new NotificationHelper(context);
            NotificationCompat.Builder pressioneMassima = notificationHelperPressioneMassima.getChannel2Notification("Pressione massima");
            float pressioneMassimaAVG = helper.getAVG2("PressioneSanguignaDB");
            float pressioneMassimaMin = helper.getSogliaMin("PressioneSanguignaMassima");
            float pressioneMassimaMax = helper.getSogliaMax("PressioneSanguignaMassima");
            if ((pressioneMassimaAVG < pressioneMassimaMin || pressioneMassimaAVG > pressioneMassimaMax) && pressioneMassimaAVG != -1 && pressioneMassimaMin != -1 && pressioneMassimaMax != -1) {
                notificationHelperPressioneMassima.getManager().notify(7, pressioneMassima.build());
            }
        }

        int indiceGlicemicoImportanza = helper.getImportanza("IndiceGlicemicoDB");
        if (indiceGlicemicoImportanza > 3) {
            NotificationHelper notificationHelperIndiceGlicemico = new NotificationHelper(context);
            NotificationCompat.Builder indiceGlicemico = notificationHelperIndiceGlicemico.getChannel2Notification("Indice glicemico");
            float indiceGlicemicoAVG = helper.getAVG1("IndiceGlicemicoDB");
            float indiceGlicemicoMin = helper.getSogliaMin("IndiceGlicemico");
            float indiceGlicemicoMax = helper.getSogliaMax("IndiceGlicemico");
            if ((indiceGlicemicoAVG < indiceGlicemicoMin || indiceGlicemicoAVG > indiceGlicemicoMax) && indiceGlicemicoAVG != -1 && indiceGlicemicoMax != -1 && indiceGlicemicoMin != -1) {
                notificationHelperIndiceGlicemico.getManager().notify(4, indiceGlicemico.build());
            }
        }

        int oreSonnoImportanza = helper.getImportanza("OreSonnoDB");
        if (oreSonnoImportanza > 3) {
            NotificationHelper notificationHelperOreSonno = new NotificationHelper(context);
            NotificationCompat.Builder oreSonno = notificationHelperOreSonno.getChannel2Notification("Ore di sonno");
            float oreSonnoAVG = helper.getAVG1("OreSonnoDB");
            float oreSonnoMin = helper.getSogliaMin("OreSonno");
            float oreSonnoMax = helper.getSogliaMax("OreSonno");
            if ((oreSonnoAVG < oreSonnoMin || oreSonnoAVG > oreSonnoMax) && oreSonnoAVG != -1 && oreSonnoMin != -1 && oreSonnoMax != -1) {
                notificationHelperOreSonno.getManager().notify(5, oreSonno.build());
            }
        }

        int pesoCorporeoImportanza = helper.getImportanza("PesoCorporeoDB");
        if (pesoCorporeoImportanza > 3) {
            NotificationHelper notificationHelperPeso = new NotificationHelper(context);
            NotificationCompat.Builder peso = notificationHelperPeso.getChannel2Notification("Peso corporeo");
            float pesoAVG = helper.getAVG1("PesoCorporeoDB");
            float pesoMin = helper.getSogliaMin("PesoCorporeo");
            float pesoMax = helper.getSogliaMax("PesoCorporeo");
            if ((pesoAVG < pesoMin || pesoAVG > pesoMax) && pesoAVG != -1 && pesoMin != -1 && pesoMax != -1) {
                notificationHelperPeso.getManager().notify(6, peso.build());
            }
        }
    }
}
