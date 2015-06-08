package com.demo.materialdesign.http;

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
 * Created by 2015/6/1 11:06.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public interface UIDataListener<T> {
    
    public void onDataChanged(T data);

    public void onErrorHappened(String errorCode, String errorMessage);

}