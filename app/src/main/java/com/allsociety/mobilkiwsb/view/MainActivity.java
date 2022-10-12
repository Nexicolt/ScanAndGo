package com.allsociety.mobilkiwsb.view;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.base.BaseActivity;
import com.allsociety.mobilkiwsb.databinding.ActivityMainBinding;
import com.allsociety.mobilkiwsb.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new MainViewModel(this));
        activityMainBinding.executePendingBindings();
    }

    /**
     * Metoda wywoływana z VM, po otrzymaniu zwrotki z API
     */
    public void OnLoginRequest(boolean loginDataValid){
        if(loginDataValid){
            ShowToast("Zalogowano!");
        }else{
            ShowToast("Błędne dane logowania");
        }
        //Uruchom dashboard
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    /**
     * Jak użytkownik zostanie wylogowany, to mógłby się wrócić do poprzedniej aktywności
     */
    @Override
    public void onBackPressed() {}
}