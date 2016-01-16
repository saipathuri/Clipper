package com.example.momin.clipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CouponInfo extends AppCompatActivity {

    public static String display = "null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CouponView sv = new CouponView(this, display);
        setContentView(sv);
     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);
    }



}
