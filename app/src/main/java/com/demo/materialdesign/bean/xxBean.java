package com.demo.materialdesign.bean;

import java.io.Serializable;

/**
 * ******************************************************************************
 * <p/>
 * Project Name  : MaterialDesignNavDrawer
 * Package       : com.demo.materialdesign.bean
 * <p/>
 * <p/>
 * Copyright  绿康源电子商务部  Corporation 2015 All Rights Reserved
 * <p/>
 * <p/>
 * <Pre>
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2015/6/2 9:45.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public class xxBean implements Serializable{

    /**
     * QYTOTAL : 9
     * COVERAGE : 11%
     * JCQYTYPENAME : 食品生产企业
     * XCNUM : 1
     */
    private int QYTOTAL;
    private String COVERAGE;
    private String JCQYTYPENAME;
    private int XCNUM;

    public xxBean(int QYTOTAL, int XCNUM, String JCQYTYPENAME, String COVERAGE) {
        this.QYTOTAL = QYTOTAL;
        this.XCNUM = XCNUM;
        this.JCQYTYPENAME = JCQYTYPENAME;
        this.COVERAGE = COVERAGE;
    }
    public xxBean() {
       
    }

    public void setQYTOTAL(int QYTOTAL) {
        this.QYTOTAL = QYTOTAL;
    }

    public void setCOVERAGE(String COVERAGE) {
        this.COVERAGE = COVERAGE;
    }

    public void setJCQYTYPENAME(String JCQYTYPENAME) {
        this.JCQYTYPENAME = JCQYTYPENAME;
    }

    public void setXCNUM(int XCNUM) {
        this.XCNUM = XCNUM;
    }

    public int getQYTOTAL() {
        return QYTOTAL;
    }

    public String getCOVERAGE() {
        return COVERAGE;
    }

    public String getJCQYTYPENAME() {
        return JCQYTYPENAME;
    }

    public int getXCNUM() {
        return XCNUM;
    }
}