package com.allsociety.mobilkiwsb.viewmodel;

import androidx.databinding.Bindable;

import com.allsociety.mobilkiwsb.BR;
import com.allsociety.mobilkiwsb.base.BaseViewModel;
import com.allsociety.mobilkiwsb.model.MainModel;
import com.allsociety.mobilkiwsb.view.MainActivity;

import java.util.concurrent.CompletableFuture;

public class MainViewModel extends BaseViewModel {

    private MainModel _mainModel;
    private MainActivity _mainActivity; //Uchwyt do głównej aktywności

    public MainViewModel(MainActivity mainActivity) {
        this._mainModel = new MainModel();
        this._mainActivity = mainActivity;
    }

    @Bindable
    public String getLogin() {
        return _mainModel.getLogin();
    }

    public void setLogin(String login) {
        _mainModel.setLogin(login);
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public String getPassword() {
        return _mainModel.getPassword();
    }

    public void setPassword(String password) {
        _mainModel.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    /**
     * Kliknięto przycisk od zalogowania
     */
    public void loginButtonClicked(){
        _mainActivity.ShowLoadingSpinner();
        CompletableFuture.runAsync(() ->{
            //Wynik przekazany do aktywności, żeby móc operować na UI
            _mainActivity.OnLoginRequest(_mainModel.VerifyLoginData());
            _mainActivity.HideLoadingSpinner();
        });
    }
}
