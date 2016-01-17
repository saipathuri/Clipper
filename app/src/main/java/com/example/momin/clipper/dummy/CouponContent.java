package com.example.momin.clipper.dummy;

import com.example.momin.clipper.Coupon;
import com.example.momin.clipper.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Momin on 1/16/2016.
 */
public class CouponContent{
    public static final List<CouponItem> ITEMS = new ArrayList<CouponItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CouponItem> ITEM_MAP = new HashMap<String, CouponItem>();

    public static void createList(ArrayList<Coupon> coupons)
    {
        ITEMS.clear();
        ITEM_MAP.clear();
        if(coupons.size() > 0) {
            for (int i = 1; i <= coupons.size(); i++) {
                String title = coupons.get(i - 1).getDealTitle();
                CouponItem item = new CouponItem(String.valueOf(i), title, "", coupons.get(i-1));
                addItem(item);
            }
        }
        else if(coupons.size() == 0){
            addItem(new CouponItem("666", "No stores/coupons in your area", "", null));
        }
    }

    private static void addItem(CouponItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static class CouponItem{
        public final String id;
        public final String content;
        public final String details;
        public Coupon coupon;

        public CouponItem(String id, String content, String details, Coupon c) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.coupon = c;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
