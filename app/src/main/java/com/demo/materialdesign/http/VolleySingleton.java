package com.demo.materialdesign.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

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
 * Created by 2015/6/1 11:23.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mContext;
    public VolleySingleton(Context context) {
        this.mContext = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache(){
                    private final LruCache<String,Bitmap> cache = new LruCache<String ,Bitmap>(20);
                    @Override
                    public Bitmap getBitmap(String url){
                        return cache.get(url);
                    }
                    @Override
                    public void putBitmap(String url,Bitmap bitmap){
                        cache.put(url,bitmap);
                    }
                });
    }
    public static synchronized VolleySingleton getVolleySingleton(Context context){
        if(volleySingleton == null){
            volleySingleton = new VolleySingleton(context);
        }
        return volleySingleton;
    }
    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}