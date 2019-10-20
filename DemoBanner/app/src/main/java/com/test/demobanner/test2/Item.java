package com.test.demobanner.test2;

public class Item {

    private int imageId;
    private String extra;

    public Item(int imageId, String extra) {
        this.imageId = imageId;
        this.extra = extra;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
