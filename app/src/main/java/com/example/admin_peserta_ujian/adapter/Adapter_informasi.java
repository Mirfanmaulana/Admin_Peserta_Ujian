package com.example.admin_peserta_ujian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Informasi;

import java.util.ArrayList;

public class Adapter_informasi extends RecyclerView.Adapter<Adapter_informasi.InformasiViewHolder> {
    private ArrayList<Informasi> datalist;
    private Context context;

    public Adapter_informasi(ArrayList<Informasi> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public Adapter_informasi.InformasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informasi,parent,false);
        InformasiViewHolder vh = new InformasiViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_informasi.InformasiViewHolder holder, int position) {
        final String Sjudul = datalist.get(position).getJudul();
        final String Sdeskripsi = datalist.get(position).getDeskripsi();

        holder.judul.setText(Sjudul);
        holder.deskripsi.setText(Sdeskripsi);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class InformasiViewHolder extends RecyclerView.ViewHolder {
        private TextView judul, deskripsi;
        public InformasiViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul_informasi);
            deskripsi = itemView.findViewById(R.id.deskripsi_informasi);
        }
    }
}
