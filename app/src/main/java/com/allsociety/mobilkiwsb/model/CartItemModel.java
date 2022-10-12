package com.allsociety.mobilkiwsb.model;

import com.allsociety.mobilkiwsb.base.BaseModel;
import com.google.gson.annotations.SerializedName;

import API.APIFetcher;

public class CartItemModel extends BaseModel {

    @SerializedName("code")
    private String code;
    @SerializedName("quantity")
    private float quantity;
    @SerializedName("price")
    private float price;
    @SerializedName("eanCode")
    private String eanCode;
    @SerializedName("discount")
    private float discount;

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }


    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceDiscounted(){
        return (price*quantity-((price*quantity)*(discount/100)));
    }
}
