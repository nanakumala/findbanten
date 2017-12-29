package com.example.nkumala.findbanten.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nkumala.findbanten.R;
import com.example.nkumala.findbanten.adapter.TokoAdapter;
import com.example.nkumala.findbanten.api.ApiService;
import com.example.nkumala.findbanten.model.TokoModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    ApiService apiService= new ApiService();
    Call<List<TokoModel>> apiCall;
    ProgressDialog progressDialog;

    List<TokoModel> listToko  = new ArrayList<>();

    MapView mMapView;
    private GoogleMap mMap;
    public MapsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
    }

    public void setData(){
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiCall = apiService.getService().getToko();

        apiCall.enqueue(new Callback<List<TokoModel>>() {
            @Override
            public void onResponse(Call<List<TokoModel>> call, Response<List<TokoModel>> response) {
                if (response.isSuccessful()){
                    listToko=response.body();
//                    setMap();
                    try {
                        MapsInitializer.initialize(getActivity().getApplicationContext());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mMapView.getMapAsync(MapsFragment.this);
//                    Toast.makeText(getContext(), "Map Tampil", Toast.LENGTH_SHORT).show();
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_maps,container,false);
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            // Add a marker in Sydney and move the camera
        for (int i=0;i<listToko.size();i++){
            TokoModel tokoModel=listToko.get(i);
            LatLng toko = new LatLng(tokoModel.getLat(), tokoModel.getLng());
            mMap.addMarker(new MarkerOptions().position(toko).title(tokoModel.getNama_toko()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toko,15));
        }
    }


}
