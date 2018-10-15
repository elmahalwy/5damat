
package com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("like")
    @Expose
    private boolean like;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("owner_image")
    @Expose
    private String ownerImage;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("videos")
    @Expose
    private List<String> videos = null;
    @SerializedName("key_words")
    @Expose
    private Object keyWords;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("owner_id")
    @Expose
    private int ownerId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("roles")
    @Expose
    private String roles;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("order_count")
    @Expose
    private int orderCount;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("developments")
    @Expose
    private List<Development> developments = null;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public Object getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Object keyWords) {
        this.keyWords = keyWords;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Development> getDevelopments() {
        return developments;
    }

    public void setDevelopments(List<Development> developments) {
        this.developments = developments;
    }

}
