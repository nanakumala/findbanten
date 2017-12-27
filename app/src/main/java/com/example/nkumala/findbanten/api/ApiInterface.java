package com.example.nkumala.findbanten.api;

import com.example.nkumala.findbanten.model.BarangModel;
import com.example.nkumala.findbanten.model.TokoModel;
import com.example.nkumala.findbanten.model.UserModel;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by NKumala on 12/27/2017.
 */

public interface ApiInterface {
    @POST("login")
    Call<UserModel> login(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Call<UserModel> register(@Query("nama") String nama,
                              @Query("jenis_kelamin") String jenis_kelamin,
                              @Query("no_telp") String no_telp,
                              @Query("email") String email,
                              @Query("password") String password);

    @GET("toko")
    Call<List<TokoModel>> getToko();

    @GET("barang/{id_toko}")
    Call<List<BarangModel>> getBarang(@Path("id_toko") int id_toko);

    @GET("search")
    Call<List<BarangModel>> searchBarang(@Query("keyword") String keyword);

}
