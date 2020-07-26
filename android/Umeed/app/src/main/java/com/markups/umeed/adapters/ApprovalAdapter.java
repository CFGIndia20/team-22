package com.markups.umeed.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.markups.umeed.R;
import com.markups.umeed.RetrofitInterface;
import com.markups.umeed.activities.BottomNavManagerActivity;
import com.markups.umeed.models.Approvals;
import com.markups.umeed.models.LogInReturn;
import com.markups.umeed.models.Result;
import com.markups.umeed.models.TasksAssigned;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApprovalAdapter extends RecyclerView.Adapter<ApprovalAdapter.viewholder> {
    ArrayList<Approvals>approvals;
    public ApprovalAdapter(ArrayList<Approvals> approvals) {
        this.approvals=approvals;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.recycler_layout2,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        viewholder.taskid.setText("Task Id- "+approvals.get(i).getTask_id());
        viewholder.product.setText("Product- "+approvals.get(i).getTypeName());
        viewholder.quantity.setText("Quantity- "+approvals.get(i).getWorker_uploaded_L0());
        viewholder.workerid.setText("Karigar Id- "+approvals.get(i).getWorker_id());
        final String worker_id=approvals.get(i).getWorker_id();
        viewholder.accept.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(final View view) {
               Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
                       GsonConverterFactory.create()).build();
               RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
               Call<LogInReturn> logInReturnCall=retrofitInterface.result(new Result(worker_id,1));
               logInReturnCall.enqueue(new Callback<LogInReturn>() {
                   @Override
                   public void onResponse(Call<LogInReturn> call, Response<LogInReturn> response) {
                       Toast.makeText(view.getContext(),"Submission Accepted",Toast.LENGTH_LONG).show();
                       Intent i=new Intent(view.getContext(), BottomNavManagerActivity.class);
                       view.getContext().startActivity(i);
                   }

                   @Override
                   public void onFailure(Call<LogInReturn> call, Throwable t) {
                       Toast.makeText(view.getContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
                       Intent i=new Intent(view.getContext(), BottomNavManagerActivity.class);
                       view.getContext().startActivity(i);
                   }
               });


           }
       });
        viewholder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
                        GsonConverterFactory.create()).build();
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                Call<LogInReturn> logInReturnCall=retrofitInterface.result(new Result(worker_id,0));
                logInReturnCall.enqueue(new Callback<LogInReturn>() {
                    @Override
                    public void onResponse(Call<LogInReturn> call, Response<LogInReturn> response) {
                        Toast.makeText(view.getContext(),"Submission rejected",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(view.getContext(), BottomNavManagerActivity.class);
                        view.getContext().startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<LogInReturn> call, Throwable t) {
                        Toast.makeText(view.getContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
                        Intent i=new Intent(view.getContext(), BottomNavManagerActivity.class);
                        view.getContext().startActivity(i);
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return approvals.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView taskid,product,quantity,workerid;
        ImageView accept,reject;
        CardView card;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            taskid=(TextView)itemView.findViewById(R.id.id);
            product=(TextView)itemView.findViewById(R.id.product);
            quantity=(TextView)itemView.findViewById(R.id.quantity);
            workerid=(TextView)itemView.findViewById(R.id.worker);
            card=(CardView)itemView.findViewById(R.id.card);
            accept=(ImageView)itemView.findViewById(R.id.accept);
            reject=(ImageView)itemView.findViewById(R.id.reject);
        }
    }
}
