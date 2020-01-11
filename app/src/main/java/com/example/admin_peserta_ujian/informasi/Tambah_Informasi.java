package com.example.admin_peserta_ujian.informasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Informasi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambah_Informasi extends AppCompatActivity {

    private EditText judul, deskripsi;
    private Button simpan,lihat;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__informasi);

        judul = findViewById(R.id.judul);
        deskripsi = findViewById(R.id.deskrip);
        simpan = findViewById(R.id.btn_simpan);
        lihat = findViewById(R.id.btn_lihatinformasi);
        database = FirebaseDatabase.getInstance().getReference();

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(Tambah_Informasi.this, Lihat_Informasi.class);
                startActivity(inten);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sjudul = judul.getText().toString();
                String Sdeskripsi = deskripsi.getText().toString();
                if (Sjudul.isEmpty() || Sdeskripsi.isEmpty()){
                    Toast.makeText(Tambah_Informasi.this,"Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    simpan(new Informasi(judul.getText().toString(), deskripsi.getText().toString()));
                }

            }
        });

    }




    private void simpan(Informasi informasi) {
        database.child("Admin Informasi").push().setValue(informasi).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                judul.setText("");
                deskripsi.setText("");
                Snackbar.make(findViewById(R.id.simpan), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}