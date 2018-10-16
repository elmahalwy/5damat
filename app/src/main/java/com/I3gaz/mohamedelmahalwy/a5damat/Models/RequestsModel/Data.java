
package com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("order_id")
    @Expose
    private int orderId;
    @SerializedName("service_id")
    @Expose
    private int serviceId;
    @SerializedName("service_title")
    @Expose
    private String serviceTitle;
    @SerializedName("service_category")
    @Expose
    private String serviceCategory;
    @SerializedName("buyer")
    @Expose
    private String buyer;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("service_note")
    @Expose
    private String serviceNote;
    @SerializedName("service_roles")
    @Expose
    private String serviceRoles;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getServiceNote() {
        return serviceNote;
    }

    public void setServiceNote(String serviceNote) {
        this.serviceNote = serviceNote;
    }

    public String getServiceRoles() {
        return serviceRoles;
    }

    public void setServiceRoles(String serviceRoles) {
        this.serviceRoles = serviceRoles;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
