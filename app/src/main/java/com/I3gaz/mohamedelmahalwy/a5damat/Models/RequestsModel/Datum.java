
package com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {


    @SerializedName("order_id")
    @Expose
    private int orderId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("buyer")
    @Expose
    private String buyer;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tab_type")
    @Expose
    private String tab_type;



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTab_type() {
        return tab_type;
    }

    public void setTab_type(String tab_type) {
        this.tab_type = tab_type;
    }
}
