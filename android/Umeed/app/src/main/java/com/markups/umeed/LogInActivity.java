package com.markups.umeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {
    TextInputEditText login_ed_phone, login_ed_password ;
    TextView login_bt_login;
    ProgressBar login_pg_login;
    String phone,password;
    SharedPreferences spref;
    SharedPreferences.Editor edit;
    static  RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login_ed_phone=findViewById(R.id.login_ed_phone);
        login_ed_password=findViewById(R.id.login_ed_password);
        phone=login_ed_phone.getText().toString();
        password=login_ed_password.getText().toString();
        spref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        edit=spref.edit();
        login_bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_bt_login.setVisibility(View.GONE);
                login_pg_login.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder().baseUrl("").addConverterFactory(
                        GsonConverterFactory.create()).build();
                retrofitInterface = retrofit.create(RetrofitInterface.class);
                Call<LogInReturn> logInReturnCall=retrofitInterface.login(new LogInRequest(Integer.parseInt(phone),password));
                logInReturnCall.enqueue(new Callback<LogInReturn>() {
                    @Override
                    public void onResponse(Call<LogInReturn> call, Response<LogInReturn> response) {
                        if(response.body()==null){
                            Toast.makeText(LogInActivity.this,"Incorrect Credential",Toast.LENGTH_LONG);
                            login_bt_login.setVisibility(View.VISIBLE);
                            login_pg_login.setVisibility(View.GONE);
                        }
                        else
                        {
                            if(response.body().getIs_Manager()=="T"){
                            edit.putString("type", "manager");
                        }
                        else{
                            edit.putString("type", "employee");
                        }
                            edit.putString("id",response.body().getWorker_ID());
                            edit.commit();

                        startActivity(new Intent(LogInActivity.this,BottomNavManagerActivity.class));
                        finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<LogInReturn> call, Throwable t) {
                        Toast.makeText(LogInActivity.this,""+t.getMessage(),Toast.LENGTH_LONG);
                        login_bt_login.setVisibility(View.VISIBLE);
                        login_pg_login.setVisibility(View.GONE);
                    }
                });


            }
        });
    }
}