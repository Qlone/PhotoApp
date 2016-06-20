package com.example.weina.phontoapp.View;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.weina.phontoapp.SharedPreference.CreateSharedPreference;
import com.example.weina.phontoapp.SharedPreference.ICreateSharedPreference;

/**
 * Created by weina on 2016/6/20.
 */
public class InitAppPresenter {
    private IInitApp appView;
    private ICreateSharedPreference preferencemodle;
    private Handler handler;
    private Context context;
    public InitAppPresenter(InitApp view){
        handler = new Handler();
        appView = view;
        context = view.getBaseContext();
        preferencemodle = new CreateSharedPreference();
        Log.d("here","sucess");

    }
    public String getIpFromPreference(){
        //先查看是否有存
        String serverIp  =  preferencemodle.getServerIp(context);
        if(serverIp.equals("")){
            return null;
        }else{
            return serverIp;
        }
    }
    public String getIpFromTextView(){
        String serverIp = appView.getIp();
        Log.d("getFromText","hello?");
        if(serverIp==null || serverIp.equals("")){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,"输入不能为空",Toast.LENGTH_LONG).show();
                }

            });
            return null;
        }else{
            preferencemodle.setServerIp(context,serverIp);
            return serverIp;
        }
    }

}
