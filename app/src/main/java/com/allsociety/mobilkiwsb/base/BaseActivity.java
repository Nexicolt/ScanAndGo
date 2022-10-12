package com.allsociety.mobilkiwsb.base;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    /**
     * Pokaż spiner z informacją "prosze czekać"
     */
    public void ShowLoadingSpinner(){
        runOnUiThread( () -> progressDialog = ProgressDialog.show(this, "","Prosze czekać...", true));
    }

    /**
     * Ukryj spiner z informacją "prosze czekać"
     */
    public void HideLoadingSpinner(){
        runOnUiThread(() -> progressDialog.dismiss());
    }

    public void ShowToast(String message){
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show());
    }
    public void ShowToastLong(String message){
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }
}
