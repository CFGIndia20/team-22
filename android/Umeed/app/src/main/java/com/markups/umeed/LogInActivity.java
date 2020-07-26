package com.markups.umeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login_ed_phone=findViewById(R.id.login_ed_phone);
        login_ed_password=findViewById(R.id.login_ed_password);
        login_bt_login=findViewById(R.id.login_bt_login);
        login_pg_login=findViewById(R.id.login_pg_login);

        spref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        edit=spref.edit();
        login_bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_bt_login.setVisibility(View.GONE);
                login_pg_login.setVisibility(View.VISIBLE);
                phone=login_ed_phone.getText().toString();
                password=login_ed_password.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
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
                        {LogInReturn logInReturn=response.body();
                            edit.putString("id",response.body().getWorker_id());
                            edit.putString("contact",phone);
                            edit.putString("name",response.body().getName());
                            Log.v("test",response.body().getIs_manager());
                            if(logInReturn.getIs_manager().equals("n")){
                                edit.putString("type", "employee");
                                edit.commit();
                                startActivity(new Intent(LogInActivity.this,BottomNavKarigarActivity.class));
                                finish();
                        }
                        else{
                                edit.putString("type", "manager");
                                edit.commit();
                                startActivity(new Intent(LogInActivity.this,BottomNavManagerActivity.class));
                                finish();
                        }
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