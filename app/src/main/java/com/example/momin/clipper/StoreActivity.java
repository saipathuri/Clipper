package com.example.momin.clipper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.momin.clipper.dummy.StoreContent;

public class StoreActivity extends Activity implements StoreSpecificCoupons.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        StoreSpecificCoupons sc = new StoreSpecificCoupons();
        fragmentTransaction.add(R.id.lists, sc,"Hello");
        fragmentTransaction.commit();
    }
    public void onListFragmentInteraction(StoreContent.StoreItem item) {
        Intent intent = new Intent(this, CouponInfo.class);
        CouponInfo.display = item.id;
        startActivity(intent);
    }
}
