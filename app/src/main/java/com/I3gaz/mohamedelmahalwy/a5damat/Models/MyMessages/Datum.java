
package com.I3gaz.mohamedelmahalwy.a5damat.Models.MyMessages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("service_title")
    @Expose
    private String serviceTitle;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("service_id")
    @Expose
    private int service_id;
    @SerializedName("sender_id")
    @Expose
    private int sender_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }
}
