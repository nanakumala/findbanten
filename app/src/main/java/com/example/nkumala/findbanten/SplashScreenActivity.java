package com.example.nkumala.findbanten;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.nkumala.findbanten.utilities.MySharedPreference;

/**
 * Created by NKumala on 12/28/2017.
 */

public class SplashScreenActivity extends AppCompatActivity{

    MySharedPreference sharedPreference;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >=19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        sharedPreference= new MySharedPreference(this);
        if (!sharedPreference.getIsLogin()){
            intent = new Intent(this,LoginActivity.class);
        }else{
            intent= new Intent(this,MainActivity.class);
        }
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        finish();
                    }
                },3000
        );
    }
}
