package com.example.nkumala.findbanten;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nkumala.findbanten.fragment.HomeFragment;
import com.example.nkumala.findbanten.fragment.MapsFragment;
import com.example.nkumala.findbanten.fragment.ProfileFragment;
import com.example.nkumala.findbanten.fragment.SearchFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    BottomNavigationViewEx bottomNavigationViewEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout_main,new HomeFragment(),HomeFragment.class.getSimpleName())
                .commit();
        init();
    }

    private void init(){
        bottomNavigationViewEx=findViewById(R.id.bnve);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_layout_main,new HomeFragment(),HomeFragment.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.menu_maps:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_layout_main,new MapsFragment(),MapsFragment.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.menu_search:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_layout_main,new SearchFragment(),SearchFragment.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.menu_profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_layout_main,new ProfileFragment(),ProfileFragment.class.getSimpleName())
                                .commit();
                        break;
                }
                return true;
            }
        });
    }
}
