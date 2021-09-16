package com.example.progetto_lam;

public class TemperaturaCorporeaManager {
    String data,commento;
    String temperatura;
    int id;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return this.data;
    }

    public void setCommento(String commento){
        this.commento = commento;
    }
    public String getCommento(){
        return this.commento;
    }

    public void setTemperatura(String temperatura){
        this.temperatura = temperatura;
    }
    public String getTemperatura(){
        return this.temperatura;
    }
}
