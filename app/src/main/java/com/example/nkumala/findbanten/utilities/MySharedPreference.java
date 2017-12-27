package com.example.nkumala.findbanten.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NKumala on 12/27/2017.
 */

public class MySharedPreference {
    private static final String SHARED_USER="user";

    private static final String IS_LOGIN="is_login";
    private static final String USER_ID="user_id";
    private static final String NAMA="nama";
    private static final String JENIS_KELAMIN="jenis_kelamin";
    private static final String NO_TELP="no_telp";
    private static final String EMAIL="email";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_USER,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setIsLogin(boolean isLogin){
        editor.putBoolean(IS_LOGIN,isLogin);
        editor.apply();
    }

    public void setUserId(int userId){
        editor.putInt(USER_ID, userId);
        editor.apply();
    }

    public void setNama(String nama){
        editor.putString(NAMA,nama);
        editor.apply();
    }

    public void setJenisKelamin(String jenisKelamin){
        editor.putString(JENIS_KELAMIN,jenisKelamin);
        editor.apply();
    }

    public void setNoTelp(String noTelp){
        editor.putString(NO_TELP, noTelp);
        editor.apply();
    }

    public void setEmail(String email){
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public boolean getIsLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public int getUserId(){
        return sharedPreferences.getInt(USER_ID, 0);
    }

    public String getNama(){
        return sharedPreferences.getString(NAMA, "");
    }

    public String getJenisKelamin(){
        return sharedPreferences.getString(JENIS_KELAMIN, "");
    }

    public String getNoTelp(){
        return sharedPreferences.getString(NO_TELP, "");
    }

    public String getEmail(){
        return sharedPreferences.getString(EMAIL, "");
    }

}
