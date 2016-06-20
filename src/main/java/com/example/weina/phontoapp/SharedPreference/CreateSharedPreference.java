package com.example.weina.phontoapp.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by weina on 2016/6/19.
 */
public class CreateSharedPreference implements ICreateSharedPreference{
    @Override
    public void setServerIp(Context context,String serverIp) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SERVERIP,serverIp);
        editor.commit();
    }

    @Override
    public String getServerIp(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getString(SERVERIP,"");
    }
}
