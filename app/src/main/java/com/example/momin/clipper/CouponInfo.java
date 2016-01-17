package com.example.momin.clipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CouponInfo extends AppCompatActivity {

    public static Coupon display = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // CouponView sv = new CouponView(this, display);
        setContentView(R.layout.coupon_info_activity);
        TextView t=new TextView(this);

        t=(TextView)findViewById(R.id.dealName);
        t.setText(display.getDealTitle());

    }



}
