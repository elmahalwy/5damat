
package com.I3gaz.mohamedelmahalwy.a5damat.Models.MyBalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balance {

    @SerializedName("value")
    @Expose
    private boolean value;
    @SerializedName("total_balance")
    @Expose
    private int totalBalance;
    @SerializedName("dragable_balance")
    @Expose
    private int dragableBalance;
    @SerializedName("paused_balance")
    @Expose
    private int pausedBalance;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getDragableBalance() {
        return dragableBalance;
    }

    public void setDragableBalance(int dragableBalance) {
        this.dragableBalance = dragableBalance;
    }

    public int getPausedBalance() {
        return pausedBalance;
    }

    public void setPausedBalance(int pausedBalance) {
        this.pausedBalance = pausedBalance;
    }

}
