package com.example.jzzq.main.My.bean;

/**
 * Created by admin on 2018/4/14.
 */

public class LoginBeans {


    /**
     * code : 200
     * data : {"is_first":"N","user_id":"13","firm_id":"1","channel_id":"1","nick_name":"李强飞","loan_money":"100","qq":"1119253673","wechat":"","card_id":"13042319901027335x"}
     * msg : 登录成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * is_first : N
         * user_id : 13
         * firm_id : 1
         * channel_id : 1
         * nick_name : 李强飞
         * loan_money : 100
         * qq : 1119253673
         * wechat :
         * card_id : 13042319901027335x
         */

        private String is_first;
        private String user_id;
        private String firm_id;
        private String channel_id;
        private String nick_name;
        private String loan_money;
        private String qq;
        private String wechat;
        private String card_id;

        public String getIs_first() {
            return is_first;
        }

        public void setIs_first(String is_first) {
            this.is_first = is_first;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFirm_id() {
            return firm_id;
        }

        public void setFirm_id(String firm_id) {
            this.firm_id = firm_id;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getLoan_money() {
            return loan_money;
        }

        public void setLoan_money(String loan_money) {
            this.loan_money = loan_money;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }
    }


    /**
     * code : 200
     * data : {"id":10,"firm_id":1,"user_id":5159,"nick_name":"","phone":"13524570134","card_id":"","qq":"","wechat":null,"loan_money":"0.00","status":0,"day":null,"loan_time":null,"card_img_z":"","card_img_b":"","card_img_s":"","zhima_id":"","zhima":0,"zhima_create_time":0,"authe":"","firm_text":null,"dsid":"","dsid_create_time":0,"address_list":null}
     * msg : 登录成功!
     *//*

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        *//**
     * id : 10
     * firm_id : 1
     * user_id : 5159
     * nick_name :
     * phone : 13524570134
     * card_id :
     * qq :
     * wechat : null
     * loan_money : 0.00
     * status : 0
     * day : null
     * loan_time : null
     * card_img_z :
     * card_img_b :
     * card_img_s :
     * zhima_id :
     * zhima : 0
     * zhima_create_time : 0
     * authe :
     * firm_text : null
     * dsid :
     * dsid_create_time : 0
     * address_list : null
     *//*
        private String is_first;
        private int id;
        private int firm_id;
        private int user_id;
        private String nick_name;
        private String phone;
        private String card_id;
        private String qq;
        private Object wechat;
        private String loan_money;
        private int status;
        private Object day;
        private Object loan_time;
        private String card_img_z;
        private String card_img_b;
        private String card_img_s;
        private String zhima_id;
        private int zhima;
        private int zhima_create_time;
        private String authe;
        private Object firm_text;
        private String dsid;
        private int dsid_create_time;
        private Object address_list;

        public String getIs_first() {
            return is_first;
        }

        public void setIs_first(String is_first) {
            this.is_first = is_first;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFirm_id() {
            return firm_id;
        }

        public void setFirm_id(int firm_id) {
            this.firm_id = firm_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getWechat() {
            return wechat;
        }

        public void setWechat(Object wechat) {
            this.wechat = wechat;
        }

        public String getLoan_money() {
            return loan_money;
        }

        public void setLoan_money(String loan_money) {
            this.loan_money = loan_money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getDay() {
            return day;
        }

        public void setDay(Object day) {
            this.day = day;
        }

        public Object getLoan_time() {
            return loan_time;
        }

        public void setLoan_time(Object loan_time) {
            this.loan_time = loan_time;
        }

        public String getCard_img_z() {
            return card_img_z;
        }

        public void setCard_img_z(String card_img_z) {
            this.card_img_z = card_img_z;
        }

        public String getCard_img_b() {
            return card_img_b;
        }

        public void setCard_img_b(String card_img_b) {
            this.card_img_b = card_img_b;
        }

        public String getCard_img_s() {
            return card_img_s;
        }

        public void setCard_img_s(String card_img_s) {
            this.card_img_s = card_img_s;
        }

        public String getZhima_id() {
            return zhima_id;
        }

        public void setZhima_id(String zhima_id) {
            this.zhima_id = zhima_id;
        }

        public int getZhima() {
            return zhima;
        }

        public void setZhima(int zhima) {
            this.zhima = zhima;
        }

        public int getZhima_create_time() {
            return zhima_create_time;
        }

        public void setZhima_create_time(int zhima_create_time) {
            this.zhima_create_time = zhima_create_time;
        }

        public String getAuthe() {
            return authe;
        }

        public void setAuthe(String authe) {
            this.authe = authe;
        }

        public Object getFirm_text() {
            return firm_text;
        }

        public void setFirm_text(Object firm_text) {
            this.firm_text = firm_text;
        }

        public String getDsid() {
            return dsid;
        }

        public void setDsid(String dsid) {
            this.dsid = dsid;
        }

        public int getDsid_create_time() {
            return dsid_create_time;
        }

        public void setDsid_create_time(int dsid_create_time) {
            this.dsid_create_time = dsid_create_time;
        }

        public Object getAddress_list() {
            return address_list;
        }

        public void setAddress_list(Object address_list) {
            this.address_list = address_list;
        }
    }*/
}
