package com.example.sergey.lesson2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.sergey.lesson2.abstrDao.HyperbolaDAO;
import com.example.sergey.lesson2.entity.Hyperbola;

public class MainActivity extends AppCompatActivity {

    HyperbolaDAO vh = HyperbolaDAO.getHyperbolaDAO();
    Button buttonY, buttonX;
    EditText x, y, k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (EditText) findViewById(R.id.textX);
        y = (EditText) findViewById(R.id.textY);
        k = (EditText) findViewById(R.id.textK);

        buttonY = (Button) findViewById(R.id.button);
        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hyperbola h = vh.valueX(update());
                Log.i("TEG", h.toString());
                if (h != null) {
                    x.setText(String.valueOf(h.getX()));
                    vh.save(h);
                }
            }
        });
        buttonX = (Button) findViewById(R.id.button2);
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hyperbola h = vh.valueY(update());
                Log.i("TEG", h.toString());
                if (h != null) {
                    y.setText(String.valueOf(h.getY()));
                    vh.save(h);
                }
            }
        });

    }

    Hyperbola update() {
        return new Hyperbola
                (Float.valueOf(String.valueOf(x.getText())),
                        Float.valueOf(String.valueOf(y.getText())),
                        Float.valueOf(String.valueOf(k.getText())));

    }
}
