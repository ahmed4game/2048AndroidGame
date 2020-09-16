package com.ahmed.ather.codechallenge.a2048ather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public final String KEY = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences("2048_preferences", Context.MODE_PRIVATE);
    }

    String[] getBoardKeySet(){
        return new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
    }

    public void storeScore(String key,String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getScore(String key,String defaultValue){
        return sharedpreferences.getString(key,defaultValue);
    }

    void showInfo(String msg){
        Log.i(BaseActivity.this.getLocalClassName(),msg);
    }

    void showLog(String msg){
        Log.i(BaseActivity.this.getLocalClassName(),msg);
    }
}