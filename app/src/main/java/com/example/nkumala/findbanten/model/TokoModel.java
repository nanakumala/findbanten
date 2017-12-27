package com.example.nkumala.findbanten.model;

import java.io.Serializable;

/**
 * Created by Urip on 11/30/2017.
 */

public class TokoModel implements Serializable{
    private int id_toko;
    private String nama_toko, alamat, no_telp;
    private double lat, lng;
//
//    public TokoModel(int id_toko, String nama_toko, String alamat, String no_telp ) {
//        this.id_toko = id_toko;
//        this.nama_toko = nama_toko;
//        this.no_telp = no_telp;
//        this.alamat = alamat;
//    }

    public int getId() {
        return id_toko;
    }

    public void setId(int id_toko) {
        this.id_toko = id_toko;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
