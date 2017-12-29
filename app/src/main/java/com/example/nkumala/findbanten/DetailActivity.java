package com.example.nkumala.findbanten;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nkumala.findbanten.adapter.BarangAdapter;
import com.example.nkumala.findbanten.adapter.TokoAdapter;
import com.example.nkumala.findbanten.api.ApiService;
import com.example.nkumala.findbanten.model.BarangModel;
import com.example.nkumala.findbanten.model.TokoModel;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    ApiService apiService= new ApiService();
    Call<List<BarangModel>> apiCall;
    Call<TokoModel> apiToko;
    ProgressDialog progressDialog;
    List<BarangModel> listBarang  = new ArrayList<>();
    int id_toko;
    BarangAdapter adapter;
    RecyclerView rvBarang;

    public static final String KEY_TOKO="id_toko";
    TokoModel tokoModel;
    BottomNavigationViewEx bottomNavigationViewEx;
    String IMAGE_URL="http://192.168.43.226:8000/images/toko/";
    TextView txt_det_nama, txt_det_telp, txt_det_alamat;
    ImageView img_det;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        setToko();
//        setBarang();
    }

    private void setToko() {
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiToko=apiService.getService().getDetailToko(id_toko);
        apiToko.enqueue(new Callback<TokoModel>() {
            @Override
            public void onResponse(Call<TokoModel> call, Response<TokoModel> response) {
                if (response.isSuccessful()){
                    tokoModel=response.body();

                    txt_det_nama.setText(tokoModel.getNama_toko());
                    txt_det_telp.setText(tokoModel.getAlamat());
                    txt_det_alamat.setText(tokoModel.getNo_telp());
                    Glide.with(getApplicationContext()).load(IMAGE_URL+tokoModel.getGambar_toko()).into(img_det);

                    setBarang();
                }else {
                    progressDialog.cancel();
                    Toast.makeText(DetailActivity.this, "response toko gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokoModel> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Koneksi error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init(){
        id_toko=getIntent().getIntExtra(KEY_TOKO,0);

        bottomNavigationViewEx=findViewById(R.id.bnv_detail);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);

        txt_det_nama=findViewById(R.id.txt_detail_nama);
        txt_det_telp=findViewById(R.id.txt_detail_telepon);
        txt_det_alamat=findViewById(R.id.txt_detail_alamat);
        img_det=findViewById(R.id.img_detail);

        rvBarang=findViewById(R.id.rv_barang);
        rvBarang.setLayoutManager(new LinearLayoutManager(this));

        progressDialog=new ProgressDialog(this);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_call:
                        Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tokoModel.getNo_telp()));
                        startActivity(intentCall);
                        break;
                    case R.id.menu_message:
                        Intent intentMsg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+tokoModel.getNo_telp()));
                        startActivity(intentMsg);
                        break;
                    case R.id.menu_location:
                        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putDouble(MapsActivity.KEY_LAT,tokoModel.getLat());
                        bundle.putDouble(MapsActivity.KEY_LNG,tokoModel.getLng());
                        bundle.putString(MapsActivity.KEY_NAMA,tokoModel.getNama_toko());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    public void setBarang(){
        apiCall = apiService.getService().getBarang(tokoModel.getId());
        apiCall.enqueue(new Callback<List<BarangModel>>() {
            @Override
            public void onResponse(Call<List<BarangModel>> call, Response<List<BarangModel>> response) {
                if (response.isSuccessful()){
                    listBarang=response.body();
                    setAdapter();
                }else{
                    Toast.makeText(DetailActivity.this, "Response gagal", Toast.LENGTH_SHORT).show();
                }
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<BarangModel>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }

    public void setAdapter(){
        adapter=new BarangAdapter(listBarang,this);
        rvBarang.setAdapter(adapter);
    }
}
