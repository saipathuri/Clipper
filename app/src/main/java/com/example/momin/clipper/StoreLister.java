package com.example.momin.clipper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Owner on 1/16/16.
 */
public class StoreLister {

//    private Context myContext;
//    private Geocoder geocoder;
//
//    public StoreLister(Context context) {
//        myContext = context;
//        geocoder = new Geocoder(myContext, Locale.getDefault());
//    }

    @SuppressLint("LongLogTag")
    public static ArrayList<Store> generateListing(Location l) {

        if (l == null)
            return null;
        else {

            double lat = l.getLatitude();
            double lon = l.getLongitude();

            int mileradius = 2;
            int displimit = 1000;
            String orderby = "radius";

            String query = "http://api.8coupons.com/v1/getdeals?key=7706d583294296431e81abac1b84d57a3ff88f5b710536108616ae1a0fa4b64919e2fc523bd3a1bdbbab66947a0d67a5";
            query += "&lat=" + lat;
            query += "&lon=" + lon;
            query += "&mileradius=" + mileradius;
            query += "&limit=" + displimit;
            query += "&orderby=" + orderby;

//            DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
//            HttpPost httppost = new HttpPost(http://someJSONUrl/jsonWebService);
//// Depends on your web service
//            httppost.setHeader("Content-type", "application/json");
//
//            InputStream inputStream = null;
//            String result = null;
//            try {
//                HttpResponse response = httpclient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//
//                inputStream = entity.getContent();
//                // json is UTF-8 by default
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
//                StringBuilder sb = new StringBuilder();
//
//                String line = null;
//                while ((line = reader.readLine()) != null)
//                {
//                    sb.append(line + "\n");
//                }
//                result = sb.toString();
//            } catch (Exception e) {
//                // Oops
//            }
//            finally {
//                try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
//            }


//            try {
//                List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
//                if (addresses != null) {
//                    Address returnedAddress = addresses.get(0);
//                    String zip = returnedAddress.getPostalCode();
//
//                }
//                else {
//                    Log.w("My Curr location address", "No Address returned");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10001&mileradius=20&limit=1000&orderby=radius&categoryid=2,6
            //http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10022&mileradius=20&limit=500&userid=18381
        }
        return null;
    }

}
