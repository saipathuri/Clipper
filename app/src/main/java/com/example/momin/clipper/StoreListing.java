package com.example.momin.clipper;

import android.location.Geocoder;
import android.location.Location;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Owner on 1/16/16.
 */
public class StoreListing {

    public ArrayList<Store> generateListing(Location l) {

        if (l == null)
            return null;
        else {

            double lat = l.getLatitude();
            double lng = l.getLongitude();

            String categories;
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10001&mileradius=20&limit=1000&orderby=radius&categoryid=2,6
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10022&mileradius=20&limit=500&userid=18381
        }
        return null;
    }

}
