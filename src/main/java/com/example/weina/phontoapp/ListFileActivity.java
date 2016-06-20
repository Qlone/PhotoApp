package com.example.weina.phontoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.weina.phontoapp.InternetServer.InternetPrenter;
import com.example.weina.phontoapp.Modle.FileModel;
import com.example.weina.phontoapp.Modle.FileModelAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/6/16.
 */
public class ListFileActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private FileModelAdapter mAdaperter;
    private List<FileModel> mDatas;
    private InternetPrenter mprenter;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
        setContentView(R.layout.list_file_layout);

        initView();
       // Intent bindIntent = new Intent(this, InternetServer.class);
      //  bindService(bindIntent, connection, BIND_AUTO_CREATE);
        mprenter = InternetPrenter.getmInstances();
        mprenter.getiBinderAndBind(this);
    }
    public void initView(){
        mRecyclerView = (XRecyclerView) findViewById(R.id.list_file_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatas = new ArrayList<FileModel>();
        mDatas.add(new FileModel());
        mAdaperter = new FileModelAdapter(mDatas);
        mRecyclerView.setAdapter(mAdaperter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
               mprenter.senFilePath("w");
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                // load more data here
                mRecyclerView.loadMoreComplete();
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mprenter.UnBindService(this);
    }
}
