package com.gh.playground;

public class ValueItem {
    private String timestamp;
    private String feature;
    private int ean;
    private String data;

    public ValueItem(){
    }

    public ValueItem(String timestamp, String feature, int ean, String data){
        this.timestamp = timestamp;
        this.feature = feature;
        this.ean = ean;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getEan() {
        return ean;
    }

    public void setEan(int ean) {
        this.ean = ean;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("{timestamp:%s,feature:%s,ean:%s,data:%s}", timestamp, feature, ean, data);
    }
}