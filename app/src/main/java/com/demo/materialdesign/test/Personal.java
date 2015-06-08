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
 * Created by 2015/5/26 15:08.
 * <p>
 * <p>
 * ********************************************************************************
 */
public class Personal {
    public Personal(){
    }

    public void doSomething(CallBack callBack,String task){
        Log.i("Personal","经理交代的任务"+task);
        callBack.backResult("代码完成了");
    }


}