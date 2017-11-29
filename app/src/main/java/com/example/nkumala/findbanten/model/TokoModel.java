package com.example.nkumala.findbanten.model;

import java.io.Serializable;

/**
 * Created by Urip on 11/30/2017.
 */

public class TokoModel implements Serializable{
    private int id;
    private String nama,telepon,alamat;

    public TokoModel(int id, String nama, String telepon, String alamat) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
