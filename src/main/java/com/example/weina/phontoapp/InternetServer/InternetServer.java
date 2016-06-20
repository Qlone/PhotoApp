package com.example.weina.phontoapp.InternetServer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.weina.phontoapp.SharedPreference.CreateSharedPreference;
import com.example.weina.phontoapp.SharedPreference.ICreateSharedPreference;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by weina on 2016/6/16.
 */
public class InternetServer extends Service {
    //binder 提供外界使用服务功能
    private String serverip;
    private ICreateSharedPreference sharedPreference;
    private Handler handler = new Handler();
    private ServerBinder mBinder = new ServerBinder();
    public  class ServerBinder extends Binder implements InterfaceServer{
        @Override
        public String sendFilePath(String path) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket(serverip, ICreateSharedPreference.SERVERPORT);
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        out.writeBytes("hello\0");
                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"网络连接失败，请检查ip或网络",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                }).start();

            return  "s";
        }
        //添加 服务功能
    }

    @Override
    public IBinder onBind(Intent intent){
        return  mBinder;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        sharedPreference = new CreateSharedPreference();
        serverip = sharedPreference.getServerIp(getBaseContext());
        Log.d("nod","onCreate");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d("nod","onStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
