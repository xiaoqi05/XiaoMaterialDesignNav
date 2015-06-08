package com.demo.materialdesign.http;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2015/6/1 11:11.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public class NetworkRequest extends JsonRequest<String>{
    private Priority mPriority = Priority.HIGH;

    public NetworkRequest(int method, String url,
                          Map<String, String> postParams, Response.Listener<String> listener,
                          Response.ErrorListener errorListener)
    {
        super(method, url, paramstoString(postParams), listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public NetworkRequest(String url, List<NameValuePair> params,
                          Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        this(Method.GET, urlBuilder(url, params), null, listener, errorListener);
    }

    public NetworkRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        this(Method.GET, url, null, listener, errorListener);
    }

    private static String paramstoString(Map<String, String> params)
    {
        if (params != null && params.size() > 0)
        {
            String paramsEncoding = "UTF-8";
            StringBuilder encodedParams = new StringBuilder();
            try
            {
                for (Map.Entry<String, String> entry : params.entrySet())
                {
                    encodedParams.append(URLEncoder.encode(entry.getKey(),
                            paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue(),
                            paramsEncoding));
                    encodedParams.append('&');

                }
                return encodedParams.toString();
            }
            catch (UnsupportedEncodingException uee)
            {
                throw new RuntimeException("Encoding not supported: "
                        + paramsEncoding, uee);
            }
        }
        return null;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {

        try
        {

            String String = new String(new String(response.data, "UTF-8"));

            return Response.success(String,
                    HttpHeaderParser.parseCacheHeaders(response));

        }
        catch (Exception e)
        {

            return Response.error(new ParseError(e));

        }
    }

    @Override
    public Priority getPriority()
    {
        return mPriority;
    }

    public void setPriority(Priority priority)
    {
        mPriority = priority;
    }

    private static String urlBuilder(String url, List<NameValuePair> params)
    {
        return url + "?" + URLEncodedUtils.format(params, "UTF-8");
    }
}