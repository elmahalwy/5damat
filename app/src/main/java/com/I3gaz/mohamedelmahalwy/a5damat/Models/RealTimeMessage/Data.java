
package com.I3gaz.mohamedelmahalwy.a5damat.Models.RealTimeMessage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("room_id")
    @Expose
    private int roomId;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
