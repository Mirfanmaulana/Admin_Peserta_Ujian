package com.example.admin_peserta_ujian.model;

import java.io.Serializable;

public class Instansi implements Serializable {
    private String instansi;
    private String alamat;
    private String key;

    public Instansi(String instansi, String alamat, String key) {
        this.instansi = instansi;
        this.alamat = alamat;
        this.key = key;
    }

    public Instansi(String instansi, String alamat) {
        this.instansi = instansi;
        this.alamat = alamat;
    }

    public Instansi() {
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
