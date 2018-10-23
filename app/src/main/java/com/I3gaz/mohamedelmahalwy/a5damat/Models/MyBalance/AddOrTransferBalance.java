
package com.I3gaz.mohamedelmahalwy.a5damat.Models.MyBalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddOrTransferBalance {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("msg")
    @Expose
    private String msg;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
