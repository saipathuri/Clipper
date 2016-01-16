package com.example.momin.clipper;

/**
 * Created by Owner on 1/16/16.
 */
public class Coupon {

    private String dealTitle;
    private String disclaimer;
    private String dealInfo;
    private String expirationDate;
    private double originalPrice;
    private double dealPrice;
    private double dealSavings;
    private double dealDiscountPercent;

    public Coupon(String dealTitle, String disclaimer, String dealInfo, String expirationDate, double originalPrice, double dealPrice, double dealSavings, double dealDiscountPercent) {
        this.dealTitle = dealTitle;
        this.disclaimer = disclaimer;
        this.dealInfo = dealInfo;
        this.expirationDate = expirationDate;
        this.originalPrice = originalPrice;
        this.dealPrice = dealPrice;
        this.dealSavings = dealSavings;
        this.dealDiscountPercent = dealDiscountPercent;
    }

    public double getDealDiscountPercent() {
        return dealDiscountPercent;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public double getDealSavings() {
        return dealSavings;
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
