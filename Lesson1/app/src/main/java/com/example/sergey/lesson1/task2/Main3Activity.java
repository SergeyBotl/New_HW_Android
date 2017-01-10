package com.example.sergey.lesson1.task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sergey.lesson1.R;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class Main3Activity extends AppCompatActivity {
    Vector<String> vector = new Vector<>(Arrays.asList("w", "c", "d", "e"));

     UserDAO userDAO=UserDAO.getUserDao();

    @Override//полиморфизм
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView text=(TextView) findViewById(R.id.textView);
        text.setText("Hello teacher"); // инкапсуляция


        Enumeration<String> e = vector.elements();
        while (e.hasMoreElements()) {
            e.nextElement();
        }

    }
   Main3Activity(){
        userDAO.save(new User(1,"Bob"));
    }

}
