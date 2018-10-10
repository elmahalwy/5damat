package com.I3gaz.mohamedelmahalwy.a5damat.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddService {

    @SerializedName("value")
    @Expose
    private boolean value;


    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
