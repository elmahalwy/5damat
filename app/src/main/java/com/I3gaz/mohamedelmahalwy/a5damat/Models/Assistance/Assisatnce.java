
package com.I3gaz.mohamedelmahalwy.a5damat.Models.Assistance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assisatnce {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("data")
    @Expose
    private String data;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
