
package com.i3gaz.mohamedelmahalwy.a5damat.Models.ResetPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassword {

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
