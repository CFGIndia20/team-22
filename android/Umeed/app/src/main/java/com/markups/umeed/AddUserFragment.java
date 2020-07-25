package com.markups.umeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserFragment extends Fragment {
    public AddUserFragment() {
        // Required empty public constructor
    }
    Button addUser_bt_signIn;
    ProgressBar addUser_pg_signIn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_add_user, container, false);
        addUser_bt_signIn=v.findViewById(R.id.addUser_bt_signIn);
        addUser_pg_signIn=v.findViewById(R.id.addUser_pg_signIn);
//        addUser_bt_signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addUser_pg_signIn.setVisibility(View.VISIBLE);
//                addUser_bt_signIn.setVisibility(View.GONE);
//                Call<LogInReturn>logInReturnCall=LogInActivity.retrofitInterface.addUser(new AddUserRequest());
//                logInReturnCall.enqueue(new Callback<LogInReturn>() {
//                    @Override
//                    public void onResponse(Call<LogInReturn> call, Response<LogInReturn> response) {
//                        if(response.body()==null)
//                        {
//                            Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_LONG).show();
//                            addUser_pg_signIn.setVisibility(View.GONE);
//                            addUser_bt_signIn.setVisibility(View.VISIBLE);
//                        }
//                        else{
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LogInReturn> call, Throwable t) {
//                        Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
//                        addUser_pg_signIn.setVisibility(View.GONE);
//                        addUser_bt_signIn.setVisibility(View.VISIBLE);
//                    }
//                });
//            }
//        });

        return v;
    }
}