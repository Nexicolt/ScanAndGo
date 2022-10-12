package com.allsociety.mobilkiwsb.viewmodel;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.allsociety.mobilkiwsb.BR;
import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.base.BaseViewModel;
import com.allsociety.mobilkiwsb.model.CartItemListAdapter;
import com.allsociety.mobilkiwsb.model.CartItemModel;
import com.allsociety.mobilkiwsb.model.CheckedOutCart;
import com.allsociety.mobilkiwsb.view.CheckedOutActivity;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import API.APIFetcher;

public class CheckedOutViewModel extends BaseViewModel {

    private CheckedOutActivity _checkedOutActivity;
    private CheckedOutCart checkedOutCart;
    private String totalPrice;
    private static Bitmap barcodes;


    public CheckedOutViewModel(CheckedOutActivity checkedOutActivity){
        this._checkedOutActivity=checkedOutActivity;
        getCheckedOutCart();
    }

    public void setCheckedOutCart(CheckedOutCart checkedOutCart){
        this.checkedOutCart=checkedOutCart;
    }
    @Bindable
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
        notifyPropertyChanged(BR.totalPrice);
    }

    @Bindable
    public static Bitmap getBarcodes(){
        return barcodes;
    }
    public static void setBarcodes(Bitmap src){
        barcodes=src;
    }
    @BindingAdapter("android:src")
    public static void setBarcodes(ImageView view, Bitmap src){
        barcodes=src;
        view.setImageBitmap(barcodes);
    }

    public void goBackButton(){
        _checkedOutActivity.ShowToastLong("Powrót do koszyka");
        _checkedOutActivity.onBackPressed();
    }
    public void goDashboardButton(){
        _checkedOutActivity.goDashboard();
    }

    public void getCheckedOutCart(){
        CompletableFuture test = CompletableFuture.runAsync(() -> {
            CheckedOutCart checkedOutCart = new APIFetcher().getCartBarcode("Admin");
            setCheckedOutCart(checkedOutCart);
        });
        try{
            test.get();
            //_checkedOutActivity.generateBarCode(checkedOutCart.getCartBarode());
            if(checkedOutCart.getTotal()<=0) {
                _checkedOutActivity.ShowToastLong("Koszyk jest pusty");
                _checkedOutActivity.onBackPressed();
            }
            generateBarCode(checkedOutCart.getCartBarode());
            setTotalPrice(String.valueOf(checkedOutCart.getTotal()));
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    public void generateBarCode(String contents){
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            _checkedOutActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(contents, BarcodeFormat.EAN_13, width-100, 400);
            /*ImageView imageViewQrCode = (ImageView) findViewById(R.id.BarCode);
            imageViewQrCode.setImageBitmap(bitmap);*/
            setBarcodes(_checkedOutActivity.findViewById(R.id.BarCode),bitmap);
        } catch(Exception e) {
            e.getStackTrace();
        }
    }
}
