package com.example.nkumala.findbanten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nkumala.findbanten.model.TokoModel;

public class DetailActivity extends AppCompatActivity {
    public static final String KEY_TOKO="toko";

    TokoModel tokoModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tokoModel= (TokoModel) getIntent().getSerializableExtra(KEY_TOKO);
    }
}
