
package com.demo.materialdesign.customViews;

import android.support.v4.widget.SwipeRefreshLayout;


public class InitView {
    private static InitView initView;


    public static InitView instance() {
        if (initView == null) {
            initView = new InitView();
        }
        return initView;
    }

    /**
     * 设置下拉刷新控件颜色
     * 
     * @param swipeLayout
     */
    public void initSwipeRefreshLayout(SwipeRefreshLayout swipeLayout) {
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


}
