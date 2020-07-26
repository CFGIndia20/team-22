package com.markups.umeed.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.markups.umeed.R;
import com.markups.umeed.RetrofitInterface;
import com.markups.umeed.models.Temp;
import com.markups.umeed.models.Worker;
import com.markups.umeed.models.WorkersAssigned;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskAssignmentActivity extends AppCompatActivity {
AutoCompleteTextView taskAssignment_et_taskId,taskAssignment_et_product,taskAssignment_et_quantity;
Spinner taskAssignment_et_worker;
TextView addUser_bt_signIn;
String taskid,product;
    Integer quantity;
    SharedPreferences spref;
    ProgressBar taskAssignment_pg_signIn;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assignment);
        spref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        taskid = getIntent().getStringExtra("task_id");
        product = getIntent().getStringExtra("product");
        taskAssignment_et_product=findViewById(R.id.taskAssignment_et_product);
        taskAssignment_et_taskId=findViewById(R.id.taskAssignment_et_taskId);
        taskAssignment_et_quantity=findViewById(R.id.taskAssignment_et_quantity);
        taskAssignment_pg_signIn=findViewById(R.id.taskAssignment_pg_signIn);
        addUser_bt_signIn=findViewById(R.id.addUser_bt_signIn);
        taskAssignment_et_worker=findViewById(R.id.taskAssignment_et_worker);
        taskAssignment_et_taskId.setText(taskid);
        taskAssignment_et_product.setText(product);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
                GsonConverterFactory.create()).build();
         retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ArrayList<Worker>>worker=retrofitInterface.workerList(spref.getString("id","100"));
        worker.enqueue(new Callback<ArrayList<Worker>>() {
            @Override
            public void onResponse(Call<ArrayList<Worker>> call, Response<ArrayList<Worker>> response) {
                final ArrayList<String> abc=new ArrayList<>();
                final ArrayList<String> def=new ArrayList<>();
                for(Worker re:response.body()){
                        abc.add(re.getName());
                        def.add(re.getWorker_id());

                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(TaskAssignmentActivity.this, android.R.layout.simple_spinner_item, def);
                taskAssignment_et_worker.setAdapter(dataAdapter);
                addUser_bt_signIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quantity=Integer.parseInt(taskAssignment_et_quantity.getText().toString());
                        taskAssignment_pg_signIn.setVisibility(View.VISIBLE);
                        addUser_bt_signIn.setVisibility(View.GONE);
                        Log.v("temp",""+taskid+"\n"+product+"\n"+
                                taskAssignment_et_quantity.getText().toString()+"\n"+taskAssignment_et_worker.getSelectedItem().toString()+"\n"+spref.getString("id","10"));
                        Call<Temp>workersAssignedCall=retrofitInterface.assignWork(new WorkersAssigned(taskid,product,
                                taskAssignment_et_quantity.getText().toString(),taskAssignment_et_worker.getSelectedItem().toString(),spref.getString("id","10")));
                        workersAssignedCall.enqueue(new Callback<Temp>() {
                            @Override
                            public void onResponse(Call<Temp> call, Response<Temp> response) {
                                Toast.makeText(TaskAssignmentActivity.this,"Work allocated",Toast.LENGTH_LONG).show();
                                taskAssignment_pg_signIn.setVisibility(View.GONE);
                                addUser_bt_signIn.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFailure(Call<Temp> call, Throwable t) {
                                Toast.makeText(TaskAssignmentActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
                                taskAssignment_pg_signIn.setVisibility(View.GONE);
                                addUser_bt_signIn.setVisibility(View.VISIBLE);


                            }
                        });

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<Worker>> call, Throwable t) {
Toast.makeText(TaskAssignmentActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}