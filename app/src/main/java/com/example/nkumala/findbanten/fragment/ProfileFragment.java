package com.example.nkumala.findbanten.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nkumala.findbanten.LoginActivity;
import com.example.nkumala.findbanten.R;
import com.example.nkumala.findbanten.utilities.MySharedPreference;


public class ProfileFragment extends Fragment{
    TextView txt_nama, txt_email, txt_telp, txt_jenis_kelamin;
    Button btn_logout;
    MySharedPreference sharedPreference;

    public ProfileFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreference=new MySharedPreference(getContext());

        txt_nama.setText(sharedPreference.getNama());
        txt_email.setText(sharedPreference.getEmail());
        txt_telp.setText(sharedPreference.getNoTelp());
        txt_jenis_kelamin.setText(sharedPreference.getJenisKelamin());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile,container,false);

        txt_nama=view.findViewById(R.id.txt_nama_profile);
        txt_email=view.findViewById( R.id.txt_email_profile);
        txt_telp=view.findViewById(R.id.txt_telp_profile);
        txt_jenis_kelamin=view.findViewById(R.id.txt_jk_profile);
        btn_logout=view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.setIsLogin(false);
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
