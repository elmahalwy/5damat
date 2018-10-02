
package com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCategories {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
