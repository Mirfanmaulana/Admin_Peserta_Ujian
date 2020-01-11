package com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_hijau;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_putih;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sabuk_hijau extends AppCompatActivity {

    private static Activity activity;
    private DatabaseReference database_sabukhijau;
    private RecyclerView recyclerView_sabukhijau;
    private RecyclerView.Adapter adapter_sabukhijau;
    private RecyclerView.LayoutManager layoutManager_sabukhijau;
    private ArrayList<Lihat_Peserta> daftar_sabukhijau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabuk_hijau);
        recyclerView_sabukhijau = findViewById(R.id.Rv_sabuk_hijau);
        recyclerView_sabukhijau.setHasFixedSize(true);
        layoutManager_sabukhijau = new LinearLayoutManager(this);
        recyclerView_sabukhijau.setLayoutManager(layoutManager_sabukhijau);

        database_sabukhijau = FirebaseDatabase.getInstance().getReference();

        database_sabukhijau.child("Daftar Peserta_Sabuk Hijau").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_sabukhijau = new ArrayList<Lihat_Peserta>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Peserta peserta = dataSnapshot1.getValue(Lihat_Peserta.class);
                    peserta.setKey(dataSnapshot1.getKey());
                    daftar_sabukhijau.add(peserta);

                    adapter_sabukhijau = new Adapter_sabuk_hijau(daftar_sabukhijau, Sabuk_hijau.this);
                    recyclerView_sabukhijau.setAdapter(adapter_sabukhijau);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Sabuk_hijau.activity = activity;
        return new Intent(activity, Sabuk_hijau.class);
    }



}

