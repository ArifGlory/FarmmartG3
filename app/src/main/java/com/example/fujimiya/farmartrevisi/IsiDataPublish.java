package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 1/11/17.
 */

public class IsiDataPublish {
    String gambar;
    String harga;
    String spesifikasi;
    String kunci;
    String nama;
    String komoditi;

    public IsiDataPublish(String gambar,String harga,String spesifikasi,String kunci,String nama,String komoditi){
        this.gambar = gambar;
        this.harga = harga;
        this.spesifikasi = spesifikasi;
        this.kunci = kunci;
        this.nama = nama;
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

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getKomoditi() {
        return komoditi;
    }

    public void setKomoditi(String komoditi) {
        this.komoditi = komoditi;
    }
}
