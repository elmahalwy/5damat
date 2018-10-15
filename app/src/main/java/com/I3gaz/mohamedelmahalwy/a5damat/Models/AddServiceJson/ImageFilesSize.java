
package com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageFilesSize {

    @SerializedName("size")
    @Expose
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
