package com.example.admin_peserta_ujian.instansi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.adapter.Adapter_instansi;
import com.example.admin_peserta_ujian.model.Instansi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lihat_instansi extends AppCompatActivity {

    private static Activity activity;
    private DatabaseReference database_instansi;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter_instansi;
    private RecyclerView.LayoutManager layoutManager_instansi;
    private ArrayList<Instansi> daftar_instansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_instansi);

        recyclerView = findViewById(R.id.rv_instansi);
        recyclerView.setHasFixedSize(true);
        layoutManager_instansi = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager_instansi);

        database_instansi = FirebaseDatabase.getInstance().getReference();

        database_instansi.child("Admin Instansi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_instansi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Instansi instansi = dataSnapshot1.getValue(Instansi.class);
                    instansi.setKey(dataSnapshot1.getKey());
                    daftar_instansi.add(instansi);

                    adapter_instansi = new Adapter_instansi(daftar_instansi, Lihat_instansi.this);
                    recyclerView.setAdapter(adapter_instansi);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Lihat_instansi.activity = activity;
        return new Intent(activity, Lihat_instansi.class);

    }
}
