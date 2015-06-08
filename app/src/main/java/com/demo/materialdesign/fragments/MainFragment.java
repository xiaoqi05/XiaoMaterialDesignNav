package com.demo.materialdesign.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.materialdesign.R;
import com.demo.materialdesign.customViews.MyViewPager;
import com.demo.materialdesign.customViews.PagerSlidingTabStrip;

public class MainFragment extends Fragment {
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private MyViewPager mViewPager;
    public static final String sARGUMENT_COLOR = "backgroundColor";


    public static MainFragment newInstance(Bundle bundle) {
        MainFragment colorFragment = new MainFragment();

        if (bundle != null) {
            colorFragment.setArguments(bundle);
        }
        return colorFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mian_first, container, false);
        initialise(view);
        mViewPager = (MyViewPager) view.findViewById(R.id.pager);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        initTabsValue();
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        return view;
    }

    private void initialise(View view) {
        view.getRootView().setBackgroundColor(getResources().getColor(getArguments().getInt(sARGUMENT_COLOR)));
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.primary));
        // tab的分割线颜色
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景 
        mPagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.background_material_light));
        // tab底线高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics()));
        // 游标高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        mPagerSlidingTabStrip.setSelectedTextColor(getResources().getColor(R.color.primary));
        // 正常文字颜色
        mPagerSlidingTabStrip.setTextColor(getResources().getColor(R.color.gray_de));
        //设置文字大小
        mPagerSlidingTabStrip.setTextSize(20);
        mPagerSlidingTabStrip.setDividerPadding(5);
    }


    /* ***************FragmentPagerAdapter***************** */
    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.type_item)[position];
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Fragment getItem(int position) {
            // Create the first fragment to be shown
            Bundle bundle = new Bundle();
            bundle.putInt(ColorFragment.sARGUMENT_COLOR, R.color.background_material_light);
            if (position == 0) {
                return FirstFragment.newInstance("","");
            } else if (position == 1) {
                return FirstFragment.newInstance("","");
            } else if (position == 2) {
                return FirstFragment.newInstance("","");
            } else if (position == 3) {
                return FirstFragment.newInstance("","");
            } else if (position == 4) {
                return FirstFragment.newInstance("","");
            } else {
                return FirstFragment.newInstance("","");
            }
        }
    }


}
