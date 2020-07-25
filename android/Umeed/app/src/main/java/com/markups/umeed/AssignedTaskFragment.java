package com.markups.umeed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class AssignedTaskFragment extends Fragment {

    public AssignedTaskFragment() {
        // Required empty public constructor
    }
    SharedPreferences spref;
    TextView apology;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_assigned_task, container, false);
        spref = getActivity().getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        final ProgressBar pbar=v.findViewById(R.id.progressbar);
        Call<ArrayList<TasksAssigned>>arrayListCall= LogInActivity.retrofitInterface.taskAssigned(spref.getString("id","default"));
        arrayListCall.enqueue(new Callback<ArrayList<TasksAssigned>>() {
            @Override
            public void onResponse(Call<ArrayList<TasksAssigned>> call, Response<ArrayList<TasksAssigned>> response) {
                if(response.body()!=null) {
                    RecyclerView recycler = v.findViewById(R.id.recycler);
                    recycler.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    pbar.setVisibility(View.GONE);
                    recycler.setAdapter(new AssignedAdapter(response.body()));
                }
                else{
                    pbar.setVisibility(View.GONE);
                    apology.setVisibility(View.VISIBLE);
                    Toast.makeText(v.getContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TasksAssigned>> call, Throwable t) {
                pbar.setVisibility(View.GONE);
                apology.setVisibility(View.VISIBLE);
                Toast.makeText(v.getContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}