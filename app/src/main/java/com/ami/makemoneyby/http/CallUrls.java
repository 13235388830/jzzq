package com.ami.makemoneyby.http;

/**
 * Created by admin on 2018/4/9.
 */

public enum CallUrls {

    ;


    //public static String PFURL = "http://api.ruyijinkong.com/";
    public static String PFURL0 = "https://appid-apkk.xx-app.com/frontApi/getAboutUs?";

    public static String PFURL = "http://android_api.ruyijinkong.com/";

    public static String PFURL1= "http://app.lhq8.com/jianzhi/list?";

    public static String PFURL2= "http://app.lhq8.com/jianzhi/detail?";

    public static String PFURL3= "http://www.meikejob.com/campusLife/list?";

    public static String PFURL4= "http://admin.hudatech.cn/Api/Product/ProductList?ClassId=0&pagesize=10&Sign=0C1E94FD442525B54FEC126E9D482BAA&page=1";
    public static String PFURL5= "http://admin.hudatech.cn/Api/Product/ProductList?ClassId=7&pagesize=10&Sign=4461CB89D278655E292E2ED506E6D63C&page=1";
    public static String PFURL6= "http://admin.hudatech.cn/Api/Product/ProductList?ClassId=19&pagesize=10&Sign=43C2F6E77B8543D9C2F4A76451198A52&page=1";
    public static String PFURL7= "http://admin.hudatech.cn/Api/Product/ProductList?ClassId=8&pagesize=10&Sign=2D6A2D18530E6336C6C845903774C33D&page=1";
    public static String PFURL8= "http://admin.hudatech.cn/Api/Product/ProductList?ClassId=15&pagesize=10&Sign=E7F3DDE5697E29F23704BED877ED46AC&page=1";

    public static String PFURL9= "http://www.meikejob.com/campusLife/detail?";



    private String url;

    CallUrls(String s) {
        this.url = s;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return new StringBuffer(PFURL).append(url).toString().trim();
    }
}
