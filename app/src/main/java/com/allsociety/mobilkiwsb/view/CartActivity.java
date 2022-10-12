package com.allsociety.mobilkiwsb.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.base.BaseActivity;
import com.allsociety.mobilkiwsb.databinding.ActivityCartBinding;
import com.allsociety.mobilkiwsb.model.AssortmentModel;
import com.allsociety.mobilkiwsb.model.CartItemModel;
import com.allsociety.mobilkiwsb.model.CheckedOutCart;
import com.allsociety.mobilkiwsb.viewmodel.CartViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.Serializable;
import java.util.List;

import API.APIFetcher;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding activityCartBinding;
    public CartViewModel vm;
    public final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    ShowToast("Cancelled");
                } else {
                    vm.doScanCode(result.getContents());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ActivityCartBinding activityCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        activityCartBinding.setViewModel(new CartViewModel(this));
        activityCartBinding.executePendingBindings();
        vm=activityCartBinding.getViewModel();

    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.LoadItemFromCart();
    }

    public void checkedOut(){
        Intent intent=new Intent(this, CheckedOutActivity.class);
        startActivity(intent);
    }

    public void createDialog(AssortmentModel item, int lay){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final int[] quantityOfAssortment = new int[1];
        quantityOfAssortment[0]=1;
        builder.setView(inflater.inflate(lay, null))
                .setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        vm.addAssortmentToCart(item.getEanCode(), quantityOfAssortment[0], "Admin", false);
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        AlertDialog alert=builder.show();
        if(lay==R.layout.number_of_assortment_on_edit){
            Button bt=(Button)alert.findViewById(R.id.UsunZKoszyka);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vm.addAssortmentToCart(item.getEanCode(), quantityOfAssortment[0], "Admin", true);
                    alert.dismiss();
                }
            });
            /*builder.setNeutralButton("Usuń", new DialogInterface.OnClickListener() { // define the 'Cancel' button
                public void onClick(DialogInterface dialog, int which) {
                    //Either of the following two lines should work.
                    vm.addAssortmentToCart(item.getEanCode(), quantityOfAssortment[0], "Admin", true);
                    dialog.cancel();
                    //dialog.dismiss();
                }
            });*/
        }
        NumberPicker np = (NumberPicker)alert.findViewById(R.id.assortmentNumberOnDialog);
        TextView assName = (TextView)alert.findViewById(R.id.assortmentNameOnDialog);
        assName.setText(item.getCode());
        TextView assPrice = (TextView)alert.findViewById(R.id.assortmentPriceOnDialog);
        assPrice.setText("Cena: "+item.getPriceDiscounted());
        np.setMaxValue(99);
        np.setMinValue(1);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                quantityOfAssortment[0] =i1;
                assPrice.setText("Cena: "+item.getPriceDiscounted()*i1);
            }
        });
    }
}