package com.example.admin_peserta_ujian.model;

import java.io.Serializable;

public class Lihat_Instansi_Peserta implements Serializable {
     private String instansi;
     private String unit;
     private String sabuk;
     private String nama;
     private String ttl;
     private String alamat;
     private String pekerjaan;
     private String umur;
     private String bb;
     private String key;

    public Lihat_Instansi_Peserta(String key) {
        this.key = key;
    }

    public Lihat_Instansi_Peserta(String instansi, String unit, String sabuk, String nama, String ttl, String alamat, String pekerjaan, String umur, String bb) {
        this.instansi = instansi;
        this.unit = unit;
        this.sabuk = sabuk;
        this.nama = nama;
        this.ttl = ttl;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.umur = umur;
        this.bb = bb;
    }

    public Lihat_Instansi_Peserta() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUnit() {
        return unit;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSabuk() {
        return sabuk;
    }

    public void setSabuk(String sabuk) {
        this.sabuk = sabuk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }
}
