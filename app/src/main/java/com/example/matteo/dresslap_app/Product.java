package com.example.matteo.dresslap_app;

public class Product {
    private int id;
    private String nome;
    private String colore;
    private String taglia;
    private String prezzo;
    private String camerino;

    public Product(int id, String nome, String colore, String taglia,String prezzo, String camerino){
        this.id = id;
        this.nome = nome;
        this.colore=colore;
        this.prezzo=prezzo;
        this.taglia=taglia;
        this.camerino=camerino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getCamerino() {
        return camerino;
    }

    public void setCamerino(String camerino) {
        this.camerino = camerino;
    }
}
