package com.example.progetto_lam;

public class PressioneSanguignaManager {
    String dataPressione, pressioneMinima, pressioneMassima, commentoPressione;
    int id;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setData(String dataPressione){
        this.dataPressione = dataPressione;
    }
    public String getData(){
        return this.dataPressione;
    }

    public void setPressioneMinima(String pressioneMinima){
        this.pressioneMinima = pressioneMinima;
    }
    public String getPressioneMinima(){
        return this.pressioneMinima;
    }

    public void setPressioneMassima(String pressioneMassima){
        this.pressioneMassima = pressioneMassima;
    }
    public String getPressioneMassima(){
        return this.pressioneMassima;
    }

    public void setCommentoPressionea(String commentoPressione){
        this.commentoPressione = commentoPressione;
    }
    public String getCommentoPressione(){
        return this.commentoPressione;
    }
}
