package com.example.weina.phontoapp.InternetServer;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by weina on 2016/6/16.
 */
public class InternetPrenter {
    static private InternetPrenter mInstances;
    private InternetServer.ServerBinder serverBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
          serverBinder = (InternetServer.ServerBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    //构造函数
    public InternetPrenter getmInstances(){
        if(mInstances == null){
            mInstances = new InternetPrenter();
            return mInstances;
        }else{
            return mInstances;
        }
    }
}
