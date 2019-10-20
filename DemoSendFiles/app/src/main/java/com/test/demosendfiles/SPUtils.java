package com.test.demosendfiles;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {

    private final String SP_NAME = "Sender_SP";

    public static final String SP_SERVER_IP = "sp_servier_ip";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    SPUtils(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SPUtils getInstance(Context context) {
        return new SPUtils(context);
    }

    public void putString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public String getString(String key) {
        return sp.getString(key, null);
    }
}
