package com.example.weina.phontoapp.Modle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weina.phontoapp.R;

import java.util.List;

/**
 * Created by weina on 2016/6/17.
 */
public class FileModelAdapter extends RecyclerView.Adapter<FileModelAdapter.MyViewHolder>  {
    private List<FileModel> mDate;
    public FileModelAdapter(List<FileModel> mDate){
        this.mDate =  mDate;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.test_swipelayout,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //绑定view
        public MyViewHolder(View view) {
            super(view);
        }
    }
}
