package com.example.admin_peserta_ujian.peserta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.SaveDataPreference;
import com.example.admin_peserta_ujian.adapter.Adapter_peserta;
import com.example.admin_peserta_ujian.model.Lihat_Instansi_Peserta;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_putih;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lihat_peserta extends AppCompatActivity implements Adapter_peserta.FirebaseDataListener {

    private static Activity activity;
    private DatabaseReference databaselihatpeserta;
    private RecyclerView recyclerViewlihatpeserta;
    private RecyclerView.Adapter adapterlihatpeserta;
    private RecyclerView.LayoutManager layoutManagerlihatpeserta;
    private ArrayList<Lihat_Peserta> daftar_peserta;
    public String keyInstansi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_peserta);

        keyInstansi = getIntent().getStringExtra("key");
        SaveDataPreference.setUniversitas(this, keyInstansi);

        recyclerViewlihatpeserta = findViewById(R.id.rv_lihatpeserta);
        recyclerViewlihatpeserta.setHasFixedSize(true);
        layoutManagerlihatpeserta = new LinearLayoutManager(this);
        recyclerViewlihatpeserta.setLayoutManager(layoutManagerlihatpeserta);

        databaselihatpeserta = FirebaseDatabase.getInstance().getReference();

        databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_peserta = new ArrayList<Lihat_Peserta>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Lihat_Peserta peserta = dataSnapshot1.getValue(Lihat_Peserta.class);
                    peserta.setKey(dataSnapshot1.getKey());
                    daftar_peserta.add(peserta);

                    adapterlihatpeserta = new Adapter_peserta(daftar_peserta, Lihat_peserta.this);
                    recyclerViewlihatpeserta.setAdapter(adapterlihatpeserta);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Lihat_peserta.activity = activity;
        return new Intent(activity, Lihat_peserta.class);
    }

    @Override
    public void onDeleteData(Lihat_Peserta lihat_peserta, int position) {
        if (databaselihatpeserta != null) {
            databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).child(lihat_peserta.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Lihat_peserta.this, "Berhasil Menghapus Data", Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    @Override
    public void onKirimDataSabukPutih(final Lihat_Peserta lihat_peserta, final int position) {

        databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    // databasedapur.child("Daftar Konsumen").child(konsumen.getKey()).child(key).child("tanggal").setValue(weekDay+", "+tgl);
                    databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).child(lihat_peserta.getKey()).child("status").setValue("Dalam Proses");



                    }
                kirimdatasabukputih(new Lihat_Peserta(daftar_peserta.get(position).getInstansi(), daftar_peserta.get(position).getUnit(), daftar_peserta.get(position).getSabuk(), daftar_peserta.get(position).getNama(), daftar_peserta.get(position).getTtl(),
                        daftar_peserta.get(position).getAlamat(), daftar_peserta.get(position).getPekerjaan(), daftar_peserta.get(position).getUmur(), daftar_peserta.get(position).getBb(), "Dalam Proses"));
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }

    @Override
    public void onKirimDataSabukKuning(final Lihat_Peserta lihat_peserta, final int position) {

        databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    // databasedapur.child("Daftar Konsumen").child(konsumen.getKey()).child(key).child("tanggal").setValue(weekDay+", "+tgl);
                    databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).child(lihat_peserta.getKey()).child("status").setValue("Dalam Proses");




                }
                kirimdatasabukkuning(new Lihat_Peserta(daftar_peserta.get(position).getInstansi(), daftar_peserta.get(position).getUnit(), daftar_peserta.get(position).getSabuk(), daftar_peserta.get(position).getNama(), daftar_peserta.get(position).getTtl(),
                        daftar_peserta.get(position).getAlamat(), daftar_peserta.get(position).getPekerjaan(), daftar_peserta.get(position).getUmur(), daftar_peserta.get(position).getBb(), "Dalam Proses"));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onKirimDataSabukHijau(final Lihat_Peserta lihat_peserta, final int position) {

        databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    // databasedapur.child("Daftar Konsumen").child(konsumen.getKey()).child(key).child("tanggal").setValue(weekDay+", "+tgl);
                    databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).child(lihat_peserta.getKey()).child("status").setValue("Dalam Proses");




                }
                kirimdatasabukhijau(new Lihat_Peserta(daftar_peserta.get(position).getInstansi(), daftar_peserta.get(position).getUnit(), daftar_peserta.get(position).getSabuk(), daftar_peserta.get(position).getNama(), daftar_peserta.get(position).getTtl(),
                        daftar_peserta.get(position).getAlamat(), daftar_peserta.get(position).getPekerjaan(), daftar_peserta.get(position).getUmur(), daftar_peserta.get(position).getBb(), "Dalam Proses"));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onKirimDataSabukBiru(final Lihat_Peserta lihat_peserta, final int position) {

        databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    // databasedapur.child("Daftar Konsumen").child(konsumen.getKey()).child(key).child("tanggal").setValue(weekDay+", "+tgl);
                    databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).child(lihat_peserta.getKey()).child("status").setValue("Dalam Proses");




                }
                kirimdatasabukbiru(new Lihat_Peserta(daftar_peserta.get(position).getInstansi(), daftar_peserta.get(position).getUnit(), daftar_peserta.get(position).getSabuk(), daftar_peserta.get(position).getNama(), daftar_peserta.get(position).getTtl(),
                        daftar_peserta.get(position).getAlamat(), daftar_peserta.get(position).getPekerjaan(), daftar_peserta.get(position).getUmur(), daftar_peserta.get(position).getBb(), "Dalam Proses"));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onKirimDataSabukMerah(final Lihat_Peserta lihat_peserta, final int position) {

        databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    // databasedapur.child("Daftar Konsumen").child(konsumen.getKey()).child(key).child("tanggal").setValue(weekDay+", "+tgl);
                    databaselihatpeserta.child("data_pendaftaran").child(keyInstansi).child(lihat_peserta.getKey()).child("status").setValue("Dalam Proses");




                }
                kirimdatasabukmerah(new Lihat_Peserta(daftar_peserta.get(position).getInstansi(), daftar_peserta.get(position).getUnit(), daftar_peserta.get(position).getSabuk(), daftar_peserta.get(position).getNama(), daftar_peserta.get(position).getTtl(),
                        daftar_peserta.get(position).getAlamat(), daftar_peserta.get(position).getPekerjaan(), daftar_peserta.get(position).getUmur(), daftar_peserta.get(position).getBb(), "Dalam Proses"));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }





    private void kirimdatasabukputih(Lihat_Peserta lihat_peserta) {
        Log.d("MASUK", "Masuk ke fungsi submit ");
        databaselihatpeserta.child("Daftar Peserta_Sabuk Putih").push().setValue(lihat_peserta);


    }
    private void kirimdatasabukkuning(Lihat_Peserta lihat_peserta) {
        Log.d("MASUK", "Masuk ke fungsi submit ");
        databaselihatpeserta.child("Daftar Peserta_Sabuk Kuninng").push().setValue(lihat_peserta);


    }

    private void kirimdatasabukhijau(Lihat_Peserta lihat_peserta) {
        Log.d("MASUK", "Masuk ke fungsi submit ");
        databaselihatpeserta.child("Daftar Peserta_Sabuk Hijau").push().setValue(lihat_peserta);


    }
    private void kirimdatasabukbiru(Lihat_Peserta lihat_peserta) {
        Log.d("MASUK", "Masuk ke fungsi submit ");
        databaselihatpeserta.child("Daftar Peserta_Sabuk Biru").push().setValue(lihat_peserta);


    }
    private void kirimdatasabukmerah(Lihat_Peserta lihat_peserta) {
        Log.d("MASUK", "Masuk ke fungsi submit ");
        databaselihatpeserta.child("Daftar Peserta_Sabuk Merah")/*.child(keyInstansi)*/.push().setValue(lihat_peserta);


    }







}

