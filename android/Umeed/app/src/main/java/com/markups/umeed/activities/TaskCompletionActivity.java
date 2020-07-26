package com.markups.umeed.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.markups.umeed.R;
import com.markups.umeed.RetrofitInterface;
import com.markups.umeed.models.LogInReturn;
import com.markups.umeed.models.TaskCompletion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskCompletionActivity extends AppCompatActivity {
    AutoCompleteTextView taskAssignment_et_taskId,taskAssignment_et_product,taskAssignment_et_quantity,taskAssignment_et_grievance;
    private static final int PERMISSION_CODE =1000;
    private static final int IMAGE_CAPTURE_CODE =1001 ;
    Uri image_uri;
    TextView addUser_bt_signIn,addUser_bt_capture;
    String taskid,product;
    Integer quantity;
    SharedPreferences spref;
    ProgressBar taskAssignment_pg_signIn;
    RetrofitInterface retrofitInterface;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_completion);
        spref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        taskid = getIntent().getStringExtra("task_id");
        product = getIntent().getStringExtra("product");
        taskAssignment_et_product=findViewById(R.id.taskAssignment_et_product);
        taskAssignment_et_taskId=findViewById(R.id.taskAssignment_et_taskId);
        taskAssignment_et_quantity=findViewById(R.id.taskAssignment_et_quantity);
        taskAssignment_pg_signIn=findViewById(R.id.taskAssignment_pg_signIn);
        taskAssignment_et_grievance=findViewById(R.id.taskAssignment_et_grievance);
        addUser_bt_capture=findViewById(R.id.addUser_bt_capture);
        img=findViewById(R.id.img);
        addUser_bt_signIn=findViewById(R.id.addUser_bt_signIn);
        taskAssignment_et_taskId.setText(taskid);
        taskAssignment_et_product.setText(product);
        addUser_bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskAssignment_pg_signIn.setVisibility(View.VISIBLE);
                addUser_bt_signIn.setVisibility(View.GONE);
                Date todayDate = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String todayString = formatter.format(todayDate);
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sleepy-coast-63651.herokuapp.com/").addConverterFactory(
                        GsonConverterFactory.create()).build();
                retrofitInterface = retrofit.create(RetrofitInterface.class);
                Log.v("temp",taskid+"\n"+spref.getString("id","1")+"\n"+Integer.parseInt(taskAssignment_et_quantity.getText().toString())+"\n"+
                        product+"\n"+todayString+"\n"+taskAssignment_et_grievance.getText().toString());
                Call<LogInReturn>logInReturnCall=retrofitInterface.completeTask(new TaskCompletion(taskid,spref.getString("id","1"),Integer.parseInt(taskAssignment_et_quantity.getText().toString()),
                        product,todayString,taskAssignment_et_grievance.getText().toString()));
                logInReturnCall.enqueue(new Callback<LogInReturn>() {
                    @Override
                    public void onResponse(Call<LogInReturn> call, Response<LogInReturn> response) {
                        Toast.makeText(TaskCompletionActivity.this,"Task Progress Uploaded",Toast.LENGTH_LONG).show();
                        taskAssignment_pg_signIn.setVisibility(View.GONE);
                        addUser_bt_signIn.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onFailure(Call<LogInReturn> call, Throwable t) {
                    Toast.makeText(TaskCompletionActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
                        taskAssignment_pg_signIn.setVisibility(View.GONE);
                        addUser_bt_signIn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        addUser_bt_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CAMERA)==
                            PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                        String [] permission= {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISSION_CODE);
                    }
                    else
                    {
                        openCamera();
                    }
                }
                else{
                    openCamera();
                }
            }
        });


    }
    private void openCamera(){
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"from camera");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //camera
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(camera, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else{
                    Toast.makeText(this,"Denied",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //called when image captured
        if(resultCode==RESULT_OK){
            img.setImageURI(image_uri);
            try {
                //image to base64
                InputStream imageStream=getContentResolver().openInputStream(image_uri);
                Bitmap selectedImage= BitmapFactory.decodeStream(imageStream);
                String encodedImage=encodeImage(selectedImage);
                img.setImageBitmap(selectedImage);
//                // base64 to image
//                byte[] imgBytes = Base64.decode(encodedImage.getBytes(), Base64.DEFAULT);
//                Bitmap decodeImage=BitmapFactory.decodeByteArray(imgBytes,0,imgBytes.length);

                // save to file
                File file = new File(TaskCompletionActivity.this.getFilesDir(), "text");
                if (!file.exists()) {
                    file.mkdir();
                }
                File gpxfile = new File(file, "image");
                FileWriter writer = new FileWriter(gpxfile);
                writer.append(encodedImage);
                writer.flush();
                writer.close();
                Toast.makeText(TaskCompletionActivity.this, "Saved image in base64 format", Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private String encodeImage(Bitmap bm){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b=baos.toByteArray();
        String encImage = Base64.encodeToString(b,Base64.DEFAULT);
        return encImage;
    }
}