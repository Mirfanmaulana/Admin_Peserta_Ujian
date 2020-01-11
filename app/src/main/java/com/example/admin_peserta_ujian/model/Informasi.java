package com.example.admin_peserta_ujian.model;

import java.io.Serializable;

public class Informasi implements Serializable {
    private String judul;
    private String Deskripsi;
    private String key;

    public Informasi(String judul, String deskripsi, String key) {
        this.judul = judul;
        Deskripsi = deskripsi;
        this.key = key;
    }
    
    public Informasi(String judul, String deskripsi) {
        this.judul = judul;
        Deskripsi = deskripsi;
    }

    public Informasi() {
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
