package com.example.momin.clipper;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.example.momin.clipper.Coupon;
import com.example.momin.clipper.Store;
import com.example.momin.clipper.dummy.CouponContent2;
import com.example.momin.clipper.dummy.StoreContent;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by MLH-User on 1/16/2016.
 */
public class BackgroundStoreLister extends AsyncTask<Location, Void, Integer> {
    @SuppressLint("LongLogTag")
    @Override
    protected Integer doInBackground(Location... params) {
        if(StoreContent.ITEMS.size() == 0)
            try {
                double mLatitude = params[0].getLatitude();
                double mLongitude = params[0].getLongitude();
                double lat = mLatitude;
                double lon = mLongitude;

                int mileradius = 5;
                int displimit = 1000;
                String orderby = "radius";

                String query = "http://api.8coupons.com/v1/getdeals?key=d4aa04ccac274d92eb18748cd60271e1820913dde745898b18ac4219e1e19d7496f3e7c85a1c34d0b2a0cc3bfa6dff85";
                query += "&lat=" + lat;
                query += "&lon=" + lon;
                query += "&mileradius=" + mileradius;
                query += "&limit=" + displimit;
                query += "&orderby=" + orderby;

                String jsoncontent = null;

                URL url = null;
                try {
                    url = new URL(query);
                    Log.e("new implementation", "try url");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("new implementation", "catch url");
                }
                HttpURLConnection urc = null;
                try {
                    urc = (HttpURLConnection) url.openConnection();
                    Log.e("new implementation", "try urc");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("new implementation", "catch urc");
                }

                try {
                    InputStream in = new BufferedInputStream(urc.getInputStream());
                    jsoncontent = readStream(in);
                    Log.e("new implementation", "try input stream");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("new implementation", "catch input stream");
                } finally {
                    urc.disconnect();
                }
                Log.e("Info", jsoncontent);

                ArrayList<String> storeinfo = separateDeals(jsoncontent);
                System.out.println(storeinfo.get(1));
                ArrayList<Store> stores = new ArrayList<Store>();
                ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
                for (int i=0; i< storeinfo.size(); i++) {
                    stores.add(generateStore(storeinfo.get(i)));
                    allCoupons.add(generateCoupon(storeinfo.get(i)));
                }

                Log.e("stores 0", stores.get(0).getAddress());
                Log.e("stores 1", stores.get(1).getAddress());
                System.out.println(stores.get(0).getAddress());
                System.out.println(stores.get(1).getAddress());

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

                Log.e("After multiple coupon merge", Integer.toString(stores.size()));
                Log.e("new implementation", "before createList call");
                Collections.sort(stores);
                StoreContent.createList(stores);
                CouponContent2.createList(allCoupons);

            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
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

        String name = "";
        String address = "";
        int totalDeals = 0;
        String phone = null;
        double distance = 0;
        int categoryID = 0;
        int subcategoryID = 0;
        String state = null;
        String city = null;
        String zip = null;

        for (String j: storedetails) {
            System.out.println("String j: " + j);
            if (j.startsWith("\"name\":\"")) {
                name = j.substring(8, j.length() - 1);
            }
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
        String url = "";
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
                originalPrice = Double.parseDouble(j.substring(21, j.length() - 1));
            if (j.startsWith("\"dealPrice\":\""))
                dealPrice = Double.parseDouble(j.substring(13, j.length()-1));
            if (j.startsWith("\"URL\":\"")) {
                url = j.substring(7, j.length() - 1);
                url = url.replace("\\","");
            }
        }

        Coupon coupon = new Coupon(name,dealTitle,disclaimer,dealInfo,expirationDate,originalPrice,dealPrice,url);
        return coupon;
    }

}

