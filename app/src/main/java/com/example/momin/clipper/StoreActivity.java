package com.example.momin.clipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.momin.clipper.dummy.CouponContent;

public class StoreActivity extends AppCompatActivity implements StoreSpecificCoupons.OnListFragmentInteractionListener {

    public static Store store = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TextView shopName = new TextView(this);
        shopName = (TextView)findViewById(R.id.shopName);
        shopName.setText(store.getName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        StoreSpecificCoupons sc = new StoreSpecificCoupons();
        fragmentTransaction.add(R.id.lists, sc,"Hello");
        fragmentTransaction.commit();
    }
    public void onListFragmentInteraction(CouponContent.CouponItem item) {
        Intent intent = new Intent(this, CouponInfo.class);
        CouponInfo.display = item.coupon;
        startActivity(intent);
    }
}
