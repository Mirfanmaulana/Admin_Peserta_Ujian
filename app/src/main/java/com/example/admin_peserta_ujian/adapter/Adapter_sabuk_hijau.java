package com.example.admin_peserta_ujian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;

import java.util.ArrayList;

public class Adapter_sabuk_hijau extends RecyclerView.Adapter<Adapter_sabuk_hijau.SabukhijauViewHolder> {
    private ArrayList<Lihat_Peserta> datalist;
    private Context context;

    public Adapter_sabuk_hijau(ArrayList<Lihat_Peserta> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public Adapter_sabuk_hijau.SabukhijauViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_peserta,parent,false);
        SabukhijauViewHolder vh = new SabukhijauViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_sabuk_hijau.SabukhijauViewHolder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class SabukhijauViewHolder extends RecyclerView.ViewHolder {
        private TextView instansi, nama, sabuk,bb,konfirmasi;
        private CardView cardpeserta;


        public SabukhijauViewHolder(@NonNull View itemView) {
            super(itemView);

            instansi = itemView.findViewById(R.id.txt_instansi);
            nama = itemView.findViewById(R.id.txt_peserta_nama);
            sabuk = itemView.findViewById(R.id.txt_sabuk);
            bb = itemView.findViewById(R.id.txt_bb);
            konfirmasi = itemView.findViewById(R.id.konfirmasi);

            cardpeserta = itemView.findViewById(R.id.card_peserta);
        }
    }
}
