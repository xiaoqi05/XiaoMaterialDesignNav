package com.demo.materialdesign.http;

import android.content.Context;

import com.android.volley.VolleyError;
import com.demo.materialdesign.bean.xxBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * ******************************************************************************
 * <p/>
 * Project Name  : MaterialDesignNavDrawer
 * Package       : com.demo.materialdesign.http
 * <p/>
 * <p/>
 * Copyright  绿康源电子商务部  Corporation 2015 All Rights Reserved
 * <p/>
 * <p/>
 * <Pre>
 * 将从接口获取到的json数据，转换成list实体类数据
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2015/6/1 11:32.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public class GetListResultNetWorkHelper extends NetworkHelper<ArrayList<xxBean>> {
    public GetListResultNetWorkHelper(Context context) {
        super(context);
    }

    @Override
    protected void disposeVolleyError(VolleyError error) {
        notifyErrorHappened("", error == null ? "Null" : error.getMessage());

    }

    @Override
    protected void disposeResponse(String response) {
        if (response != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<xxBean>>() {
            }.getType();
            ArrayList<xxBean> repast = gson.fromJson(response, listType);
            notifyDataChanged(repast);
        }
    }
}