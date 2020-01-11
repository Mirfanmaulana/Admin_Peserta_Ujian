package com.example.admin_peserta_ujian.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Lihat_Instansi_Peserta;
import com.example.admin_peserta_ujian.peserta.Lihat_peserta;


import java.util.ArrayList;

public class Adapter_lihat_instansi_peserta extends RecyclerView.Adapter<Adapter_lihat_instansi_peserta.InstansiViewHolder> {

    private ArrayList<Lihat_Instansi_Peserta> datalist;
    private Context context;

    public Adapter_lihat_instansi_peserta(ArrayList<Lihat_Instansi_Peserta> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public InstansiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instansi_peserta,parent,false);
        InstansiViewHolder vh = new InstansiViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InstansiViewHolder holder, int position) {
        final String Sinstansi = datalist.get(position).getInstansi();

        final String key = datalist.get(position).getKey();

        holder.card_instansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), Lihat_peserta.class);
                intent.putExtra("key", key);
                context.startActivity(intent);

            }
        });

        holder.instansi.setText(Sinstansi);


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class InstansiViewHolder extends RecyclerView.ViewHolder {

        private TextView instansi;
        private CardView card_instansi;

        public InstansiViewHolder(@NonNull View itemView) {
            super(itemView);

            instansi = (TextView) itemView.findViewById(R.id.item_instansi_peserta);
            card_instansi = (CardView) itemView.findViewById(R.id.card_instansi_peserta);
        }
    }
}
