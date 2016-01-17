package com.example.momin.clipper.dummy;

import com.example.momin.clipper.Coupon;
import com.example.momin.clipper.Store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Momin on 1/16/2016.
 */
public class CouponContent2 {
    public static final List<CouponItem2> ITEMS = new ArrayList<CouponItem2>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CouponItem2> ITEM_MAP = new HashMap<String, CouponItem2>();

    public static void createList(ArrayList<Store> stores)
    {
        ITEMS.clear();
        ITEM_MAP.clear();
        if(stores.size() > 0) {
            for (Store x : stores) {
                ArrayList<Coupon> coupons = x.getCoupons();
                 for (int i = 1; i < coupons.size(); i++) {
                     String title = x.getName() + ": " + coupons.get(i - 1).getDealTitle();
                     CouponItem2 item = new CouponItem2("" + coupons.get(i-1).getDealSavings(),
                             title, "", coupons.get(i - 1));
                     addItem(item);
                 }
            }
            Collections.sort(ITEMS);
            Collections.reverse(ITEMS);
        }
        else if(stores.size() == 0){
            addItem(new CouponItem2("666", "No stores/coupons in your area", "", null));
        }

    }

    private static void addItem(CouponItem2 item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static class CouponItem2 implements Comparable {
        public final String id;
        public final String content;
        public final String details;
        public Coupon coupon;

        public CouponItem2(String id, String content, String details, Coupon c) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.coupon = c;
        }

        public int compareTo(Object o){
            CouponItem2 c = (CouponItem2) o;
            return this.coupon.compareTo(c.coupon);
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
