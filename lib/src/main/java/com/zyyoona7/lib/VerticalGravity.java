package com.zyyoona7.lib;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zyyoona7 on 2017/8/3.
 */

@IntDef({
        VerticalGravity.CENTER,
        VerticalGravity.ABOVE,
        VerticalGravity.BELOW,
        VerticalGravity.ALIGN_TOP,
        VerticalGravity.ALIGN_BOTTOM,
})
@Retention(RetentionPolicy.SOURCE)
public @interface VerticalGravity {
    int CENTER = 0;
    int ABOVE = 1;
    int BELOW = 2;
    int ALIGN_TOP = 3;
    int ALIGN_BOTTOM = 4;
}
