package com.example.nkumala.findbanten;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nkumala.findbanten.api.ApiService;
import com.example.nkumala.findbanten.model.UserModel;
import com.example.nkumala.findbanten.utilities.MySharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    UserModel user;
    ApiService apiService= new ApiService();
    Call<UserModel> apiCall;

    EditText etEmail, etPassword;
    Button loginButton;
    ProgressDialog progressDialog;
    TextView txt_register;

    MySharedPreference mySharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySharedPreference= new MySharedPreference(this);
        if (mySharedPreference.getIsLogin()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_login);
        init();

    }

    public void init(){
        etEmail=findViewById(R.id.etemaillogin);
        etPassword=findViewById(R.id.etpasslogin);
        loginButton=findViewById(R.id.btnlogin);
        txt_register=findViewById(R.id.txt_register);

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        progressDialog= new ProgressDialog(this);
    }

    public void login(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String email= etEmail.getText().toString();
        String password=etPassword.getText().toString();

        apiCall = apiService.getService().login(email,password);
        apiCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()){
//                    JSONObject user=response.body();
                    user=response.body();
                    if (user.isSuccess()){
                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        mySharedPreference.setIsLogin(true);
                        mySharedPreference.setUserId(user.getId_user());
                        mySharedPreference.setNama(user.getNama());
                        mySharedPreference.setJenisKelamin(user.getJenis_kelamin());
                        mySharedPreference.setNoTelp(user.getNo_telp());
                        mySharedPreference.setEmail(user.getEmail());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Koneksi Error"+t, Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }
}
