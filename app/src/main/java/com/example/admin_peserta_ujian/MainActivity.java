package com.example.admin_peserta_ujian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin_peserta_ujian.informasi.Tambah_Informasi;
import com.example.admin_peserta_ujian.instansi.Tambah_instansi;
import com.example.admin_peserta_ujian.penjadwalan.Penjadwalan;
import com.example.admin_peserta_ujian.peserta.Lihat_instansi_Peserta;

public class MainActivity extends AppCompatActivity {
    private Button btn_instansi,btn_informasi,btn_lihatpeserta,btn_penjadwalan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_instansi = findViewById(R.id.btn_tmbh_instansi);
        btn_informasi = findViewById(R.id.btn_tmbh_informasi);
        btn_lihatpeserta = findViewById(R.id.btn_lihat_peserta);
        btn_penjadwalan = findViewById(R.id.btn_penjadwalan);

        btn_instansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Tambah_instansi.class);
                startActivity(intent);
            }
        });
        btn_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( MainActivity.this, Tambah_Informasi.class);
                startActivity(intent);
            }
        });
        btn_lihatpeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( MainActivity.this, Lihat_instansi_Peserta.class);
                startActivity(intent);
            }
        });
        btn_penjadwalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( MainActivity.this, Penjadwalan.class);
                startActivity(intent);
            }
        });
    }
}
