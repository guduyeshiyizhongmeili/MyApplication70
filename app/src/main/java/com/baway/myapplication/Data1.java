package com.baway.myapplication;

/**
 * 刘海峰.8:25
 */

public class Data1 {
    private boolean ischecked;
    private String image_url;
    private float price;

    public Data1(boolean ischecked, String image_url, float price) {
        this.ischecked = ischecked;
        this.image_url = image_url;
        this.price = price;
    }

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
