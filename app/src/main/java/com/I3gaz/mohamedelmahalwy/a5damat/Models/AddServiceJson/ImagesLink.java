
package com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImagesLink {

    @SerializedName("image_link")
    @Expose
    private String imageLink;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
