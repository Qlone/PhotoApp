package com.example.weina.phontoapp.InternetServer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by weina on 2016/6/16.
 */
public class InternetServer extends Service {
    //binder 提供外界使用服务功能
    private ServerBinder mBinder = new ServerBinder();
    class ServerBinder extends Binder{
        //添加 服务功能
    }

    @Override
    public IBinder onBind(Intent intent){
        return  null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
