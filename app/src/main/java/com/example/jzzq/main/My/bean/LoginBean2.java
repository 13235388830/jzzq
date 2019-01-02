package com.example.jzzq.main.My.bean;

import java.util.List;

/**
 * Created by admin on 2018/4/9.
 */

public class LoginBean2 {
    /**
     * code : 200
     * data : []
     * msg : 短信验证码发送成功
     */

    private String code;
    private String msg;
    private List<?> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }





   /* *//**
     * code : 200
     * data : {"url":"data:image/png;base64,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"}
     * msg : 登录成功!
     *//*

    private String code;
    private String data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }*/

}