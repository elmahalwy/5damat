
package com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubService {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("sub_prices")
    @Expose
    private String subPrices;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSubPrices() {
        return subPrices;
    }

    public void setSubPrices(String subPrices) {
        this.subPrices = subPrices;
    }

}
