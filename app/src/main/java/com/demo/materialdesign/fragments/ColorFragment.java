package com.demo.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.materialdesign.R;
import com.demo.materialdesign.bean.xxBean;
import com.demo.materialdesign.http.GetListResultNetWorkHelper;
import com.demo.materialdesign.http.NetworkHelper;
import com.demo.materialdesign.http.UIDataListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment showing a solid background color
 *
 * @author Sotti https://plus.google.com/+PabloCostaTirado/about
 */
public class ColorFragment extends Fragment implements UIDataListener<ArrayList<xxBean>> {
    public static final String sARGUMENT_COLOR = "backgroundColor";
    private Button btnPick;
    private NetworkHelper<ArrayList<xxBean>> networkHelper;


    public static ColorFragment newInstance(Bundle bundle) {
        ColorFragment colorFragment = new ColorFragment();

        if (bundle != null) {
            colorFragment.setArguments(bundle);
        }
        return colorFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.colored_fragment, container, false);
        initialise(view);

        view.findViewById(R.id.bt_callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Personal personal = new Personal();
                // new Manager(personal);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("city", "成都市"));
                params.add(new BasicNameValuePair("county", "锦江区"));
                networkHelper.sendGETRequest("http://218.89.222.118:8085/sg_rcjg/mobile/getXcfgl.do", params);
              /*  String url ="http://211.149.197.241:8080/whereru/news/app/news/show_all.do";
                Map<String, String> params = new HashMap<String, String>();
                params.put("index",5+"");
                params.put("flag",""+0);
                params.put("type",1+"");
                networkHelper.sendPostRequest(url,params);*/
            }
        });
        networkHelper = new GetListResultNetWorkHelper(getActivity());
        networkHelper.setUiDataListener(this);

        return view;
    }

    /**
     * Bind the resources and set up them
     *
     * @param view is the view to get the bindings, context...
     */
    private void initialise(View view) {
        // Set the background color
        view.getRootView().setBackgroundColor(getResources().getColor(getArguments().getInt(sARGUMENT_COLOR)));
    }


    @Override
    public void onDataChanged(ArrayList<xxBean> data) {
        String name = data.get(0).getJCQYTYPENAME();
        Log.i("JSONObject", name);
    }

    @Override
    public void onErrorHappened(String errorCode, String errorMessage) {

    }
}
