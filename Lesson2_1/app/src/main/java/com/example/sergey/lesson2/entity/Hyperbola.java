package com.example.sergey.lesson2.entity;

import android.text.Editable;
import android.widget.EditText;

public class Hyperbola {

    private float x;
    private float y;
    private float k;

    public Hyperbola(float x, float y, float k) {
        this.x = x;
        this.y = y;
        this.k = k;
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }



    public float getK() {
        return k;
    }


    @Override
    public String toString() {
        return "Hyperbola{" +
                "x=" + x +
                ", y=" + y +
                ", k=" + k +
                '}';
    }
}

