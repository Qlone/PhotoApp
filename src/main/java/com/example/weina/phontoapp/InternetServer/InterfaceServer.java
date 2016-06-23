package com.example.weina.phontoapp.InternetServer;

import com.example.weina.phontoapp.Modle.FileModelAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by weina on 2016/6/19.
 */
public interface InterfaceServer {
    //Bind函数里面的接口
    //发送文件路径，返回文件目录
    public String sendFilePath(String path , FileModelAdapter modelAdapter, XRecyclerView xRecyclerView);
    public String getServerIp();
}
