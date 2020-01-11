package com.example.admin_peserta_ujian.penjadwalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_biru;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_hijau;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_kuning;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_merah;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_putih;

public class Penjadwalan extends AppCompatActivity {
    private Button sabuk_putih,sabuk_kuning,sabuk_hijau,sabuk_biru,sabuk_merah, tambahJadwal;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwalan);



        sabuk_putih = findViewById(R.id.btn_sabuk_putih);
        sabuk_kuning = findViewById(R.id.btn_sabuk_kuning);
        sabuk_hijau = findViewById(R.id.btn_sabuk_hijau);
        sabuk_biru = findViewById(R.id.btn_sabuk_biru);
        sabuk_merah = findViewById(R.id.btn_sabuk_merah);
        tambahJadwal = findViewById(R.id.btn_tambah_jadwal);

        tambahJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Penjadwalan.this, TambahJadwal.class);
                startActivity(intent);
            }
        });

        sabuk_putih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penjadwalan.this, Sabuk_putih.class);
                startActivity(intent);
            }
        });

        sabuk_kuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penjadwalan.this, Sabuk_kuning.class);
                startActivity(intent);
            }
        });

        sabuk_hijau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penjadwalan.this, Sabuk_hijau.class);
                startActivity(intent);
            }
        });

        sabuk_biru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penjadwalan.this, Sabuk_biru.class);
                startActivity(intent);
            }
        });

        sabuk_merah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penjadwalan.this, Sabuk_merah.class);
                startActivity(intent);
            }
        });
    }
}
