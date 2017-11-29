package com.example.nkumala.findbanten.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nkumala.findbanten.R;

/**
 * Created by Urip on 11/28/2017.
 */

public class MapsFragment extends Fragment{
    public MapsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_maps,container,false);
        return view;
    }
}
