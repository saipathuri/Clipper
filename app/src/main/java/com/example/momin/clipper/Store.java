package com.example.momin.clipper;

import java.util.ArrayList;

/**
 * Created by Owner on 1/16/16.
 */
public class Store {

    private String name;
    private String address;
    private int totalDeals;
    private String phone;
    private double distance;
    private ArrayList<Coupon> coupons;
    private int categoryID;
    private int subcategoryID;
    private String state;
    private String city;
    private String zip;



    public Store(String name, String address, int totalDeals, String phone,
                 double distance, int categoryID, int subcategoryID, String state,
                 String city, String zip) {
        this.name = name;
        this.address = address;
        this.totalDeals = totalDeals;
        this.phone = phone;
        this.distance = distance;
        this.categoryID = categoryID;
        this.subcategoryID = subcategoryID;
        this.state = state;
        this.city = city;
        this.zip = zip;
        coupons = new ArrayList<Coupon>();
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getName() {
        return name;
    }

    public void addCoupon(Coupon c) {
        coupons.add(c);
    }

    public String getAddress() {
        return address;
    }

    public int getTotalDeals() {
        return totalDeals;
    }

    public String getPhone() {
        return phone;
    }

    public double getDistance() {
        return distance;
    }

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getSubcategoryID() {
        return subcategoryID;
    }
}
