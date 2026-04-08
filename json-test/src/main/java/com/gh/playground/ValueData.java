package com.gh.playground;

import java.util.ArrayList;
import java.util.List;

public class ValueData {

    private List<ValueItem> information;

    public ValueData(){
    }

    public List<ValueItem> getInformation() {
        List<ValueItem> copy = new ArrayList<>();
        copy.addAll(information);
        return copy;
    }

    public void setInformation(List<ValueItem> information) {
        List<ValueItem> copy = new ArrayList<>();
        copy.addAll(information);
        this.information = copy;
    }

    @Override
    public String toString() {
        return String.format("{information:%s}", information);
    }

}
