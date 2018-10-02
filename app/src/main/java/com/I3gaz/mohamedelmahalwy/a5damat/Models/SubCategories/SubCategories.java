
package com.I3gaz.mohamedelmahalwy.a5damat.Models.SubCategories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategories {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("data")
    @Expose
    private List<SubCategoriesDatum> data = null;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public List<SubCategoriesDatum> getData() {
        return data;
    }

    public void setData(List<SubCategoriesDatum> data) {
        this.data = data;
    }

}
