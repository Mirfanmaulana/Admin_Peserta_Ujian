package com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_biru;
import com.example.admin_peserta_ujian.adapter.Adapter_sabuk_putih;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sabuk_biru extends AppCompatActivity {

    private static Activity activity;
    private DatabaseReference database_sabukbiru;
    private RecyclerView recyclerView_sabukbiru;
    private RecyclerView.Adapter adapter_sabukbiru;
    private RecyclerView.LayoutManager layoutManager_sabukbiru;
    private ArrayList<Lihat_Peserta> daftar_sabukbiru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabuk_biru);

        recyclerView_sabukbiru = findViewById(R.id.Rv_sabuk_biru);
        recyclerView_sabukbiru.setHasFixedSize(true);
        layoutManager_sabukbiru = new LinearLayoutManager(this);
        recyclerView_sabukbiru.setLayoutManager(layoutManager_sabukbiru);

        database_sabukbiru = FirebaseDatabase.getInstance().getReference();

        database_sabukbiru.child("Daftar Peserta_Sabuk Biru").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_sabukbiru = new ArrayList<Lihat_Peserta>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Peserta peserta = dataSnapshot1.getValue(Lihat_Peserta.class);
                    peserta.setKey(dataSnapshot1.getKey());
                    daftar_sabukbiru.add(peserta);

                    adapter_sabukbiru = new Adapter_sabuk_biru(daftar_sabukbiru, Sabuk_biru.this);
                    recyclerView_sabukbiru.setAdapter(adapter_sabukbiru);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Sabuk_biru.activity = activity;
        return new Intent(activity, Sabuk_biru.class);
    }

}
