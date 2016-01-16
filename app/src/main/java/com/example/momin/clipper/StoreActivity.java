package com.example.momin.clipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.momin.clipper.dummy.DummyContent;

import java.util.Locale;

public class StoreActivity extends AppCompatActivity implements StoreSpecificCoupons.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        StoreSpecificCoupons sc = new StoreSpecificCoupons();
        fragmentTransaction.add(R.id.lists, sc,"Hello");
        fragmentTransaction.commit();
    }
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Intent intent = new Intent(this, CouponInfo.class);
        CouponInfo.display = item.id;
        startActivity(intent);
    }
}
