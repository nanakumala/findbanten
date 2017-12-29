package com.example.nkumala.findbanten.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nkumala.findbanten.DetailActivity;
import com.example.nkumala.findbanten.R;
import com.example.nkumala.findbanten.adapter.TokoAdapter;
import com.example.nkumala.findbanten.api.ApiInterface;
import com.example.nkumala.findbanten.api.ApiService;
import com.example.nkumala.findbanten.model.TokoModel;
import com.example.nkumala.findbanten.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment{
    ApiService apiService= new ApiService();
    Call<List<TokoModel>> apiCall;
    RecyclerView rvToko;
    TokoAdapter tokoAdapter;
    ProgressDialog progressDialog;

    List<TokoModel> listToko  = new ArrayList<>();
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        rvToko=view.findViewById(R.id.rv_toko);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(getContext());
        setData();
    }

    public void setData(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiCall = apiService.getService().getToko();

        apiCall.enqueue(new Callback<List<TokoModel>>() {
            @Override
            public void onResponse(Call<List<TokoModel>> call, Response<List<TokoModel>> response) {
             if (response.isSuccessful()){
                 listToko=response.body();
                 setAdapter();
             }else{
                 Toast.makeText(getContext(), "respone gagal", Toast.LENGTH_SHORT).show();
             }
             progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<TokoModel>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.cancel();

            }
        });
    }

    public void setAdapter(){
        tokoAdapter =new TokoAdapter(listToko, getContext(), new TokoAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                TokoModel tokoModel=listToko.get(position);
                Intent intent= new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_TOKO,tokoModel.getId());
                startActivity(intent);
            }
        });
        rvToko.setLayoutManager(new GridLayoutManager(getContext(),3));
        rvToko.setAdapter(tokoAdapter);
    }
}
