package com.markups.umeed.fragments;

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

import com.markups.umeed.R;
import com.markups.umeed.RetrofitInterface;
import com.markups.umeed.adapters.ApprovalAdapter;
import com.markups.umeed.adapters.WAssignedAdapter;
import com.markups.umeed.models.Approvals;
import com.markups.umeed.models.TasksAssigned;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class RateFragment extends Fragment {

    public RateFragment() {
        // Required empty public constructor
    }

    SharedPreferences spref;
    TextView apology;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_rate, container, false);
        apology=v.findViewById(R.id.apology);
        spref = getActivity().getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        final ProgressBar pbar=v.findViewById(R.id.progressbar);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
                GsonConverterFactory.create()).build();
        final RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ArrayList<Approvals>>arrayListCall= retrofitInterface.approvalList(spref.getString("id","default"));
        arrayListCall.enqueue(new Callback<ArrayList<Approvals>>() {
            @Override
            public void onResponse(Call<ArrayList<Approvals>> call, Response<ArrayList<Approvals>> response) {
                if(response.body()!=null) {
                    RecyclerView recycler = v.findViewById(R.id.recycler);
                    recycler.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    pbar.setVisibility(View.GONE);
                    recycler.setAdapter(new ApprovalAdapter(response.body()));
                }
                else{
                    pbar.setVisibility(View.GONE);
                    apology.setVisibility(View.VISIBLE);
                    Toast.makeText(v.getContext(),"Nothing to review",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Approvals>> call, Throwable t) {
                pbar.setVisibility(View.GONE);
                apology.setVisibility(View.VISIBLE);
                Toast.makeText(v.getContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}