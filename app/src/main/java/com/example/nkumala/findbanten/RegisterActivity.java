package com.example.nkumala.findbanten;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nkumala.findbanten.api.ApiService;
import com.example.nkumala.findbanten.model.UserModel;
import com.example.nkumala.findbanten.utilities.MySharedPreference;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    UserModel user;
    ApiService apiService= new ApiService();
    Call<UserModel> apiCall;

    EditText edtNama,edtNo_Telp,edtEmail, edtPassword;
    RadioGroup rgJenisKelamin;
    RadioButton rbLaki_laki, rbPerempuan;
    Button buttonRegister;
    ProgressDialog progressDialog;

    String jenisKelamin;

    MySharedPreference mySharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
//        register();
    }

    public void init(){
        edtNama=findViewById(R.id.et_nama);
        edtNo_Telp=findViewById(R.id.et_no_telp);
        edtEmail=findViewById(R.id.et_email);
        edtPassword=findViewById(R.id.et_password);
        rbLaki_laki=findViewById(R.id.rb_lakilaki);
        rbPerempuan=findViewById(R.id.rb_perempuan);
        buttonRegister=findViewById(R.id.bt_register);
        rgJenisKelamin=findViewById(R.id.rg_jeniskelamin);
        progressDialog=new ProgressDialog(this);
        rgJenisKelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb=radioGroup.findViewById(i);
                jenisKelamin=rb.getText().toString();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        mySharedPreference=new MySharedPreference(this);

    }

    public void register(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String nama=edtNama.getText().toString();
        String no_telp=edtNo_Telp.getText().toString();
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();

        apiCall= apiService.getService().register(nama,jenisKelamin,no_telp, email,password);
        apiCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()){
                    user=response.body();
                    Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                    mySharedPreference.setIsLogin(true);
                    mySharedPreference.setUserId(user.getId_user());
                    mySharedPreference.setNama(user.getNama());
                    mySharedPreference.setJenisKelamin(user.getJenis_kelamin());
                    mySharedPreference.setNoTelp(user.getNo_telp());
                    mySharedPreference.setEmail(user.getEmail());

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                }
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Koneksi Error", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }

        });
    }




}
