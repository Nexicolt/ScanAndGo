package com.allsociety.mobilkiwsb.helper;

public class SessionSingleton {
    private static boolean IsUserLogged;
    private static String Username;

    public static void Login(String username){
        Username = username;
    }

    public static void Logout(){
        Username = null;
        IsUserLogged = false;
    }

    public static boolean IsLogged(){
        return IsUserLogged;
    }
}
