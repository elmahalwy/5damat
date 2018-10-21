
package com.I3gaz.mohamedelmahalwy.a5damat.Models.EditProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfile {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.Data data;

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

    public com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.Data getData() {
        return data;
    }

    public void setData(com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.Data data) {
        this.data = data;
    }

}
