package com.example.sergey.lesson1.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sergey.lesson1.R;

public class Main2Activity extends AppCompatActivity {

    Button mButonCount;
    int count;
    TextView mTextView;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mButonCount = (Button) findViewById(R.id.buttonCount);
        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
       // mTextView.setText("Count: ");

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after == 11) {
                mTextView.setText("Count: " + countPlus());
            }
                if (after == 22) {
                    mTextView.setText("I love GoIT so much  ");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int b = Integer.valueOf(s.toString());
                    insert(b);
                } catch (Throwable e) {
                }
            }
        });
    }

    void pluseCount(View view){

        mTextView.setText("Count: " + countPlus());
    }

    int countPlus() {
        return count++;
    }

    void insert(int b) {
        StringBuilder sb = new StringBuilder();
        int  a = 0, c = 1, d;
        for (int i = 0; i < b; i++) {
            d = a + c;
            a = c;
            c = d;
            sb.append(a + ", ");
        }
        mTextView.setText(sb.toString());
    }

}