package com.example.admin_peserta_ujian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_data_admin extends AppCompatActivity {

    private static Activity activity;
    private DatabaseReference database;
    private EditText instansi, unit, sabuk, nama, ttl, alamat, pekerjaan, umur, bb;
    private Button btn_simpan;
    private String Sinstansi, Sunit, Ssabuk, Snama, Sttl, Salamat, Spekerjaan, Sumur, Sbb;
    private String key;
    private String keyinstansi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_admin);

        instansi = findViewById(R.id.edtinstansi);
        unit = findViewById(R.id.edtunit);
        sabuk = findViewById(R.id.edtsabuk);
        nama = findViewById(R.id.edtnama);
        ttl = findViewById(R.id.edtttl);
        alamat = findViewById(R.id.edtalamat);
        pekerjaan = findViewById(R.id.edtpekerjaan);
        umur = findViewById(R.id.edtumur);
        bb = findViewById(R.id.edtberatbadan);


        key = getIntent().getStringExtra("key");
        keyinstansi = getIntent().getStringExtra("keyInstansi");




        btn_simpan = findViewById(R.id.btn_simpan_edit);

        database = FirebaseDatabase.getInstance().getReference();

        final Lihat_Peserta lihat_peserta = (Lihat_Peserta) getIntent().getSerializableExtra("data");

        if (lihat_peserta != null) {
            instansi.setText(lihat_peserta.getInstansi());
            unit.setText(lihat_peserta.getUnit());
            sabuk.setText(lihat_peserta.getSabuk());
            nama.setText(lihat_peserta.getNama());
            ttl.setText(lihat_peserta.getTtl());
            alamat.setText(lihat_peserta.getAlamat());
            pekerjaan.setText(lihat_peserta.getPekerjaan());
            umur.setText(lihat_peserta.getUmur());
            bb.setText(lihat_peserta.getBb());


            btn_simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lihat_peserta.setInstansi(instansi.getText().toString());
                    lihat_peserta.setUnit(unit.getText().toString());
                    lihat_peserta.setSabuk(sabuk.getText().toString());
                    lihat_peserta.setNama(nama.getText().toString());
                    lihat_peserta.setTtl(ttl.getText().toString());
                    lihat_peserta.setPekerjaan(pekerjaan.getText().toString());
                    lihat_peserta.setUmur(umur.getText().toString());
                    lihat_peserta.setBb(bb.getText().toString());

                        update(lihat_peserta);



                }
            });
        } else {
            btn_simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isEmpty(instansi.getText().toString()) && !isEmpty(unit.getText().toString()) && !isEmpty(sabuk.getText().toString()) && !isEmpty(nama.getText().toString()) && !isEmpty(ttl.getText().toString()) && !isEmpty(alamat.getText().toString()) && !isEmpty(pekerjaan.getText().toString()) && !isEmpty(umur.getText().toString()) && !isEmpty(bb.getText().toString()))
                        simpan(new Lihat_Peserta(instansi.getText().toString(), unit.getText().toString(), sabuk.getText().toString(), nama.getText().toString(), ttl.getText().toString(), alamat.getText().toString(), pekerjaan.getText().toString(), umur.getText().toString(), bb.getText().toString(), ""));
                    else
                        Snackbar.make(findViewById(R.id.btn_simpan_edit), "Data Tidak Boleh Kosong", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(instansi.getWindowToken(), 0);
                }
            });




        }

    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }



    private void  update(final Lihat_Peserta peserta){

/*
        database.child("data_pendaftaran").child(peserta.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String key = ds.getKey();
                    database.child("data_pendaftaran").child(peserta.getKey()).child(key).setValue(peserta);

                    Snackbar.make(findViewById(R.id.btn_simpan_edit), "Data Berhasil Di Updatekan",
                            Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    }).show();




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



      */
            String universitasKey = SaveDataPreference.getUniversitas(this);
            database.child("data_pendaftaran").child(universitasKey).child(peserta.getKey()).setValue(peserta)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.btn_simpan_edit), "Data Berhasil Di Updatekan",
                                Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();

                            }
                        }).show();


                    }
                });


    }






    private void simpan(Lihat_Peserta lihat_peserta) {
        database.child("data_pendaftaran").child(key).push().setValue(lihat_peserta).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                instansi.setText("");
                unit.setText("");
                sabuk.setText("");
                nama.setText("");
                ttl.setText("");
                alamat.setText("");
                pekerjaan.setText("");
                umur.setText("");
                bb.setText("");
                Snackbar.make(findViewById(R.id.btn_simpan), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();


            }
        });

    }

    public static Intent getActIntent(Activity activity) {
        Edit_data_admin.activity = activity;
        return new Intent(activity, Edit_data_admin.class);
    }
}
