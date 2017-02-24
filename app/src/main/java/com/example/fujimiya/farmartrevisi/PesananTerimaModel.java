package com.example.fujimiya.farmartrevisi;

/**
 * Created by Glory on 18-Feb-17.
 */

public class PesananTerimaModel {

    String customer;
    String jumlah;
    String komoditas;
    String hargakomoditi;

    public String getKeycustomer() {
        return keycustomer;
    }

    public void setKeycustomer(String keycustomer) {
        this.keycustomer = keycustomer;
    }

    String keycustomer;

    public PesananTerimaModel(String customer, String jumlah, String komoditas, String hargakomoditi, String mode, String tanggal, String total
                            ,String keycustomer) {
        this.customer = customer;
        this.jumlah = jumlah;
        this.komoditas = komoditas;
        this.hargakomoditi = hargakomoditi;
        this.mode = mode;
        this.tanggal = tanggal;
        this.total = total;
        this.keycustomer = keycustomer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getHargakomoditi() {
        return hargakomoditi;
    }

    public void setHargakomoditi(String hargakomoditi) {
        this.hargakomoditi = hargakomoditi;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    String mode;
    String tanggal;
    String total;


}
