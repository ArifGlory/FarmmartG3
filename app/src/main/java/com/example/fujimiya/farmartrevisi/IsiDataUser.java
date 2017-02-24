package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 1/10/17.
 */

public class IsiDataUser {
    String email;
    String username;
    String password;
    String status;
    String alamat;
    Double lat;
    Double lon;

    public IsiDataUser(String email,String username,String password,String status,String alamat,Double lat,Double lon){
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.alamat = alamat;
        this.lat = lat;
        this.lon = lon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
