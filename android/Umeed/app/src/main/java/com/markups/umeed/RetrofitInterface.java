package com.markups.umeed;


import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("login_manage.php/")
    Call<LogInReturn> login(@Body LogInRequest logInRequest);

    @POST("add_user.php/")
    Call<LogInReturn> addUser(@Body AddUserRequest addUserRequest);

    @POST("manager_assigns_to_worker.php/")
    Call<Temp> assignWork(@Body WorkersAssigned workersAssigned);

    @GET("manager_tasks.php/")
    Call<ArrayList<TasksAssigned>> taskAssigned(@Query("worker_id")String worker_id);

    @GET("worker_assigned_to_manager.php/")
    Call<ArrayList<Worker>> workerList(@Query("manager_id")String manager_id);


}
