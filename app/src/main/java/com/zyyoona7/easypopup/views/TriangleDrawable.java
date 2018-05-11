package com.zyyoona7.easypopup.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jiang on 2017/5/19.
 */

public class TriangleDrawable extends Drawable {
    public static final int TOP = 12;
    public static final int BOTTOM = 13;
    public static final int LEFT = 14;
    public static final int RIGHT = 15;

    private int bgColor = Color.WHITE;
    @ARROWDIRECTION
    private int arrowDirection;

    public TriangleDrawable(@ARROWDIRECTION int arrowDirection, int bgColor) {
        this.arrowDirection = arrowDirection;
        this.bgColor = bgColor;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(bgColor);
        paint.setStyle(Paint.Style.FILL);
        Path path = createPath();
        canvas.drawPath(path, paint);
    }

    private Path createPath() {
        Rect bound = getBounds();
        Path path = new Path();
        if (arrowDirection == TOP) {
            path.moveTo(bound.right / 2, 0);
            path.lineTo(0, bound.bottom);
            path.lineTo(bound.right, bound.bottom);
            path.close();
        } else if (arrowDirection == BOTTOM) {
            path.moveTo(bound.right / 2, bound.bottom);
            path.lineTo(0, 0);
            path.lineTo(bound.right, 0);
            path.close();

        } else if (arrowDirection == LEFT) {
            path.moveTo(0, bound.bottom / 2);
            path.lineTo(bound.right, 0);
            path.lineTo(bound.right, bound.bottom);
            path.close();
        } else {
            path.moveTo(bound.right, bound.bottom / 2);
            path.lineTo(0, 0);
            path.lineTo(0, bound.bottom);
            path.close();
        }
        return path;

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @IntDef({TOP, BOTTOM, LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ARROWDIRECTION {
    }
}