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
 * TODO  描述文件做什么的
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2015/6/1 11:29.
 * <p/>
 * <p/>
 * ********************************************************************************
 */
public class WeatherinfoEntity implements Serializable{

   
        /**
         * isRadar : 1
         * SD : 22%
         * temp : 9
         * city : 北京
         * Radar : JC_RADAR_AZ9010_JB
         * WSE : 2
         * qy : 1014
         * njd : 暂无实况
         * cityid : 101010100
         * time : 10:45
         * WS : 2级
         * WD : 西南风
         */
        private String isRadar;
        private String SD;
        private String temp;
        private String city;
        private String Radar;
        private String WSE;
        private String qy;
        private String njd;
        private String cityid;
        private String time;
        private String WS;
        private String WD;

        /**
         * weatherinfo : {"isRadar":"1","SD":"22%","temp":"9","city":"北京","Radar":"JC_RADAR_AZ9010_JB","WSE":"2","qy":"1014","njd":"暂无实况","cityid":"101010100","time":"10:45","WS":"2级","WD":"西南风"}
         */
        private WeatherinfoEntity weatherinfo;

        public void setWeatherinfo(WeatherinfoEntity weatherinfo) {
            this.weatherinfo = weatherinfo;
        }

        public WeatherinfoEntity getWeatherinfo() {
            return weatherinfo;
        }

        public void setIsRadar(String isRadar) {
            this.isRadar = isRadar;
        }

        public void setSD(String SD) {
            this.SD = SD;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setRadar(String Radar) {
            this.Radar = Radar;
        }

        public void setWSE(String WSE) {
            this.WSE = WSE;
        }

        public void setQy(String qy) {
            this.qy = qy;
        }

        public void setNjd(String njd) {
            this.njd = njd;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getIsRadar() {
            return isRadar;
        }

        public String getSD() {
            return SD;
        }

        public String getTemp() {
            return temp;
        }

        public String getCity() {
            return city;
        }

        public String getRadar() {
            return Radar;
        }

        public String getWSE() {
            return WSE;
        }

        public String getQy() {
            return qy;
        }

        public String getNjd() {
            return njd;
        }

        public String getCityid() {
            return cityid;
        }

        public String getTime() {
            return time;
        }

        public String getWS() {
            return WS;
        }

        public String getWD() {
            return WD;
        }
}