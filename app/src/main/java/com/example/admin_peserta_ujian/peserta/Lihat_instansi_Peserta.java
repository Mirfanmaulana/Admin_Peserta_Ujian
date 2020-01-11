package com.example.admin_peserta_ujian.peserta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.adapter.Adapter_lihat_instansi_peserta;
import com.example.admin_peserta_ujian.model.Lihat_Instansi_Peserta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lihat_instansi_Peserta extends AppCompatActivity {
    private static Activity activity;
    private DatabaseReference database_instansi;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter_instansi_lihat;
    private RecyclerView.LayoutManager layoutManager_instansi;
    private ArrayList<Lihat_Instansi_Peserta> daftar_instansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_instansi_peserta);

        recyclerView = findViewById(R.id.rv_lihatinstansi_peserta);
        recyclerView.setHasFixedSize(true);
        layoutManager_instansi = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager_instansi);

        database_instansi = FirebaseDatabase.getInstance().getReference();

        database_instansi.child("Admin Instansi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_instansi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Instansi_Peserta instansi_peserta = dataSnapshot1.getValue(Lihat_Instansi_Peserta.class);
                    instansi_peserta.setKey(dataSnapshot1.getKey());
                    daftar_instansi.add(instansi_peserta);

                    adapter_instansi_lihat = new Adapter_lihat_instansi_peserta(daftar_instansi, Lihat_instansi_Peserta.this);
                    recyclerView.setAdapter(adapter_instansi_lihat);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Lihat_instansi_Peserta.activity = activity;
        return new Intent(activity, Lihat_instansi_Peserta.class);

    }

}
