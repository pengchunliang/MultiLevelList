package com.pcl.myapplication.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by pcl on 2022/5/30
 */
public class DpUtils {

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
