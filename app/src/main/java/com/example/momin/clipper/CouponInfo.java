package com.example.momin.clipper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        dealName.setText("Original Price: $" + display.getOriginalPrice());

        TextView dealNewPrice =new TextView(this);
        dealName=(TextView)findViewById(R.id.dealNewPrice);
        dealName.setText("Deal Price: $" + display.getDealPrice());

        TextView dealMoneySaved = new TextView(this);
        dealName=(TextView)findViewById(R.id.dealMoneySaved);
        dealName.setText("Money saved: $" + (display.getDealSavings()));

        LinearLayout LL = new LinearLayout(this);
            LL.setBackgroundColor(Color.WHITE);
            LL.setOrientation(LinearLayout.VERTICAL);

        LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//        LL.addView(dealName);
//        LL.addView(dealOrigPrice);
//        LL.addView(dealNewPrice);
//        LL.addView(dealMoneySaved);
    }



}
