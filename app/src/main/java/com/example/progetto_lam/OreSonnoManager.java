package com.example.progetto_lam;

public class OreSonnoManager {
    String dataOreSonno,commentoOreSonno;
    String oreSonno;
    int id;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setDataOreSonno(String dataOreSonno){
        this.dataOreSonno = dataOreSonno;
    }
    public String getDataOreSonno(){
        return this.dataOreSonno;
    }

    public void setCommentoOreSonno(String commentoOreSonno){
        this.commentoOreSonno = commentoOreSonno;
    }
    public String getCommentoOreSonno(){
        return this.commentoOreSonno;
    }

    public void setOreSonno(String oreSonno){
        this.oreSonno = oreSonno;
    }
    public String getOreSonno(){
        return this.oreSonno;
    }
}
