package com.markups.umeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPreferences spref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        if(spref.getString("type","init").equals("init")){
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            if(spref.getString("type","default").equals("manager")){
                startActivity(new Intent(MainActivity.this, BottomNavManagerActivity.class));
                finish();
            }
            else{

            }

        }
    }
}