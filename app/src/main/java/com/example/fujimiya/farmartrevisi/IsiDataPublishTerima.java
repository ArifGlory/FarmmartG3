package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 1/12/17.
 */

public class IsiDataPublishTerima {

    String gambar;
    String harga;
    String spesifikasi;
    String komoditi;
     public IsiDataPublishTerima(String gambar,String harga,String spesifikasi, String komoditi){
         this.gambar = gambar;
         this.harga = harga;
         this.spesifikasi = spesifikasi;
         this.komoditi = komoditi;
     }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi) {
        this.spesifikasi = spesifikasi;
    }

    public String getKomoditi() {
        return komoditi;
    }

    public void setKomoditi(String komoditi) {
        this.komoditi = komoditi;
    }
}
