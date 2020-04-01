package com.example.hub.Common;

public class Common {
    public static String API_Key="";
    public static String API_LINK="api.openweathermap.org/data/2.5/weather";
    public static String apiRequest(String lat,String lng){
        StringBuilder sb=new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat,lng,API_Key));
        return sb.toString();

    }

}
