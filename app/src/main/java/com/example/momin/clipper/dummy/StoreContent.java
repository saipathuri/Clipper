package com.example.momin.clipper.dummy;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.momin.clipper.ItemFragment;
import com.example.momin.clipper.MainActivity;
import com.example.momin.clipper.MyItemRecyclerViewAdapter;
import com.example.momin.clipper.R;
import com.example.momin.clipper.StoreLister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.momin.clipper.Store;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class StoreContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<StoreItem> ITEMS = new ArrayList<StoreItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, StoreItem> ITEM_MAP = new HashMap<String, StoreItem>();

    private static final int COUNT = 25;

//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    public static void createList(ArrayList<Store> stores){
        if(ITEMS.size() < 1 && stores.size() > 0) {
            for (int i = 1; i <= stores.size(); i++) {
                String title = stores.get(i - 1).getName();
                String distance = String.format("%.2f", stores.get(i - 1).getDistance());
                String subTitle = distance + " | # of Deals: " + stores.get(i - 1).getTotalDeals();
                StoreItem item = new StoreItem(distance + " mi.", title, subTitle, stores.get(i-1));
                addItem(item);
            }
        }
        else if(stores.size() == 0){
            addItem(new StoreItem("666", "No stores/coupons in your area", "", null));
        }
    }

    private static void addItem(StoreItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    static StoreItem createDummyItem(int position) {
        return new StoreItem(String.valueOf(position), "Item " + position, makeDetails(position), null);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class StoreItem {
        public final String id;
        public final String content;
        public final String details;
        public Store store;

        public StoreItem(String id, String content, String details, Store s) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.store = s;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
