package com.example.sergey.lesson1.task2_1_oop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sergey.lesson1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText1, editText2;
    private List<Pizza> list = new ArrayList<>();
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList("thick", "thin"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        Button buttonRead = (Button) findViewById(R.id.buttonRead);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().length() != 0 && editText2.getText().toString().length() != 0) {
                    Thickness t = Thickness.valueOf(spinner.getSelectedItem().toString());
                    Log.i("TAG", "ID " + spinner.getSelectedItemId() + " Position " + spinner.getSelectedItemPosition());
                    list.add(new Pizza(editText1.getText().toString(),
                            Float.valueOf(editText2.getText().toString()),
                            t));
                }
            }
        });
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null)
                    textView.setText(list.toString());
            }
        });
    }
}
