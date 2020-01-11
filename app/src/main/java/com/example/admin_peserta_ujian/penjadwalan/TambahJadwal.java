package com.example.admin_peserta_ujian.penjadwalan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin_peserta_ujian.R;
import com.example.admin_peserta_ujian.model.Jadwal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;

public class TambahJadwal extends AppCompatActivity {

    DatabaseReference jadwalDatabase;
    private Spinner inputClass, inputTime;
    private TextView textDate;

    @BindView(R.id.input_date)
    Button inputDate, simpanBtn;

    private String dateID;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);

        myCalendar = Calendar.getInstance();
        jadwalDatabase = FirebaseDatabase.getInstance().getReference().child("Jadwal");

        simpanBtn = findViewById(R.id.simpanjadwal);
        textDate = findViewById(R.id.text_date);
        inputDate = findViewById(R.id.input_date);
        inputTime = findViewById(R.id.input_time);
        inputClass = findViewById(R.id.input_sabuk);


        inputJadwal();
    }

    private void inputJadwal(){
        //Input Date
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TambahJadwal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                        String formatTanggal = "dd-MMM-yyyy";
                        String formatTanggalID = "yyyyMMdd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        SimpleDateFormat sdfID = new SimpleDateFormat(formatTanggalID);

                        dateID = sdfID.format(myCalendar.getTime());
                        textDate.setText(sdf.format(myCalendar.getTime()));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Input Jam
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(TambahJadwal.this, R.array.times_array, android.R.layout.simple_dropdown_item_1line);
        inputTime.setAdapter(arrayAdapter);

        //Input Sabuk
        ArrayAdapter<CharSequence> arrayAdapterSabuk = ArrayAdapter.createFromResource(TambahJadwal.this, R.array.sabuk_array, android.R.layout.simple_dropdown_item_1line);
        inputClass.setAdapter(arrayAdapterSabuk);


        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waktu  = inputTime.getSelectedItem().toString();
                String sabuk  = inputClass.getSelectedItem().toString();

                final String idSchedule = dateID+waktu+sabuk;
                Jadwal jadwal = new Jadwal(dateID, waktu, sabuk);
                jadwalDatabase.child(idSchedule).setValue(jadwal);
                finish();
            }
        });

    }
}
