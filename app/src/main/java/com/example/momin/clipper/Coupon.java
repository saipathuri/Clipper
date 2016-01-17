package com.example.momin.clipper;

/**
 * Created by Owner on 1/16/16.
 */
public class Coupon {

    private String name;
    private String dealTitle;
    private String disclaimer;
    private String dealInfo;
    private String expirationDate;
    private double originalPrice;
    private double dealPrice;
    private double dealSavings;
    private double dealDiscountPercent;

    public Coupon(String name, String dealTitle, String disclaimer, String dealInfo, String expirationDate, double originalPrice, double dealPrice) {
        this.name = name;
        this.dealTitle = dealTitle;
        this.disclaimer = disclaimer;
        this.dealInfo = dealInfo;
        this.expirationDate = expirationDate;
        this.originalPrice = originalPrice;
        this.dealPrice = dealPrice;
    }


    public String getName() {
        return name;
    }


    public double getDealDiscountPercent() {
        return (originalPrice-dealPrice)/originalPrice*100;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public double getDealSavings() {
        return originalPrice-dealPrice;
    }

    public String getDealTitle() {
        return dealTitle;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getDealInfo() {
        return dealInfo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }




}
