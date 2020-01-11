package com.example.admin_peserta_ujian.model;

public class Jadwal {

    private String tanggal;
    private String waktu;
    private String sabuk;

    public Jadwal() {
    }

    public Jadwal(String tanggal, String waktu, String sabuk) {
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.sabuk = sabuk;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getSabuk() {
        return sabuk;
    }

    public void setSabuk(String sabuk) {
        this.sabuk = sabuk;
    }
}
