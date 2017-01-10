package com.example.sergey.lesson2_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mIn;
    TextView mOut;
    Button mBtnCheck;
    String toChar;
    int countInt, countString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIn = (EditText) findViewById(R.id.editText);
        mOut = (TextView) findViewById(R.id.textView2);

        mBtnCheck = (Button) findViewById(R.id.button);
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countInt = 0;
                countString = 0;
                StringBuilder sb = new StringBuilder();
                toChar = mIn.getText().toString();
                for (int i = 0; i < toChar.length(); i++) {
                    if (isNumber(toChar.charAt(i))) {
                        sb.append(toChar.charAt(i));
                        if (toChar.length() - 1 == i || !isNumber(toChar.charAt(i + 1))) {
                            sb.append(" Int\n ");
                            countInt++;
                        }
                    } else {
                        if (countString == 0) {
                            sb.append(toChar.charAt(i)).append(" String\n");
                            countString++;
                        } else {
                            sb.append(toChar.charAt(i)).append(" String \n");
                              countString++;
                        }
                    }
                }
                mOut.setText(sb.toString() + "\n" + "Стрингов: " + countString + "  Интов: " + countInt);
                 Toast.makeText(getApplicationContext(), sb.toString() + "\n" + "Стрингов: " + countString + "  Интов: " + countInt, Toast.LENGTH_LONG).show();
            }
        });

    }

    boolean isNumber(char c) {
        return (c > 47 && c < 58);
    }

}
