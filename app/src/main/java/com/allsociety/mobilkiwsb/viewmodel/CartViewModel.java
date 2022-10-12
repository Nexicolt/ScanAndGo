package com.allsociety.mobilkiwsb.viewmodel;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.databinding.Bindable;

import com.allsociety.mobilkiwsb.BR;
import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.base.BaseViewModel;
import com.allsociety.mobilkiwsb.model.AssortmentModel;
import com.allsociety.mobilkiwsb.model.CartItemListAdapter;
import com.allsociety.mobilkiwsb.model.CartItemModel;
import com.allsociety.mobilkiwsb.model.CheckedOutCart;
import com.allsociety.mobilkiwsb.model.MainModel;
import com.allsociety.mobilkiwsb.view.CartActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import API.APIFetcher;

public class CartViewModel extends BaseViewModel {

    private CartActivity _cartActivity; //Uchwyt do głównej aktywności
    private CartItemListAdapter listViewAdapter;
    private AssortmentModel bridgeAssortmentModel;


    public CartViewModel(CartActivity cartActivity) {
        this._cartActivity = cartActivity;
        LoadItemFromCart();
    }

    @Bindable
    public CartItemListAdapter getListViewAdapter() {
        return listViewAdapter;
    }
    public void setListViewAdapter(CartItemListAdapter listViewAdapter) {
        this.listViewAdapter = listViewAdapter;
        notifyPropertyChanged(BR.listViewAdapter);
    }

    public void setbridgeAssortmentModel(AssortmentModel ass){
        this.bridgeAssortmentModel=ass;
    }

    public void LoadItemFromCart(){
            CompletableFuture.runAsync(() -> {
                List<CartItemModel> tmpList = new APIFetcher().assortmentInCart("Admin");
                CartItemListAdapter adapter = new CartItemListAdapter(_cartActivity, new ArrayList<CartItemModel>(tmpList));
                setListViewAdapter(adapter);
            });
    }

    public void doScanCode(String barcode){
        CompletableFuture test = CompletableFuture.runAsync(() -> {
            AssortmentModel scannedAssortment = new APIFetcher().getAssortmentByEan(barcode);
            setbridgeAssortmentModel(scannedAssortment);
        });
        try{
            test.get();
            if(bridgeAssortmentModel.getCode()!=null&&bridgeAssortmentModel.getCode()!="Brak")
                _cartActivity.createDialog(bridgeAssortmentModel, R.layout.number_of_assortment_after_scann);
            else if(bridgeAssortmentModel.getCode()=="Brak")
                _cartActivity.ShowToastLong("Brak asortymentu");
            else
                _cartActivity.ShowToastLong("Błąd połączenia z serwerem");
        }catch (Exception e){
        }

    }

    public void addAssortmentToCart(String eanCode, float quantity, String userCode, boolean remove){
        CompletableFuture.runAsync(() -> {
            if(new APIFetcher().addAssortmentToCart(eanCode, quantity, userCode, remove));
                LoadItemFromCart();
        });
    }


    public void itemFromListSelected(int position){
        _cartActivity.createDialog(new AssortmentModel((CartItemModel)listViewAdapter.getItem(position)),R.layout.number_of_assortment_on_edit);
    }

    public void backButtonClicked(){
        _cartActivity.onBackPressed();
    }
    public void doneButtonClicked(){
        _cartActivity.checkedOut();
    }
    public void scanButtonClicked(){
        _cartActivity.barcodeLauncher.launch(new ScanOptions());
    }
}
