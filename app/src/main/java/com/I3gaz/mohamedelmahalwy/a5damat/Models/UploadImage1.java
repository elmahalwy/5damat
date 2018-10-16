
package com.I3gaz.mohamedelmahalwy.a5damat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImage1 {

    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("image")
    @Expose
    private Image image;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
