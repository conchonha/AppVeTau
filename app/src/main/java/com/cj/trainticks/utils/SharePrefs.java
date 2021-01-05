package com.cj.trainticks.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharePrefs {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditTor;
    private Gson mGson = new Gson();
    private String TAG = "SharePrefs";

    public SharePrefs(Context context) {
        mSharedPreferences = context.getSharedPreferences("datalogin", context.MODE_PRIVATE);
        mEditTor = mSharedPreferences.edit();
    }

    public void saveUser(String user) {
        mEditTor.putString(Constain.keyUser, user).commit();
    }

    public String getUser(){
        return mSharedPreferences.getString(Constain.keyUser,"");
    }
}
