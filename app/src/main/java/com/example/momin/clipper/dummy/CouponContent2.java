package com.example.momin.clipper.dummy;

import android.support.v7.widget.RecyclerView;

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

    public static RecyclerView r = null;

    public static void createList(ArrayList<Coupon> allCoupons)
    {
        ITEMS.clear();
        ITEM_MAP.clear();
        if(allCoupons.size() > 0) {
            Collections.sort(allCoupons);
            Collections.reverse(allCoupons);
            for (int i =0; i<allCoupons.size();i++) {
                addItem(new CouponItem2("$"+String.format("%.2f",allCoupons.get(i).getDealSavings()),
                        allCoupons.get(i).getName() + ": " + allCoupons.get(i).getDealTitle(),"",
                        allCoupons.get(i)));
            }
            r.getAdapter().notifyDataSetChanged();
        }
        else if(allCoupons.size() == 0){
            addItem(new CouponItem2("404", "No stores/coupons in your area", "", null));
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
