package com.allsociety.mobilkiwsb.model;

import androidx.lifecycle.MutableLiveData;

import com.allsociety.mobilkiwsb.base.BaseModel;
import com.allsociety.mobilkiwsb.view.listadapter.PromotionUILIstAdapter;

import java.util.ArrayList;
import java.util.List;

import API.APIFetcher;
import API.ResponseObject.DiscountedAssortment;

public class PromotionModel extends BaseModel {

    PromotionUILIstAdapter listAdapter;

    public MutableLiveData<List<DiscountedAssortment>> getListWithDiscountedAssortments() {
        return ListWithDiscountedAssortments;
    }

    public void setListWithDiscountedAssortments(MutableLiveData<List<DiscountedAssortment>> listWithDiscountedAssortments) {
        ListWithDiscountedAssortments = listWithDiscountedAssortments;
    }

    //Zawiera listę z asortymentami na promocji. Uzupełniane z API, nasłuchiwane w aktywności
    MutableLiveData<List<DiscountedAssortment>> ListWithDiscountedAssortments;

    public PromotionUILIstAdapter getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(PromotionUILIstAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    /**
     * Pobiera z API, przecenione asortymenty
     */
    public void FetchDiscountedAssortmentsFromAPI() {
        List<DiscountedAssortment> response = new APIFetcher().GetAllPromotions();
        ListWithDiscountedAssortments.postValue(response == null ? new ArrayList<>() : response);
    }
}
