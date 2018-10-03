
package com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpinnerModel {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("data")
    @Expose
    private List<SpinnerData> data = null;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    public List<SpinnerData> getData() {
        return data;
    }

    public void setData(List<SpinnerData> data) {
        this.data = data;
    }

}
