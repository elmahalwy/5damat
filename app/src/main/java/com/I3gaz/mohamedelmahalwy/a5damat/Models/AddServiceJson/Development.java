
package com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Development {

    @SerializedName("developments_details")
    @Expose
    private String developmentsDetails;
    @SerializedName("development_price")
    @Expose
    private String developmentPrice;
    @SerializedName("will_expand_the_delivery_date")
    @Expose
    private String willExpandTheDeliveryDate;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;

    public String getDevelopmentsDetails() {
        return developmentsDetails;
    }

    public void setDevelopmentsDetails(String developmentsDetails) {
        this.developmentsDetails = developmentsDetails;
    }

    public String getDevelopmentPrice() {
        return developmentPrice;
    }

    public void setDevelopmentPrice(String developmentPrice) {
        this.developmentPrice = developmentPrice;
    }

    public String getWillExpandTheDeliveryDate() {
        return willExpandTheDeliveryDate;
    }

    public void setWillExpandTheDeliveryDate(String willExpandTheDeliveryDate) {
        this.willExpandTheDeliveryDate = willExpandTheDeliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
