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

    public Store(String name, String address, int totalDeals, String phone, double distance, ArrayList<Coupon> coupons, int categoryID, int subcategoryID) {
        this.name = name;
        this.address = address;
        this.totalDeals = totalDeals;
        this.phone = phone;
        this.distance = distance;
        this.coupons = coupons;
        this.categoryID = categoryID;
        this.subcategoryID = subcategoryID;
    }

    public String getName() {
        return name;
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
