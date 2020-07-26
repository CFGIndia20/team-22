package com.markups.umeed.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markups.umeed.R;
import com.markups.umeed.activities.TaskCompletionActivity;
import com.markups.umeed.models.TasksAssigned;

import java.util.ArrayList;

public class WAssignedAdapter extends RecyclerView.Adapter<WAssignedAdapter.viewholder> {
    ArrayList<TasksAssigned>tasksAssigneds;
    public WAssignedAdapter(ArrayList<TasksAssigned> tasksAssigneds) {
        this.tasksAssigneds=tasksAssigneds;

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
        viewholder.taskid.setText("Task Id- "+tasksAssigneds.get(i).getTask_id());
        viewholder.product.setText("Product- "+tasksAssigneds.get(i).getTypeName());
        viewholder.quantity.setText("Quantity- "+tasksAssigneds.get(i).getQuantity());
        viewholder.deadline.setText("End Date- "+tasksAssigneds.get(i).getEnd_date());
        final String taskid,product,quantity;
        taskid=tasksAssigneds.get(i).getTask_id();
        product=tasksAssigneds.get(i).getTypeName();
        quantity=tasksAssigneds.get(i).getQuantity();

        viewholder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskCompletionActivity.class);
                intent.putExtra("task_id", taskid);
                intent.putExtra("product",product);
                intent.putExtra("quantity",quantity);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasksAssigneds.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView taskid,product,quantity,deadline;
        CardView card;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            taskid=(TextView)itemView.findViewById(R.id.id);
            product=(TextView)itemView.findViewById(R.id.product);
            quantity=(TextView)itemView.findViewById(R.id.quantity);
            deadline=(TextView)itemView.findViewById(R.id.deadline);
            card=(CardView)itemView.findViewById(R.id.card);
        }
    }
}
