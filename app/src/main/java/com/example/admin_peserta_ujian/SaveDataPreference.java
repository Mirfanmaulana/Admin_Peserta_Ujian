package com.example.admin_peserta_ujian;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveDataPreference {

    private static final String PREF_NI= "universitas";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    //Menghapus data
    public static void clearAllPref(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }

    //Untuk Menyimpan data
    public static void setUniversitas(Context ctx, String userNI)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_NI, userNI);
        editor.commit();
    }



    //Untuk mengambil data
    public static String getUniversitas(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_NI, "");
    }



}
