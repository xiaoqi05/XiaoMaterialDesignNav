package com.demo.materialdesign.customViews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiao on 2015/1/16.
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context,AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        //这里可以处理滑动冲突问题
        if(v.getClass().getName().equals("com.baidu.mapapi.map.MapView")){
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}
