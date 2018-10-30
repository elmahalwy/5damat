
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
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sender_image")
    @Expose
    private String sender_image;


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
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public String getSender_image() {
        return sender_image;
    }

    public void setSender_image(String sender_image) {
        this.sender_image = sender_image;
    }
}
