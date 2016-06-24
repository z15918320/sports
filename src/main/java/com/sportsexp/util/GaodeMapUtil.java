package com.sportsexp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 高德地图辅助类
 * add by zhijiang.zhang 
 * 2016-05-17 17:10:45
 */
public class GaodeMapUtil {
    private static String API = "http://restapi.amap.com/v3/geocode/geo?key=<key>&s=rsv3&address=<address>";

    private static String KEY = "aa4a48297242d22d2b3fd6eddfe62217";

    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");

    static {
        init();
    }

    private static void init() {
        API = API.replaceAll("<key>", KEY);
    }

    public static double[] addressToGPS(String address) throws IOException {
        try {
            String requestUrl = API.replaceAll("<address>", URLEncoder.encode(address, "UTF-8"));
            requestUrl = HttpClientHelper.INSTANCE.get(requestUrl);
            if (requestUrl != null ) {
                Matcher matcher = pattern.matcher(requestUrl);
                if (matcher.find() && matcher.groupCount() == 2) {
                    double[] gps = new double[2];
                    gps[0] = Double.valueOf(matcher.group(1));
                    gps[1] = Double.valueOf(matcher.group(2));
                    return gps;
                }
            }
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    public static void main(String[] args) {
        try {
        	double[] dou = GaodeMapUtil.addressToGPS("长沙人民路2号");
            System.out.println(dou.length+":"+dou[1]+","+dou[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
