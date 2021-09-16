package com.example.progetto_lam;

public class IndiceGlicemicoManager {
    String dataGlicemia,commentoGlicemia;
    String glicemia;
    int id;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setData(String dataGlicemia){
        this.dataGlicemia = dataGlicemia;
    }
    public String getData(){
        return this.dataGlicemia;
    }

    public void setCommentoGlicemia(String commentoGlicemia){
        this.commentoGlicemia = commentoGlicemia;
    }
    public String getCommentoGlicemia(){
        return this.commentoGlicemia;
    }

    public void setGlicemia(String glicemia){
        this.glicemia = glicemia;
    }
    public String getGlicemia(){
        return this.glicemia;
    }
}
