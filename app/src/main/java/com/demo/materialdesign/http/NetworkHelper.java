package com.demo.materialdesign.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

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
 * TODO  描述文件做什么的
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2015/6/1 11:17.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public abstract class NetworkHelper<T> implements Response.Listener<String>, Response.ErrorListener {
    private Context context;

    public NetworkHelper(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    protected NetworkRequest getRequestForGet(String url, List<NameValuePair> params) {
        if (params == null) {
            return new NetworkRequest(url, this, this);
        } else {
            return new NetworkRequest(url, params, this, this);
        }

    }

    protected NetworkRequest getRequestForPost(String url, Map<String, String> params) {
        return new NetworkRequest(Request.Method.POST, url, params, this, this);
    }

    public void sendGETRequest(String url, List<NameValuePair> params) {
        VolleySingleton.getVolleySingleton(context).
                getRequestQueue().add(getRequestForGet(url, params));
    }

    public void sendPostRequest(String url, Map<String, String> params) {
        VolleySingleton.getVolleySingleton(context).
                getRequestQueue().add(getRequestForPost(url, params));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        disposeVolleyError(error);
    }

    protected abstract void disposeVolleyError(VolleyError error);

    @Override
    public void onResponse(String response) {
        disposeResponse(response);
    }

    protected abstract void disposeResponse(String response);

    private UIDataListener<T> uiDataListener;

    public void setUiDataListener(UIDataListener<T> uiDataListener) {
        this.uiDataListener = uiDataListener;
    }

    protected void notifyDataChanged(T data) {
        if (uiDataListener != null) {
            uiDataListener.onDataChanged(data);
        }
    }

    protected void notifyErrorHappened(String errorCode, String errorMessage) {
        if (uiDataListener != null) {
            uiDataListener.onErrorHappened(errorCode, errorMessage);
        }
    }

}