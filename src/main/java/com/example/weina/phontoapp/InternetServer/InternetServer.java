package com.example.weina.phontoapp.InternetServer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.weina.phontoapp.Modle.FileModelAdapter;
import com.example.weina.phontoapp.SharedPreference.CreateSharedPreference;
import com.example.weina.phontoapp.SharedPreference.ICreateSharedPreference;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by weina on 2016/6/16.
 */
public class InternetServer extends Service {
    //binder 提供外界使用服务功能
    private String serverip;
    Socket socket;
    private ICreateSharedPreference sharedPreference;
    private Handler handler = new Handler();
    private ServerBinder mBinder = new ServerBinder();
    public  class ServerBinder extends Binder implements InterfaceServer{
        //sendFile 需要用到的参数
        private FileModelAdapter modelAdapter;
        private XRecyclerView xRecyclerView;
        @Override
        public String sendFilePath(final String path, final FileModelAdapter modelAdapter, final XRecyclerView xRecyclerView) {
            //获取 数据容器，方便主线程更新
            this.modelAdapter = modelAdapter;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket = new Socket();
                        SocketAddress socketAddress = new InetSocketAddress(serverip,ICreateSharedPreference.SERVERPORT);
                        socket.connect(socketAddress,7000);//设置超时
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        out.write((path + "#getFilePath\0").getBytes("GBK"));
                        byte[] getrec = new byte[2500];
                        for (byte b: getrec) {
                            b = '\0';
                        }
                        input.read(getrec);
                        Log.w("getresult",new String(getrec,"gb2312"));
                        modelAdapter.updata(new String(getrec,"gb2312"));
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                modelAdapter.notifyDataSetChanged();
                                xRecyclerView.refreshComplete();
                               // Toast.makeText(getBaseContext(),"成功更新",Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"网络连接失败，请检查ip或网络",Toast.LENGTH_SHORT).show();
                                xRecyclerView.refreshComplete();
                            }
                        });
                    }finally {
                        deleteSocket(socket);
                    }
                }
                }).start();

            return  "s";
        }

        @Override
        public String getServerIp() {
            return serverip;
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
    private void deleteSocket(Socket socket){
        try {
            if (socket!=null && socket.isClosed() == false && socket.isConnected() ==true) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
