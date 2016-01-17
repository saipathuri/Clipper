package com.example.momin.clipper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SyncStatusObserver;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.example.momin.clipper.dummy.StoreContent;
import com.example.momin.clipper.dummy.StoreContent.*;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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

    public static void generateListing(Location l) {


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

        String jsoncontent = null;

        URL url = null;
        HttpURLConnection urc = null;
        try {
            url = new URL(query);
            urc = (HttpURLConnection) url.openConnection();
            InputStream in = urc.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                jsoncontent += current;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            urc.disconnect();
        }
//        try {
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            urc = (HttpURLConnection) url.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            InputStream in = new BufferedInputStream(urc.getInputStream());
//            jsoncontent = readStream(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            urc.disconnect();
//        }

        ArrayList<String> storeinfo = separateDeals(jsoncontent);
        ArrayList<Store> stores = new ArrayList<Store>();
        ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
        for (int i=0; i< storeinfo.size(); i++) {
            stores.add(generateStore(storeinfo.get(i)));
            allCoupons.add(generateCoupon(storeinfo.get(i)));
        }

        stores.remove(0);
        allCoupons.remove(0);

        for (Store s: stores) {
            String name = s.getName();
            for (Coupon c: allCoupons) {
                if (name.equals(c.getName())) {
                    s.addCoupon(c);
                }
            }
        }

        Log.i("StoreLister Updated", jsoncontent);
        StoreContent.createList(stores);

    }

    private static String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    private static ArrayList<String> separateDeals(String s){
        StringTokenizer st = new StringTokenizer(s,"{");
        ArrayList<String> storeinfo = new ArrayList<String>();

        while (st.hasMoreTokens()) {
            storeinfo.add(st.nextToken());
        }

        return storeinfo;

    }

    public static Store generateStore(String s) {

        StringTokenizer dets = new StringTokenizer(s,",");
        ArrayList<String> storedetails = new ArrayList<String>();
        while (dets.hasMoreTokens()) {
            storedetails.add(dets.nextToken());
        }
        String name = null;
        String address = null;
        int totalDeals = 0;
        String phone = null;
        double distance = 0;
        int categoryID = 0;
        int subcategoryID = 0;
        String state = null;
        String city = null;
        String zip = null;

        for (String j: storedetails) {
            if (j.startsWith("\"name\":\""))
                name = j.substring(8, j.length()-1);
            if (j.startsWith("\"address\":\""))
                address = j.substring(11, j.length()-1);
            if (j.startsWith("\"phone\":\""))
                phone = j.substring(9, j.length()-1);
            if (j.startsWith("\"distance\":\""))
                distance = Double.parseDouble(j.substring(12, j.length()-1));
            if (j.startsWith("\"categoryID\":\""))
                categoryID = Integer.parseInt(j.substring(14, j.length()-1));
            if (j.startsWith("\"subcategoryID\":\""))
                subcategoryID = Integer.parseInt(j.substring(17, j.length()-1));
            if (j.startsWith("\"totalDealsInThisStore\":\""))
                totalDeals = Integer.parseInt(j.substring(25, j.length()-1));
            if (j.startsWith("\"state\":\""))
                state = j.substring(9, j.length()-1);
            if (j.startsWith("\"city\":\""))
                city = j.substring(8, j.length()-1);
            if (j.startsWith("\"zip\":\""))
                zip = j.substring(7, j.length()-1);
        }

        Store store = new Store(name,address,totalDeals,phone,distance,categoryID,subcategoryID,state,city,zip);
        return store;
    }

    public static Coupon generateCoupon(String s) {
        StringTokenizer dets = new StringTokenizer(s,",");
        ArrayList<String> details = new ArrayList<String>();
        while (dets.hasMoreTokens()) {
            details.add(dets.nextToken());
        }
        String name = null;
        String dealTitle = null;
        String disclaimer = null;
        String dealInfo = null;
        String expirationDate = null;
        double originalPrice = 0;
        double dealPrice = 0;
        for (String j: details) {
            if (j.startsWith("\"name\":\""))
                name = j.substring(8, j.length()-1);
            if (j.startsWith("\"dealTitle\":\""))
                dealTitle = j.substring(13, j.length()-1);
            if (j.startsWith("\"disclaimer\":\""))
                disclaimer = j.substring(14, j.length()-1);
            if (j.startsWith("\"dealinfo\":\""))
                dealInfo = j.substring(12, j.length()-1);
            if (j.startsWith("\"expirationDate\":\""))
                expirationDate = j.substring(18, j.length()-1);
            if (j.startsWith("\"dealOriginalPrice\":\""))
                originalPrice = Double.parseDouble(j.substring(21, j.length()-1));
            if (j.startsWith("\"dealPrice\":\""))
                dealPrice = Double.parseDouble(j.substring(13, j.length()-1));
        }

        Coupon coupon = new Coupon(name,dealTitle,disclaimer,dealInfo,expirationDate,originalPrice,dealPrice);
        return coupon;
    }

}


//DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
//HttpPost httppost = new HttpPost(http://someJSONUrl/jsonWebService);
////Depends on your web service
//httppost.setHeader("Content-type", "application/json");
//
//InputStream inputStream = null;
//String result = null;
//try {
//  HttpResponse response = httpclient.execute(httppost);
//  HttpEntity entity = response.getEntity();
//
//  inputStream = entity.getContent();
//  // json is UTF-8 by default
//  BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
//  StringBuilder sb = new StringBuilder();
//
//  String line = null;
//  while ((line = reader.readLine()) != null)
//  {
//      sb.append(line + "\n");
//  }
//  result = sb.toString();
//} catch (Exception e) {
//  // Oops
//}
//finally {
//  try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
//}


//try {
//  List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
//  if (addresses != null) {
//      Address returnedAddress = addresses.get(0);
//      String zip = returnedAddress.getPostalCode();
//
//  }
//  else {
//      Log.w("My Curr location address", "No Address returned");
//  }
//} catch (IOException e) {
//  e.printStackTrace();
//}



//http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10001&mileradius=20&limit=1000&orderby=radius&categoryid=2,6
//http://api.8coupons.com/v1/getdeals?key=XYZ&zip=10022&mileradius=20&limit=500&userid=18381

