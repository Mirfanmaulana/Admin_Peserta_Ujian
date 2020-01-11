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
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_putih;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sabuk_putih extends  AppCompatActivity implements Adapter_sabuk_putih.FirebaseDataListener{
    private static Activity activity;
    private DatabaseReference database_sabukputih;
    private RecyclerView recyclerView_sabukputih;
    private RecyclerView.Adapter adapter_sabukputih;
    private RecyclerView.LayoutManager layoutManager_sabukputih;
    private ArrayList<Lihat_Peserta> daftar_sabukputih;
    private String keyInstansi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabuk_putih);



        recyclerView_sabukputih = findViewById(R.id.Rv_sabuk_putih);
        recyclerView_sabukputih.setHasFixedSize(true);
        layoutManager_sabukputih = new LinearLayoutManager(this);
        recyclerView_sabukputih.setLayoutManager(layoutManager_sabukputih);

        database_sabukputih = FirebaseDatabase.getInstance().getReference();

        database_sabukputih.child("Daftar Peserta_Sabuk Putih").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_sabukputih = new ArrayList<Lihat_Peserta>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Peserta peserta = dataSnapshot1.getValue(Lihat_Peserta.class);
                    peserta.setKey(dataSnapshot1.getKey());
                    daftar_sabukputih.add(peserta);

                    adapter_sabukputih = new Adapter_sabuk_putih(daftar_sabukputih, Sabuk_putih.this);
                    recyclerView_sabukputih.setAdapter(adapter_sabukputih);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Sabuk_putih.activity = activity;
        return new Intent(activity, Sabuk_putih.class);
    }


    @Override
    public void onDeleteData(Lihat_Peserta lihat_peserta, int position) {
        if (database_sabukputih != null) {
            database_sabukputih.child("Daftar Peserta_Sabuk Putih").child(lihat_peserta.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Sabuk_putih.this, "Berhasil Menghapus Data", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
