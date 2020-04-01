package com.example.hub.Common;

public class Common {
    public static String API_Key="";
    public static String API_LINK="http://api.openweathermap.org/data/2.5/forecast";
    public static String apiRequest(String lat,String lng){
        StringBuilder sb=new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat,lng,API_Key))
    }

}
