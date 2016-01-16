package com.example.momin.clipper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Owner on 1/16/16.
 */
public class StoreLister {

    public ArrayList<Store> generateListing(Location l) {

        if (l == null)
            return null;
        else {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            double lat = l.getLatitude();
            double lng = l.getLongitude();
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);


            String categories;
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10001&mileradius=20&limit=1000&orderby=radius&categoryid=2,6
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10022&mileradius=20&limit=500&userid=18381
        }
        return null;
    }

    @SuppressLint("LongLogTag")
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", "" + strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

}
