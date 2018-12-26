package com.ami.makemoneyby.bean;

public class FragmentBean {

    private int img;
    private int title;
    private Class Fragment;

    public FragmentBean(int img, int title, Class fragment) {
        this.img = img;
        this.title = title;
        Fragment = fragment;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class getFragment() {
        return Fragment;
    }

    public void setFragment(Class fragment) {
        Fragment = fragment;
    }
}
