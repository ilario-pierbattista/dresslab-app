package com.example.matteo.dresslap_app;

public class Product {
    private String id;
    private String colore;
    private String taglia;
    private String prezzo;

    public Product(String id, String colore, String taglia,String prezzo){
        this.id=id;
        this.colore=colore;
        this.prezzo=prezzo;
        this.taglia=taglia;
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
}
