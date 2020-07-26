package com.markups.umeed.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.markups.umeed.R;
import com.markups.umeed.fragments.AddUserFragment;
import com.markups.umeed.fragments.AssignedTaskFragment;
import com.markups.umeed.fragments.ProfileFragment;

public class BottomNavManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_manager);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AssignedTaskFragment()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;

                switch(item.getItemId()) {
                    case R.id.addUser:
                        tempFragment = new AddUserFragment();
                        break;
                    case R.id.profile:
                        tempFragment=new ProfileFragment();
                        break;
                    case R.id.assignedTask:
                        tempFragment=new AssignedTaskFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tempFragment).commit();
                return true;
            }
        });
    }
}