package com.allsociety.mobilkiwsb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.allsociety.mobilkiwsb.base.DraverBaseActivity;
import com.allsociety.mobilkiwsb.databinding.ActivityDashboardBinding;
import com.allsociety.mobilkiwsb.viewmodel.DashboardViewModel;

public class DashboardActivity extends DraverBaseActivity {

    ActivityDashboardBinding _activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(_activityDashboardBinding.getRoot());

        _activityDashboardBinding.setViewModel(new DashboardViewModel());
        _activityDashboardBinding.executePendingBindings();
    }

    /**
     * Wciśnięto przycisk z ikonką koszyka
     */
    public void CartButtonClicked(View view){
        Intent cartIntent = new Intent(this, CartActivity.class);
        startActivity(cartIntent);
    }

    /**
     * Wciśnięto przycisk z ikonką dolara
     */
    public void PromotionButtonClicked(View view){
        Intent promotionIntent = new Intent(this, PromotionActivity.class);
        startActivity(promotionIntent);
    }

    /**
     * Zapobiegaj wracaniu, do strony logowania
     */
    @Override
    public void onBackPressed() {}
}