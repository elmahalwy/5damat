
package com.I3gaz.mohamedelmahalwy.a5damat.Models.RealTimeMessage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("reciever")
    @Expose
    private String reciever;
    @SerializedName("msg")
    @Expose
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
