package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 2/10/17.
 */

public class BeliModel {

    String timestamp;
    String komoditi;
    String harga;
    String jumlah;
    String total;
    String modebayar;
    String gambar;
    String costomer;
    String key;

    public BeliModel(String timestamp, String komoditi, String harga, String jumlah, String total, String modebayar, String gambar, String costomer, String key)
    {
        this.timestamp = timestamp;
        this.komoditi = komoditi;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = total;
        this.modebayar = modebayar;
        this.gambar = gambar;
        this.costomer = costomer;
        this.key = key;


    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getKomoditi() {
        return komoditi;
    }

    public void setKomoditi(String komoditi) {
        this.komoditi = komoditi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getModebayar() {
        return modebayar;
    }

    public void setModebayar(String modebayar) {
        this.modebayar = modebayar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCostomer() {
        return costomer;
    }

    public void setCostomer(String costomer) {
        this.costomer = costomer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
