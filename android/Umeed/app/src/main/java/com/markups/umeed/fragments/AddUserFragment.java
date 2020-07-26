package com.markups.umeed.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.markups.umeed.R;
import com.markups.umeed.RetrofitInterface;
import com.markups.umeed.models.AddUserRequest;
import com.markups.umeed.models.LogInReturn;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddUserFragment extends Fragment {
    public AddUserFragment() {
        // Required empty public constructor
    }
    TextView addUser_bt_signIn;
    ProgressBar addUser_pg_signIn;
    AutoCompleteTextView addUser_et_name, addUser_et_phoneNumber, addUser_et_location,addUser_et_managerId,addUser_et_password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
                GsonConverterFactory.create()).build();
        final RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        View v= inflater.inflate(R.layout.fragment_add_user, container, false);
        addUser_bt_signIn=v.findViewById(R.id.addUser_bt_signIn);
        addUser_pg_signIn=v.findViewById(R.id.addUser_pg_signIn);
        addUser_et_location=v.findViewById(R.id.addUser_et_location);
        addUser_et_phoneNumber=v.findViewById(R.id.addUser_et_phoneNumber);
        addUser_et_name=v.findViewById(R.id.addUser_et_name);
        addUser_et_managerId=v.findViewById(R.id.addUser_et_managerId);
        addUser_et_password=v.findViewById(R.id.addUser_et_password);
        addUser_bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser_pg_signIn.setVisibility(View.VISIBLE);
                addUser_bt_signIn.setVisibility(View.GONE);
                Call<LogInReturn>logInReturnCall=retrofitInterface.addUser(new AddUserRequest(addUser_et_name.getText().toString(),addUser_et_password.getText().toString(),
                        Integer.parseInt(addUser_et_phoneNumber.getText().toString()),Integer.parseInt(addUser_et_location.getText().toString()), Integer.parseInt(addUser_et_managerId.getText().toString())));
                logInReturnCall.enqueue(new Callback<LogInReturn>() {
                    @Override
                    public void onResponse(Call<LogInReturn> call, Response<LogInReturn> response) {
                        if(response.body()==null)
                        {
                            Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                            addUser_pg_signIn.setVisibility(View.GONE);
                            addUser_bt_signIn.setVisibility(View.VISIBLE);
                        }
                        else{
Toast.makeText(getActivity(),"User added successfully",Toast.LENGTH_LONG).show();
                            addUser_pg_signIn.setVisibility(View.GONE);
                            addUser_bt_signIn.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<LogInReturn> call, Throwable t) {
                        Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
                        addUser_pg_signIn.setVisibility(View.GONE);
                        addUser_bt_signIn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        return v;
    }
}