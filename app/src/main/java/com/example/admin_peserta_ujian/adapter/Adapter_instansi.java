package com.example.admin_peserta_ujian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Instansi;

import java.util.ArrayList;

public class Adapter_instansi extends RecyclerView.Adapter<Adapter_instansi.InstansiViewHolder> {

    private ArrayList<Instansi> datalist;
    private Context context;

    public Adapter_instansi(ArrayList<Instansi> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public InstansiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instansi,parent,false);
        InstansiViewHolder vh = new InstansiViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InstansiViewHolder holder, int position) {
        final String Sinstansi = datalist.get(position).getInstansi();

        holder.instansi.setText(Sinstansi);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class InstansiViewHolder extends RecyclerView.ViewHolder {

        private TextView instansi;

        public InstansiViewHolder(@NonNull View itemView) {
            super(itemView);

            instansi = (TextView) itemView.findViewById(R.id.item_instansi);
        }
    }
}
