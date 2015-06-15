package com.example.gmomedia.listviewsample;

import java.util.List;

/**
 * Created by usr0200475 on 15/04/17.
 */
public class Weather {

    List<Detail> list;

    public List<Detail> getList() {
        return list;
    }

    public void setList(List<Detail> list) {
        this.list = list;
    }

    public class Detail {
        Temp temp;
        String dt;

        List<WeatherDetail> weather;

        //温度

        public class Temp {
            String max;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        //日
        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
        }


        //  天気の詳細情報

        public class WeatherDetail {
            String icon;
        }

        public List<WeatherDetail> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherDetail> weather) {
            this.weather = weather;
        }


    }



}