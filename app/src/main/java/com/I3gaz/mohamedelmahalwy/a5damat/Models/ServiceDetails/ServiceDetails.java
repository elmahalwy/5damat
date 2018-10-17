
package com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceDetails implements Parcelable {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("data")
    @Expose
    private Data data;

    protected ServiceDetails(Parcel in) {
        value = in.readByte() != 0;
    }

    public static final Creator<ServiceDetails> CREATOR = new Creator<ServiceDetails>() {
        @Override
        public ServiceDetails createFromParcel(Parcel in) {
            return new ServiceDetails(in);
        }

        @Override
        public ServiceDetails[] newArray(int size) {
            return new ServiceDetails[size];
        }
    };

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (value ? 1 : 0));
    }
}
