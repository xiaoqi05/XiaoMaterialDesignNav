package com.demo.materialdesign.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.demo.materialdesign.R;
import com.demo.materialdesign.activities.DetailActivity;
import com.demo.materialdesign.bean.FoodProBean;
import com.demo.materialdesign.customViews.InitView;
import com.demo.materialdesign.customViews.swipelistview.SwipeListView;
import com.demo.materialdesign.http.GetJsonResultNetWorkHelper;
import com.demo.materialdesign.http.NetworkHelper;
import com.demo.materialdesign.http.UIDataListener;
import com.demo.materialdesign.http.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstFragment extends Fragment implements UIDataListener<String>, SwipeRefreshLayout.OnRefreshListener,ViewPagerEx.OnPageChangeListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private SwipeListView mListView;
    private SwipeRefreshLayout swipeLayout;
    private QuickAdapter mAdapter;
    private int pageNumber = 0;
    private int pageSize = 10;
    private NetworkHelper<String> networkHelper;
    private ArrayList<FoodProBean> myFoodBeans;
    private boolean isRefresh = false;
    private int positon;
    private SliderLayout mDemoSlider;

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_list, container, false);
        myFoodBeans = new ArrayList<>();
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        InitView.instance().initSwipeRefreshLayout(swipeLayout);
        mAdapter = new QuickAdapter<FoodProBean>(getActivity(), R.layout.foot_list_item, myFoodBeans) {
            @Override
            protected void convert(BaseAdapterHelper helper, FoodProBean bean) {
                helper.setText(R.id.tv_food_date, "员工总数 ：" + bean.getEmployeesNumber());
                helper.setText(R.id.tv_food_address, "企业地址：" + bean.getProductionAddr());
                helper.setText(R.id.tv_food_person, "法人代表：" + bean.getLegalName());
                helper.setText(R.id.tv_food_phone, "联系电话：" + bean.getContactTel());
                helper.setText(R.id.tv_food_number, "获证产品：" + bean.getHzcpName());
                helper.setText(R.id.tv_food_name, "企业名称：" + bean.getQyName());
            }
        };
        // Set the adapter
        mListView = (SwipeListView) view.findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mListView.setOnBottomListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRefresh = true;
                loadData();

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int po = position % 10;
                positon = position;
                Bundle bundle = new Bundle();
                bundle.putSerializable("foodBean", myFoodBeans.get(po));
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        
        /*轮播界面*/
        View top_view =inflater.inflate(R.layout.top_view, null);
        top_view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mDemoSlider = (SliderLayout)top_view.findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("haha", "http://rootwork.co/wp-content/uploads/2013/07/Website-Android-Development-Banner.jpg");
        url_maps.put("hehe", "http://22iiaa2jpzc63c93rf3p9oti14j8.wpengine.netdna-cdn.com/wp-content/uploads/komoot-banner-640x290.png");
        url_maps.put("enen", "http://www.aromasoftech.com/images/ca-banner.jpg");
        url_maps.put("yiyi", "https://rezaaugustian.files.wordpress.com/2014/10/wpid-daftar-smartphone-yang-akan-mendapatkan-update-android-lollipop-banner.jpeg");
 
        
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.bigbang);
        file_maps.put("Big Bang Theory", R.drawable.bigbang);
        file_maps.put("House of Cards", R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                            // .image(file_maps.get(name))
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
                    //.setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mListView.addHeaderView(top_view);
        
        networkHelper = new GetJsonResultNetWorkHelper(getActivity());
        networkHelper.setUiDataListener(this);
        initData();
        return view;
    }

    private void loadData() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pageNumber", pageNumber + ""));
        params.add(new BasicNameValuePair("pageSize", pageSize + ""));
        networkHelper.sendGETRequest(Url.GET_FOOD_INFO, params);
        pageNumber++;

    }

    private void initData() {
        loadData();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onDataChanged(String data) {
        Log.i("data", data.toString());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert jsonObject != null;
        JSONObject json = jsonObject.optJSONObject("spscPo");
        JSONArray jsonArray = json.optJSONArray("rows");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<FoodProBean>>() {
        }.getType();
        ArrayList<FoodProBean> repast = gson.fromJson(jsonArray.toString(), listType);
        myFoodBeans =repast;
        if (isRefresh) {
            swipeLayout.setRefreshing(false);
            mListView.onBottomComplete();
        }
        mAdapter.addAll(repast);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorHappened(String errorCode, String errorMessage) {
        Log.i("data", errorMessage);
    }

    @Override
    public void onRefresh() {
        //延迟2秒再刷新
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isRefresh = true;
                loadData();
            }
        }, 2000);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
