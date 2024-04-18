package com.gh.playground;

import java.util.List;

public class ValueData {

    private List<ValueItem> information;

    public ValueData(){

    }

    public List<ValueItem> getInformation() {
        return information;
    }

    public void setInformation(List<ValueItem> information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return String.format("{information:%s}", information);
    }

}
