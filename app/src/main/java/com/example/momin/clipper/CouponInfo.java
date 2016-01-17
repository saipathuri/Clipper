package com.example.momin.clipper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.*;

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

        TextView dealName =new TextView(this);
        dealName=(TextView)findViewById(R.id.dealName);
        dealName.setText(display.getDealTitle());

        TextView dealOrigPrice =new TextView(this);
        dealName=(TextView)findViewById(R.id.dealOrigPrice);
        dealName.setText("Original Price: $" + String.format("%.2f", display.getOriginalPrice()));

        TextView dealNewPrice =new TextView(this);
        dealName=(TextView)findViewById(R.id.dealNewPrice);
        dealName.setText("Deal Price: $" + String.format("%.2f",display.getDealPrice()));

        TextView dealMoneySaved = new TextView(this);
        dealName=(TextView)findViewById(R.id.dealMoneySaved);
        dealName.setText("Money saved: $" + String.format("%.2f",(display.getDealSavings())));

        TextView dealURL = new TextView(this);

        Button btn = (Button) findViewById(R.id.loadCoupon);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActionBrowse.class);
                intent.putExtra("URL", display.getUrl());
                startActivity(intent);
            }
                               });

//        dealURL = (TextView) findViewById(R.id.dealURL);
//        dealURL.setText("Get the deal here: " + display.getUrl());
//        Linkify.addLinks(dealURL, Linkify.WEB_URLS);
    }



}
