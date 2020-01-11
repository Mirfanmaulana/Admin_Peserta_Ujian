package com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_merah;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_putih;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sabuk_merah extends AppCompatActivity {

    private static Activity activity;
    private DatabaseReference database_sabukmerah;
    private RecyclerView recyclerView_sabukmerah;
    private RecyclerView.Adapter adapter_sabukmerah;
    private RecyclerView.LayoutManager layoutManager_sabukmerah;
    private ArrayList<Lihat_Peserta> daftar_sabukmerah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabuk_merah);

        recyclerView_sabukmerah = findViewById(R.id.Rv_sabuk_merah);
        recyclerView_sabukmerah.setHasFixedSize(true);
        layoutManager_sabukmerah = new LinearLayoutManager(this);
        recyclerView_sabukmerah.setLayoutManager(layoutManager_sabukmerah);

        database_sabukmerah = FirebaseDatabase.getInstance().getReference();

        database_sabukmerah.child("Daftar Peserta_Sabuk Merah").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_sabukmerah = new ArrayList<Lihat_Peserta>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Peserta peserta = dataSnapshot1.getValue(Lihat_Peserta.class);
                    peserta.setKey(dataSnapshot1.getKey());
                    daftar_sabukmerah.add(peserta);

                    adapter_sabukmerah = new Adapter_sabuk_merah(daftar_sabukmerah, Sabuk_merah.this);
                    recyclerView_sabukmerah.setAdapter(adapter_sabukmerah);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Sabuk_merah.activity = activity;
        return new Intent(activity, Sabuk_merah.class);
    }

}
