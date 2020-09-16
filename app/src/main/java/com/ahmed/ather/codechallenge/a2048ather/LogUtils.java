package com.ahmed.ather.codechallenge.a2048ather;

import android.util.Log;

public class LogUtils {

    public static void showLog(String msg){
        Log.d("LOG",msg);
    }

    public static void showError(String msg){
        Log.e("LOG",msg);
    }

    public static void showInfo(String msg){
        Log.i("LOG",msg);
    }

    public static void showLog(String tag,String msg){
        Log.d(tag,msg);
    }

    public static void showError(String tag,String msg){
        Log.e(tag,msg);
    }

    public static void showInfo(String tag,String msg){
        Log.i(tag,msg);
    }

}
