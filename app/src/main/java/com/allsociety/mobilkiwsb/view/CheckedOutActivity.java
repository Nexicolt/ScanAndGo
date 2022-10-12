package com.allsociety.mobilkiwsb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.base.BaseActivity;
import com.allsociety.mobilkiwsb.databinding.ActivityCartBinding;
import com.allsociety.mobilkiwsb.databinding.ActivityCheckedoutBinding;
import com.allsociety.mobilkiwsb.model.CheckedOutCart;
import com.allsociety.mobilkiwsb.viewmodel.CartViewModel;
import com.allsociety.mobilkiwsb.viewmodel.CheckedOutViewModel;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CheckedOutActivity extends BaseActivity {
    public ActivityCheckedoutBinding activityCheckedoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkedout);


        activityCheckedoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkedout);
        activityCheckedoutBinding.setViewModel(new CheckedOutViewModel(this));
        activityCheckedoutBinding.executePendingBindings();
    }

    public void goDashboard(){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}