package com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_kuning;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sabuk_kuning extends AppCompatActivity implements Adapter_sabuk_kuning.FirebaseDataListener {

    private static Activity activity;
    private DatabaseReference database_sabukkuning;
    private RecyclerView recyclerView_sabukkuning;
    private RecyclerView.Adapter adapter_sabukkuning;
    private RecyclerView.LayoutManager layoutManager_sabukkuning;
    private ArrayList<Lihat_Peserta> daftar_sabukkuning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabuk_kuning);

        recyclerView_sabukkuning = findViewById(R.id.Rv_sabuk_kuning);
        recyclerView_sabukkuning.setHasFixedSize(true);
        layoutManager_sabukkuning = new LinearLayoutManager(this);
        recyclerView_sabukkuning.setLayoutManager(layoutManager_sabukkuning);

        database_sabukkuning = FirebaseDatabase.getInstance().getReference();

        database_sabukkuning.child("Daftar Peserta_Sabuk Kuninng").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_sabukkuning = new ArrayList<Lihat_Peserta>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Peserta peserta = dataSnapshot1.getValue(Lihat_Peserta.class);
                    peserta.setKey(dataSnapshot1.getKey());
                    daftar_sabukkuning.add(peserta);

                    adapter_sabukkuning = new Adapter_sabuk_kuning(daftar_sabukkuning, Sabuk_kuning.this);
                    recyclerView_sabukkuning.setAdapter(adapter_sabukkuning);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Sabuk_kuning.activity = activity;
        return new Intent(activity, Sabuk_kuning.class);
    }

    @Override
    public void onDeleteData(Lihat_Peserta lihat_peserta, int position) {
        if (database_sabukkuning != null) {
            database_sabukkuning.child("Daftar Peserta_Sabuk Kuninng").child(lihat_peserta.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Sabuk_kuning.this, "Berhasil Menghapus Data", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
