package com.example.weina.phontoapp.SharedPreference;

import android.content.Context;

/**
 * Created by weina on 2016/6/19.
 */
public interface ICreateSharedPreference {
    public int SERVERPORT = 9000;
    public String SERVERIP = "serverIp";
    public void setServerIp(Context context, String serverIp);
    public String getServerIp(Context context);
}
