
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
    @SerializedName("delivery_date")
    @Expose
    private String delivery_date;
    @SerializedName("time_left")
    @Expose
    private String time_left;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("order_time")
    @Expose
    private String order_time;


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

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getTime_left() {
        return time_left;
    }

    public void setTime_left(String time_left) {
        this.time_left = time_left;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
