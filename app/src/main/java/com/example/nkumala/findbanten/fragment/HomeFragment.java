package com.example.nkumala.findbanten.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nkumala.findbanten.DetailActivity;
import com.example.nkumala.findbanten.R;
import com.example.nkumala.findbanten.adapter.TokoAdapter;
import com.example.nkumala.findbanten.model.TokoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Urip on 11/28/2017.
 */

public class HomeFragment extends Fragment{

    RecyclerView rvRecommended,rvMostView,rvPopuler;
    TokoAdapter tokoAdapter;

    List<TokoModel> reccomendedList  = new ArrayList<>();
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        rvRecommended=view.findViewById(R.id.rv_shop);
        rvMostView=view.findViewById(R.id.rv_shop2);
        rvPopuler=view.findViewById(R.id.rv_shop3);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
        setAdapter();
    }

    public void setData(){
        for (int i=1;i<10;i++){
            TokoModel temp= new TokoModel(i,"Nana","081879713","jalan unud");;
            reccomendedList.add(temp);
        }
    }

    public void setAdapter(){
        tokoAdapter =new TokoAdapter(reccomendedList, getContext(), new TokoAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                TokoModel tokoModel=reccomendedList.get(position);
                Intent intent= new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_TOKO,tokoModel);
                startActivity(intent);
            }
        });
        rvRecommended.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvRecommended.setAdapter(tokoAdapter);
        rvMostView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvMostView.setAdapter(tokoAdapter);
        rvPopuler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvPopuler.setAdapter(tokoAdapter);
    }
}
