
package com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddServiceJson {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("category_id")
    @Expose
    private int categoryId;
    @SerializedName("sub_category_id")
    @Expose
    private int subCategoryId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("key_words")
    @Expose
    private String keyWords;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("roles")
    @Expose
    private String roles;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("images_links")
    @Expose
    private List<ImagesLink> imagesLinks = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("image_files_sizes")
    @Expose
    private List<ImageFilesSize> imageFilesSizes = null;
    @SerializedName("developments")
    @Expose
    private List<Development> developments = null;

    public AddServiceJson(Parcel in) {
        title = in.readString();
        price = in.readString();
        categoryId = in.readInt();
        subCategoryId = in.readInt();
        note = in.readString();
        keyWords = in.readString();
        deadline = in.readString();
        roles = in.readString();
        userId = in.readInt();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<ImagesLink> getImagesLinks() {
        return imagesLinks;
    }

    public void setImagesLinks(List<ImagesLink> imagesLinks) {
        this.imagesLinks = imagesLinks;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ImageFilesSize> getImageFilesSizes() {
        return imageFilesSizes;
    }

    public void setImageFilesSizes(List<ImageFilesSize> imageFilesSizes) {
        this.imageFilesSizes = imageFilesSizes;
    }

    public List<Development> getDevelopments() {
        return developments;
    }

    public void setDevelopments(List<Development> developments) {
        this.developments = developments;
    }
}
