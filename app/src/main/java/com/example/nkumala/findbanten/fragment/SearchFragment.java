package com.example.nkumala.findbanten.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nkumala.findbanten.DetailActivity;
import com.example.nkumala.findbanten.R;
import com.example.nkumala.findbanten.adapter.BarangAdapter;
import com.example.nkumala.findbanten.api.ApiService;
import com.example.nkumala.findbanten.model.BarangModel;
import com.example.nkumala.findbanten.model.TokoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment{
    ApiService apiService= new ApiService();
    Call<List<BarangModel>> apiCall;

    List<BarangModel> listBarang  = new ArrayList<>();

    BarangAdapter adapter;
    RecyclerView rvBarang;
    ProgressBar progressBar;


    public SearchFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_search,container,false);
        rvBarang=view.findViewById(R.id.rv_search_barang);
        rvBarang.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar=view.findViewById(R.id.progressbar);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item,menu);
        MenuItem item =menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listBarang.clear();
                progressBar.setVisibility(View.VISIBLE);

                if (!s.equals("")){
                    apiCall=apiService.getService().searchBarang(s);
                    apiCall.enqueue(new Callback<List<BarangModel>>() {
                        @Override
                        public void onResponse(Call<List<BarangModel>> call, Response<List<BarangModel>> response) {
                            if (response.isSuccessful()){
                                listBarang=response.body();
                                setAdapter();
                            }else{
                                Toast.makeText(getActivity(), "Response gagal", Toast.LENGTH_SHORT).show();
                            }progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<List<BarangModel>> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }
    public void setAdapter(){
        adapter=new BarangAdapter(listBarang,getContext());
        adapter.setOnClickListener(new BarangAdapter.OnClickListener() {
            @Override
            public void onCLick(int position) {
                BarangModel barangModel=listBarang.get(position);
                Intent intent= new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_TOKO,barangModel.getId_toko());
                startActivity(intent);
            }
        });
        rvBarang.setAdapter(adapter);
    }
}
