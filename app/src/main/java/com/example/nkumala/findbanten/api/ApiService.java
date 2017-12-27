package com.example.nkumala.findbanten.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NKumala on 12/27/2017.
 */

public class ApiService {
    public static final String BASE_URL="http://192.168.43.33:8000/";
    private ApiInterface apiInterface ;
    public ApiInterface getService(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface =retrofit.create(ApiInterface.class);
        return apiInterface ;
    }

}
