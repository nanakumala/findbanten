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

public class SearchFragment extends Fragment{
    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_search,container,false);
        return view;
    }
}
