package com.example.admin_peserta_ujian.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_peserta_ujian.Edit_data_admin;
import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Lihat_Peserta;
import com.example.admin_peserta_ujian.penjadwalan.kategori_sabuk.Sabuk_putih;
import com.example.admin_peserta_ujian.peserta.Lihat_peserta;

import java.util.ArrayList;

public class Adapter_sabuk_putih extends RecyclerView.Adapter<Adapter_sabuk_putih.SabukputihViewHolder> {
    private ArrayList<Lihat_Peserta> datalist;
    private Context context;
    private FirebaseDataListener listener;

    public Adapter_sabuk_putih(ArrayList<Lihat_Peserta> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
        listener = (Sabuk_putih)context;

    }

    @Override
    public Adapter_sabuk_putih.SabukputihViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_peserta,parent,false);
        SabukputihViewHolder vh = new SabukputihViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_sabuk_putih.SabukputihViewHolder holder, final int position) {
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

        holder.cardpeserta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view_edit_delete);
                dialog.setTitle("Silahkan Pilih Aksi");
                dialog.show();

                Button edit = dialog.findViewById(R.id.edit);
                Button delete = dialog.findViewById(R.id.delete);




                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        //context.startActivity(Edit_data_admin.getActIntent((Activity) context).putExtra("key",key).putExtra("data",datalist.get(position)));
                        Intent intent = new Intent(context.getApplicationContext(), Edit_data_admin.class);
                        intent.putExtra("data",datalist.get(position));
                        context.startActivity(intent);
                        // context.startActivity(Edit_data_admin.getActIntent((Activity) context).putExtra("key",datalist.get(position)).putExtra("data",datalist.get(position)));

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        listener.onDeleteData(datalist.get(position),position);
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

    public class SabukputihViewHolder extends RecyclerView.ViewHolder {
        private TextView instansi, nama, sabuk,bb,konfirmasi;
        private CardView cardpeserta;


        public SabukputihViewHolder(@NonNull View itemView) {
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
    }
}
