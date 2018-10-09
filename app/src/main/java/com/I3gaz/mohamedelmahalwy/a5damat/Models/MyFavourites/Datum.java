
package com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("service_id")
    @Expose
    private int serviceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private String category;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
