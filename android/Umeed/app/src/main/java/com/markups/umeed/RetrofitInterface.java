package com.markups.umeed;


import com.markups.umeed.models.AddUserRequest;
import com.markups.umeed.models.LogInRequest;
import com.markups.umeed.models.LogInReturn;
import com.markups.umeed.models.TaskCompletion;
import com.markups.umeed.models.TasksAssigned;
import com.markups.umeed.models.Temp;
import com.markups.umeed.models.Worker;
import com.markups.umeed.models.WorkersAssigned;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("login_manage.php/")
    Call<LogInReturn> login(@Body LogInRequest logInRequest);

    @POST("add_user.php/")
    Call<LogInReturn> addUser(@Body AddUserRequest addUserRequest);

    @POST("manager_assigns_to_worker.php/")
    Call<Temp> assignWork(@Body WorkersAssigned workersAssigned);

    @POST("feedback_worker.php/")
    Call<LogInReturn> completeTask(@Body TaskCompletion taskCompletion);

    @GET("manager_tasks.php/")
    Call<ArrayList<TasksAssigned>> taskAssigned(@Query("worker_id")String worker_id);

    @GET("get_worker_task.php/")
    Call<ArrayList<TasksAssigned>> taskAssignedW(@Query("worker_id")String worker_id);

    @GET("worker_assigned_to_manager.php/")
    Call<ArrayList<Worker>> workerList(@Query("manager_id")String manager_id);




}
