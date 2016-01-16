package com.example.momin.clipper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Momin on 1/16/2016.
 */
public class CouponView extends View {
    String title;
    public CouponView(Context context, String t) {
        super(context);
        title = t;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(getWidth() / 2);
        canvas.drawText(title, getWidth() / 2 - (getWidth() / 8), getHeight() / 2 + (getWidth() / 8), p);
    }
}
