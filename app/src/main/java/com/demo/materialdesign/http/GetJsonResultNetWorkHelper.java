package com.demo.materialdesign.http;

import android.content.Context;

import com.android.volley.VolleyError;

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
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2015/6/4 10:34.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public class GetJsonResultNetWorkHelper extends NetworkHelper<String> {
    public GetJsonResultNetWorkHelper(Context context) {
        super(context);
    }

    @Override
    protected void disposeVolleyError(VolleyError error) {
        notifyErrorHappened("", error == null ? "Null" : error.getMessage());
    }

    @Override
    protected void disposeResponse(String response) {
        notifyDataChanged(response);
    }
}