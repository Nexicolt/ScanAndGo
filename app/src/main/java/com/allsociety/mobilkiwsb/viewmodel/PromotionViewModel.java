package com.allsociety.mobilkiwsb.viewmodel;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;

import com.allsociety.mobilkiwsb.base.BaseViewModel;
import com.allsociety.mobilkiwsb.model.PromotionModel;
import com.allsociety.mobilkiwsb.view.PromotionActivity;
import com.allsociety.mobilkiwsb.view.listadapter.PromotionUILIstAdapter;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class PromotionViewModel extends BaseViewModel {

    PromotionActivity _promotionActivity;
    PromotionModel _model;

    //Adapter, przystosowujący wyświetlanie elementów list
    PromotionUILIstAdapter _listAdapter;

    public PromotionViewModel(PromotionActivity promotionActivity) {

        //Inicjalizacja pustych wartości, żeby nie były null'ami
        this._promotionActivity = promotionActivity;
        _model = new PromotionModel();

        _model.setListWithDiscountedAssortments(new MutableLiveData<>(new ArrayList<>()));
        this._listAdapter = new PromotionUILIstAdapter(this._promotionActivity.getApplicationContext(), _model.getListWithDiscountedAssortments().getValue());

        //Obserwuj listę z asortymentami. Jak się zmieni, to wywołaj setter i zaktualizuj UI
        _model.getListWithDiscountedAssortments().observe(_promotionActivity, changedList -> {
            setListAdapter(new PromotionUILIstAdapter(_promotionActivity, changedList));
        });

        //Asynchroniczne odpytanie API, o listę asortymentów
        FetchDiscountedAssortmentsFromAPI();
    }

    /**
     * Obsługa odpytania API, o przecenione asortymenty
     */
    private void FetchDiscountedAssortmentsFromAPI(){
        _promotionActivity.ShowLoadingSpinner();

        CompletableFuture.runAsync(() -> {
            _model.FetchDiscountedAssortmentsFromAPI();
            _promotionActivity.HideLoadingSpinner();
        });

    }
    /////////////////////////////
    //// GET & SET DLA MVVM ////
    ////////////////////////////
    @Bindable
    public PromotionUILIstAdapter getListAdapter() {
        return _model.getListAdapter();
    }

    public void setListAdapter(PromotionUILIstAdapter listAdapter) {
        _model.setListAdapter(listAdapter);
        notifyPropertyChanged(BR.listAdapter);

    }

}
