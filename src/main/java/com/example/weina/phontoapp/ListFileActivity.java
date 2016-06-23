package com.example.weina.phontoapp;

import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.weina.phontoapp.InternetServer.InternetPrenter;
import com.example.weina.phontoapp.Modle.FileModel;
import com.example.weina.phontoapp.Modle.FileModelAdapter;
import com.example.weina.phontoapp.Modle.IFileModle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/6/16.
 */
public class ListFileActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private FileModelAdapter mAdaperter;
    private List<IFileModle> mDatas;
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
        //初始化数据
        mDatas = new ArrayList<IFileModle>();
        mDatas.add(new FileModel());
        //初始化 容器
        mAdaperter = new FileModelAdapter(mDatas);
        mAdaperter.setOnItemClickListener(new FileModelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()){
                    case R.id.id_swipebutton_downlow:
                        Log.d("button","check");
                        break;
                    case R.id.up_wrapper:
                        Log.d("loyout","check");
                        if(mDatas.get(position-1).getisFile()) {
                            mAdaperter.addPath(mDatas.get(position - 1).getFileName());
                            mprenter.senFilePath(mAdaperter.getPath(), mAdaperter, mRecyclerView);
                        }
                        break;
                    case R.id.id_swipebutton_relate:
                        ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        String path = mAdaperter.getRstpPath(mprenter.getServrIp());
                        path += mDatas.get(position-1).getFileName();
                        cm.setText(path);
                        Toast.makeText(getBaseContext(),"链接已复制到粘贴板",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.d("nothing","check");
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(mAdaperter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                mprenter.senFilePath(mAdaperter.getPath(),mAdaperter,mRecyclerView);
            }

            @Override
            public void onLoadMore() {
                // load more data here
                mAdaperter.loadMoreData(mRecyclerView,mAdaperter);
            }
        });

    }
    @Override
    public void onBackPressed() {
        //返回上一个菜单
       if(mAdaperter.removePath()){
           mprenter.senFilePath(mAdaperter.getPath(),mAdaperter,mRecyclerView);
       }else{
           super.onBackPressed();
       }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mprenter.UnBindService(this);
    }
}
