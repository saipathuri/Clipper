package com.example.momin.clipper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Owner on 1/16/16.
 */
public class StoreLister {

    private Context myContext;
    private Geocoder geocoder;

    public StoreLister(Context context) {
        myContext = context;
        geocoder = new Geocoder(myContext, Locale.getDefault());
    }

    @SuppressLint("LongLogTag")
    public ArrayList<Store> generateListing(Location l) {

        if (l == null)
            return null;
        else {

            double lat = l.getLatitude();
            double lng = l.getLongitude();
            try {
                List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                if (addresses != null) {
                    Address returnedAddress = addresses.get(0);
                    String zip = returnedAddress.getPostalCode();

                }
                else {
                    Log.w("My Curr location address", "No Address returned");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



            String categories;
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10001&mileradius=20&limit=1000&orderby=radius&categoryid=2,6
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10022&mileradius=20&limit=500&userid=18381
        }
        return null;
    }

}
