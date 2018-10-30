
package com.I3gaz.mohamedelmahalwy.a5damat.Models.ChatUsersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("message_date")
    @Expose
    private String messageDate;
    @SerializedName("room_id")
    @Expose
    private int roomId;
    @SerializedName("service_id")
    @Expose
    private int service_id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
}
