package com.allsociety.mobilkiwsb.model;

import com.allsociety.mobilkiwsb.base.BaseModel;
import com.google.gson.annotations.SerializedName;

public class CheckedOutCart extends BaseModel {
    @SerializedName("cartBarode")
    private String cartBarode;
    @SerializedName("sum")
    private float total;

    public String getCartBarode() {
        return cartBarode;
    }

    public void setCartBarode(String cartBarode) {
        this.cartBarode = cartBarode;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
