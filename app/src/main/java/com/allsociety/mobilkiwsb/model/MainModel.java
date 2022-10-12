package com.allsociety.mobilkiwsb.model;

import com.allsociety.mobilkiwsb.base.BaseModel;
import com.allsociety.mobilkiwsb.helper.SessionSingleton;

import API.APIFetcher;

public class MainModel extends BaseModel {

    private String login, password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Weryfikacja danych logowania
     */
    public boolean VerifyLoginData(){
       boolean correctLoginData =  new APIFetcher().LoginUser(login, password);
       if(correctLoginData){
           SessionSingleton.Login(getLogin());
       }
       return correctLoginData;
    }
}
