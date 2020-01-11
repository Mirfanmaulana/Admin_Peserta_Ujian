package com.example.admin_peserta_ujian.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.Edit_data_admin;
import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Lihat_Instansi_Peserta;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_kuning;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_putih;
import com.example.admin_peserta_ujian.peserta.Lihat_peserta;
import com.google.firebase.database.DatabaseReference;

import java.security.Key;
import java.util.ArrayList;

public class Adapter_peserta extends RecyclerView.Adapter<Adapter_peserta.PesertaViewHolder> {
    private ArrayList<Lihat_Peserta> datalist;
    private Context context;
    private FirebaseDataListener listener;

    public Adapter_peserta(ArrayList<Lihat_Peserta> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
        listener = (Lihat_peserta)context;

    }

    @Override
    public Adapter_peserta.PesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_peserta,parent,false);
        PesertaViewHolder vh = new PesertaViewHolder(view);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_peserta.PesertaViewHolder holder, final int position) {

        final  String Sinstansi = datalist.get(position).getInstansi();
        final String Snama = datalist.get(position).getNama();
        final String Ssabuk = datalist.get(position).getSabuk();
        final  String Sbb = datalist.get(position).getBb();
        final  String Sstatus = datalist.get(position).getStatus();



        holder.instansi.setText(Sinstansi);
        holder.nama.setText(Snama);
        holder.sabuk.setText(Ssabuk);
        holder.bb.setText(Sbb);
        holder.konfirmasi.setText(Sstatus);
        final String key= datalist.get(position).getKey();





        holder.cardpeserta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                final String key= datalist.get(position).getKey();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view_peserta);
                dialog.setTitle("Silahkan Pilih Aksi");
                dialog.show();

                Button sabuk_putih = dialog.findViewById(R.id.kuning);
                Button sabuk_kuning = dialog.findViewById(R.id.hijau);
                Button sabuk_hijau = dialog.findViewById(R.id.biru);
                Button sabuk_biru = dialog.findViewById(R.id.merah);
                Button sabuk_merah = dialog.findViewById(R.id.hitam);

                Button edit_data = dialog.findViewById(R.id.btn_edit);
                Button delete_data = dialog.findViewById(R.id.btn_delete);

                final String keyInstansi = null;





//              button edit dan delete
                edit_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //context.startActivity(Edit_data_admin.getActIntent((Activity) context).putExtra("key",key).putExtra("data",datalist.get(position)));
                        Intent intent = new Intent(context.getApplicationContext(), Edit_data_admin.class);
                        intent.putExtra("key", key);
                        intent.putExtra("keyInstansi", keyInstansi);
                        intent.putExtra("data", datalist.get(position));
                        context.startActivity(intent);
                       // context.startActivity(Edit_data_admin.getActIntent((Activity) context).putExtra("key",datalist.get(position)).putExtra("data",datalist.get(position)));

                    }


                });
                delete_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        listener.onDeleteData(datalist.get(position),position);
                    }
                });

//              akhir dari button edit dan delete











                sabuk_putih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        listener.onKirimDataSabukPutih(datalist.get(position), position);
                        dialog.dismiss();


                    }
                });
               sabuk_kuning.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onKirimDataSabukKuning(datalist.get(position), position);
                        dialog.dismiss();
                    }
                });

                sabuk_hijau.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onKirimDataSabukHijau(datalist.get(position), position);
                        dialog.dismiss();
                    }
                });
                sabuk_biru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onKirimDataSabukBiru(datalist.get(position), position);
                        dialog.dismiss();
                    }
                });
                sabuk_merah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onKirimDataSabukMerah(datalist.get(position), position);
                        dialog.dismiss();
                    }
                });



                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public class PesertaViewHolder extends RecyclerView.ViewHolder {

        private TextView instansi, nama, sabuk,bb,konfirmasi;
        private CardView cardpeserta;

        public PesertaViewHolder(@NonNull View itemView) {
            super(itemView);

            instansi = itemView.findViewById(R.id.txt_instansi);
            nama = itemView.findViewById(R.id.txt_peserta_nama);
            sabuk = itemView.findViewById(R.id.txt_sabuk);
            bb = itemView.findViewById(R.id.txt_bb);
            konfirmasi = itemView.findViewById(R.id.konfirmasi);

            cardpeserta = itemView.findViewById(R.id.card_peserta);
        }
    }

    public interface FirebaseDataListener{

        void onDeleteData(Lihat_Peserta lihat_peserta, int position);

        void onKirimDataSabukPutih(Lihat_Peserta lihat_peserta, int position);

        void onKirimDataSabukKuning(Lihat_Peserta lihat_peserta, int position);

        void onKirimDataSabukHijau(Lihat_Peserta lihat_peserta, int position);

        void onKirimDataSabukBiru(Lihat_Peserta lihat_peserta, int position);

        void onKirimDataSabukMerah(Lihat_Peserta lihat_peserta, int position);

    }


}
