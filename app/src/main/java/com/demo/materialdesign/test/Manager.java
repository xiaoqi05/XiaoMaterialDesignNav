package com.demo.materialdesign.test;

import android.util.Log;

/**
 * ******************************************************************************
 * <p>
 * Project Name  : MaterialDesignNavDrawer
 * Package       : com.demo.materialdesign.test
 * <p>
 * <p>
 * Copyright  绿康源电子商务部  Corporation 2015 All Rights Reserved
 * <p>
 * <p>
 * <Pre>
 * </Pre>
 *
 * @AUTHOR by xiaoqi
 * Created by 2015/5/26 15:06.
 * <p>
 * <p>
 * ********************************************************************************
 */
public class Manager implements CallBack {
    //构造函数
    public Manager(Personal personal){
        String task ="写代码";
        personal.doSomething(this, task);
    }
  
    @Override
    public void backResult(String result) {
        Log.i("Manager得到了结果",result);
    }
}