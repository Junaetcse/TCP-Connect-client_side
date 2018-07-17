package com.androidsrc.client;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tkb on 2017-04-03.
 */

public class Common {
    public static final String name = "name";
    public static final String ip = "ip";
    private static final String pref = "ITC_Pref";
    public static final int port = 8080;
    public static final String ipDefault = "192.168.1.";

    private static SharedPreferences sharedpreferences=null;
    public static final String tea = "Tea";
    public static final String coffe = "Coffee";
    public static final String water = "Water";
    public static final String general = "General";
    public static final String breakfast = "Breakfast";
    public static final String juice = "Juice";


    public static SharedPreferences getPreference(Context context) {
        if (sharedpreferences == null) {
            return context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        } else {
             return sharedpreferences;
        }

    }

}
