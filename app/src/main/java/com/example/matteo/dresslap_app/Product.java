package com.example.matteo.dresslap_app;

public class Product {
    private String id;
    private String colore;
    private String taglia;
    private String prezzo;
    private String camerino;

    public Product(String id, String colore, String taglia,String prezzo, String camerino){
        this.id=id;
        this.colore=colore;
        this.prezzo=prezzo;
        this.taglia=taglia;
        this.camerino=camerino;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
