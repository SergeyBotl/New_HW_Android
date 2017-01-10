package com.example.sergey.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class StartActivity extends AppCompatActivity {
   private ImageView imageView;
   private Button base64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().setTitle("Picasso");
        imageView = (ImageView) findViewById(R.id.imageView);
        Log.d("tag", "onCreate");
       // base64 = (Button) findViewById(R.id.buttonToBase);
    }

    void onclick(View v) {
        String fileName = "image.jpeg";
        File baseDir = null;
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            baseDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "");
            Toast.makeText(this, baseDir + File.separator + fileName, Toast.LENGTH_LONG).show();
        }
        Picasso.with(this)
                .load(new File(baseDir + File.separator + fileName))
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_activity, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_to_grid_view:
                intent = new Intent(this, GridActivity.class);
                startActivity(intent);
                return true;
           /* case R.id.acnion_to_web_view:
                //запускаем новое Активити с использованием Intent-фильтров
                // (передаем адрес сайта, который нужно запустить)
                //не указываем явно, какое именно Активити нужно запустить.
                // Андроид предоставит нам меню выбора, где будут отображены
                // все возможные Активити/приложения, способные обрабатывать подобные запросы.
                startActivity(new Intent(Intent.ACTION_VIEW,
                        //Uri.parse("http://google.ru")));
                        Uri.parse("https://www.google.com.ua/search?q=image&prmd=ivn&source=lnms&tbm=isch&sa=X&ved=0ahUKEwj43dKzpLPRAhXCXiwKHcYsCwkQ_AUIBygB&biw=384&bih=511&dpr=2")));

                return true;*/
            case R.id.acnion_to_my_web_view:
                intent = new Intent(this, MyWebActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }


}