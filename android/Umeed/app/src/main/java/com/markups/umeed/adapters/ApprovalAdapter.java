package com.markups.umeed.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.markups.umeed.R;
import com.markups.umeed.models.Approvals;
import com.markups.umeed.models.TasksAssigned;

import java.util.ArrayList;

public class ApprovalAdapter extends RecyclerView.Adapter<ApprovalAdapter.viewholder> {
    ArrayList<Approvals>approvals;
    public ApprovalAdapter(ArrayList<Approvals> approvals) {
        this.approvals=approvals;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.recycler_layout,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, final int i) {
        viewholder.taskid.setText("Task Id- "+approvals.get(i).getTask_id());
        viewholder.product.setText("Product- "+approvals.get(i).getTypeName());
        viewholder.quantity.setText("Quantity- "+approvals.get(i).getWorker_uploaded_L0());
        viewholder.workerid.setText("Karigar Id- "+approvals.get(i).getWorker_id());
       viewholder.accept.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
        viewholder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            workerid=(TextView)itemView.findViewById(R.id.deadline);
            card=(CardView)itemView.findViewById(R.id.card);
            accept=(ImageView)itemView.findViewById(R.id.accept);
            reject=(ImageView)itemView.findViewById(R.id.reject);
        }
    }
}
