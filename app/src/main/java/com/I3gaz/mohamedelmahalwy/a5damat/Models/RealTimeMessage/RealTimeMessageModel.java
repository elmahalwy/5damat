
package com.I3gaz.mohamedelmahalwy.a5damat.Models.RealTimeMessage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealTimeMessageModel {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("data")
    @Expose
    private Data data;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
