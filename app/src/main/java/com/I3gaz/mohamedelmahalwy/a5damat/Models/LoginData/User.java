
package com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
