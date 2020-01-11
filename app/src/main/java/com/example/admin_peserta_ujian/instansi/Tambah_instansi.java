package com.example.admin_peserta_ujian.instansi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Instansi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambah_instansi extends AppCompatActivity {
    private  EditText instansi,alamat;
    private Button simpan, lihatdata;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_instansi);

        instansi = findViewById(R.id.instansi);
        alamat = findViewById(R.id.alamat);
        simpan = findViewById(R.id.simpan);
        lihatdata = findViewById(R.id.lihat_data);

        database = FirebaseDatabase.getInstance().getReference();
        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tambah_instansi.this, Lihat_instansi.class);
                startActivity(intent);
            }
        });

        data();
    }

    private void data() {
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sinstansi = instansi.getText().toString();
                String Salamat = alamat.getText().toString();

                if (Sinstansi.isEmpty() || Salamat.isEmpty()){
                    Toast.makeText(Tambah_instansi.this,"Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    simpan(new Instansi(instansi.getText().toString(), alamat.getText().toString()));
                }
            }
        });
    }

    private void simpan(final Instansi instansi) {
        database.child("Admin Instansi").push().setValue(instansi).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                instansi.setInstansi("");
                alamat.setText("");
                Snackbar.make(findViewById(R.id.simpan), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
