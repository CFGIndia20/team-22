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

public interface RetrofitInterface {

    @POST("login_manage.php/")
    Call<LogInReturn> login(@Body LogInRequest logInRequest);

    @POST("")
    Call<LogInReturn> addUser(@Body AddUserRequest addUserRequest);
}
