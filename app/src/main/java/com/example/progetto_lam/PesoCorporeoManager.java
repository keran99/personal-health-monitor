package com.example.progetto_lam;

public class PesoCorporeoManager {
    String dataPeso,commentoPeso;
    String pesoCorporeo;
    int id;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setDataPeso(String dataPeso){
        this.dataPeso = dataPeso;
    }
    public String getDataPeso(){
        return this.dataPeso;
    }

    public void setCommentoPeso(String commentoPeso){
        this.commentoPeso = commentoPeso;
    }
    public String getCommentoPeso(){
        return this.commentoPeso;
    }

    public void setPesoCorporeo(String pesoCorporeo){
        this.pesoCorporeo = pesoCorporeo;
    }
    public String getPesoCorporeo(){
        return this.pesoCorporeo;
    }
}
