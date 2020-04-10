package com.suzhou.cabinet.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * @Author : SUZ
 * @Date : 2020/01/14 08:20
 * @Description : 定位工具
 */
public class BaiDuMapUtil {
    final static String AK = "wws9Qu73jw4QkOL6osEyIsA9Yob2yYgR";

    public static String getGeocoderLatitude(String address) {
        BufferedReader in = null;
        try {
            address = URLEncoder.encode(address, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak="
                    + AK + "&callback=showLocation");
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), StandardCharsets.UTF_8));
            String res;
            StringBuilder sb = new StringBuilder();
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            if (!StringUtils.isEmpty(str)) {
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    return "lng:" + lng + "lat:" + lat;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 输入经纬度返回地址 key lng(经度),lat(纬度)
     */
    public static String getPosition(String latitude, String longitude) throws MalformedURLException {
        BufferedReader in;
        URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + latitude + "," + longitude
                + "&output=json&pois=1&ak=" + AK);
        try {
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), StandardCharsets.UTF_8));
            String res;
            StringBuilder sb = new StringBuilder();
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            if (!StringUtils.isEmpty(str)) {
                int lngStart = str.indexOf("formatted_address\":\"");
                int lngEnd = str.indexOf("\",\"business");
                if (lngStart > 0 && lngEnd > 0) {
                    return str.substring(lngStart + 20, lngEnd);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     *判定点是否在区域内
     */
    public static boolean JudgeInOutFirst(double[] LngCollotions, double[] LatCollotions, double Lng, double Lat) {
        //点在最值区域外
        if (Lng < GetMinLng(LngCollotions) || Lng > GetMaxLng(LngCollotions) || Lat < GetMinLat(LatCollotions) || Lat > GetMaxLat(LatCollotions)) {
            return false;
        } else {
            int nvert = LngCollotions.length;//点数
            int i, j, k = 0;
            //该点位与n个点所成n-1条线的判断
            for (i = 0, j = nvert - 1; i < nvert; j = i++) {
                boolean LngTemp = (LngCollotions[i] == Lng);
                boolean latTemp = (LatCollotions[i] == Lat);
                if (LngTemp && latTemp) {
                    //点在点上
                    return true;
                } else {
                    boolean notSameSide = (LngCollotions[i] > Lng) != (LngCollotions[j] >= Lng);//区域点不在同一边,线段为前开后闭
                    double TempLat = (LatCollotions[j] - LatCollotions[i]) * (Lng - LngCollotions[i]) / (LngCollotions[j] - LngCollotions[i]) + LatCollotions[i];//可疑点与直线交点
                    boolean inRange = TempLat < Math.max(LatCollotions[j], LatCollotions[i]) && TempLat >= Math.min(LatCollotions[j], LatCollotions[i]);//区域内
                    if(notSameSide&&Lat==TempLat){
                        //点在边界线上
                        if(inRange){
                            return true;
                        }
                    }else if(notSameSide&&Lat!=TempLat){
                        //点不再边界线上
                        //交点在线段上
                        if (inRange) {
                            //统计单一方向,交点数
                            if (Lat < TempLat) {
                                k++;
                            }
                        }
                    }
                }
            }
            //改点位与始点与终点所在线的判断
            return k % 2 != 0;
        }
    }

    public static double[] String2Double(String[] List) {
        double[] GpsList = new double[List.length];
        for (int i = 0; i < List.length; i++) {
            GpsList[i] = Double.parseDouble(List[i]);
        }
        return GpsList;
    }

    private static double GetMaxLng(double[] LngCollotions) {
        double MaxLng = LngCollotions[0];
        for (double lngCollotion : LngCollotions) {
            if (MaxLng < lngCollotion) {
                MaxLng = lngCollotion;
            }
        }
        return MaxLng;
    }

    private static double GetMinLng(double[] LngCollotions) {
        double MinLng = LngCollotions[0];
        for (double lngCollotion : LngCollotions) {
            if (MinLng > lngCollotion) {
                MinLng = lngCollotion;
            }
        }
        return MinLng;
    }

    private static double GetMaxLat(double[] LatCollotions) {
        double MaxLat = LatCollotions[0];
        for (double latCollotion : LatCollotions) {
            if (MaxLat < latCollotion) {
                MaxLat = latCollotion;
            }
        }
        return MaxLat;
    }

    private static double GetMinLat(double[] LatCollotions) {
        double MinLat = LatCollotions[0];
        for (double latCollotion : LatCollotions) {
            if (MinLat > latCollotion) {
                MinLat = latCollotion;
            }
        }
        return MinLat;
    }

    /*
     * 轨迹纠偏
     * 最多不能超过2000个轨迹点，且轨迹里程不超过500公里（注：若轨迹里程超长，可能会出现响应时间过长或超时）。point_list格式为json，
     * 必须包含：latitude,longitude,coord_type_input,loc_time这4个字段，
     * 可选speed,direction,height,radius这4个字段，其他字段会被舍弃。
     * 取值规则：
     * latitude：纬度，支持小数点后6位
     * longitude：经度，支持小数点后6位
     * coord_type_input：轨迹点的坐标系，支持以下值：bd09ll（百度经纬度坐标）、gcj02（国测局加密坐标）、wgs84（GPS所采用的坐标系）
     * loc_time：轨迹点的定位时间，使用UNIX时间戳
     * speed：轨迹点的速度，单位：公里/小时
     * direction：轨迹点的方向，单位：范围为[0,359]，0度为正北方向，顺时针方向递增
     * height：轨迹点的高度，单位：米
     * radius：定位时返回的定位精度，单位：米
     */
    /*public static List<PointList> Trackrectify(List<PointList> pointLists) {
        List<PointList> rs = new LinkedList<>();
        for (int i = 0; i < pointLists.size(); i += 1901) {
            int end = i + 1900;
            if ((i + 1900) >= pointLists.size()) {
                end = pointLists.size() - 1;
            }
            List<PointList> lists = pointLists.subList(i, end);
            String JSONPointList = JSONObject.toJSONString(lists);
            JSONPointList = JSONPointList.replace("locTime", "loc_time");
            BufferedReader in;
            try {
                // 定义HttpClient
                HttpClient client = new DefaultHttpClient();
                // 实例化HTTP方法
                HttpPost request = new HttpPost();
                request.setURI(new URI("http://api.map.baidu.com/rectify/v1/track?"));
                //设置参数
                List<NameValuePair> nvps = new ArrayList<>();
                nvps.add(new BasicNameValuePair("ak", "5lsGmg3AXVCaLKG2S7Nl9v7xHfdwB62k"));
                nvps.add(new BasicNameValuePair("point_list", JSONPointList));

                request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
                HttpResponse response = client.execute(request);
                int code = response.getStatusLine().getStatusCode();
                if (code == 200) {  //请求成功
                    in = new BufferedReader(new InputStreamReader(response.getEntity()
                            .getContent(), StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    //String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    in.close();
                    List<PointList> parse = (List<PointList>) JSONObject.parse(sb.substring(sb.indexOf("\"points\":") + 9, sb.length() - 1));
                    rs.addAll(parse);
                } else {
                    return new ArrayList<>();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }*/

}
