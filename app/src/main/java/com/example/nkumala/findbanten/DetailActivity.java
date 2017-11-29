package com.example.nkumala.findbanten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nkumala.findbanten.model.TokoModel;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class DetailActivity extends AppCompatActivity {
    public static final String KEY_TOKO="toko";

    TokoModel tokoModel;
    BottomNavigationViewEx bottomNavigationViewEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bottomNavigationViewEx=findViewById(R.id.bnv_detail);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        tokoModel= (TokoModel) getIntent().getSerializableExtra(KEY_TOKO);
    }
}
