package com.example.weina.phontoapp.InternetServer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.weina.phontoapp.Modle.FileModelAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by weina on 2016/6/16.
 */
public class InternetPrenter {
    static private InternetPrenter mInstances;
    private InternetServer.ServerBinder serverBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定后执行的函数
            serverBinder = (InternetServer.ServerBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    //构造函数
    static public InternetPrenter getmInstances(){
        if(mInstances == null){
            mInstances = new InternetPrenter();

            return mInstances;
        }else{
            return mInstances;
        }
    }
    public InternetServer.ServerBinder getiBinderAndBind(Context context){
        Intent bindIntent = new Intent(context, InternetServer.class);
        context.bindService(bindIntent, connection, context.BIND_AUTO_CREATE);

        return serverBinder;
    }
    public void UnBindService(Context context){
        context.unbindService(connection);
    }
    public void senFilePath(String path, FileModelAdapter modelAdapter, XRecyclerView xRecyclerView){
        serverBinder.sendFilePath(path,modelAdapter,xRecyclerView);
    }
    public String getServrIp(){
        return serverBinder.getServerIp();
    }
}
