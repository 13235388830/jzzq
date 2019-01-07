package com.example.jzzq.bean;

public class HotCityBean {
    private  String name;
    private String py;
    public HotCityBean(String name,String py){
        this.name=name;
        this.py=py;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }


}
