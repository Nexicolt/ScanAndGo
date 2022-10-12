package com.allsociety.mobilkiwsb.model;

import com.allsociety.mobilkiwsb.base.BaseModel;
import com.google.gson.annotations.SerializedName;

public class AssortmentModel extends BaseModel {

    @SerializedName("code")
    private String code;
    @SerializedName("price")
    private float price;
    @SerializedName("eanCode")
    private String eanCode;
    @SerializedName("discount")
    private float discount;

    public AssortmentModel(CartItemModel cartItemModel){
        this.code=cartItemModel.getCode();
        this.price=cartItemModel.getPrice();
        this.eanCode= cartItemModel.getEanCode();
        this.discount=cartItemModel.getDiscount();
    }
    public AssortmentModel(){}

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceDiscounted(){
        return (price*1-((price*1)*(discount/100)));
    }
}
