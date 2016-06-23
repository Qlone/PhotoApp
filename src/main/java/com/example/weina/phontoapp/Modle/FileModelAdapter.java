package com.example.weina.phontoapp.Modle;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weina.phontoapp.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by weina on 2016/6/17.
 */
public class FileModelAdapter extends RecyclerView.Adapter<FileModelAdapter.MyViewHolder>  {
    private List<IFileModle> mDate;
    private List<String> sPath;
    private int updataIndex;
    private String saveDateString;
    public FileModelAdapter(List<IFileModle> mDate){
        this.mDate =  mDate;
        sPath = new ArrayList<String>();
        sPath.add(".");
        updataIndex=0;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.test_swipelayout,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
    //设置数据
        holder.mFileNameText.setText(mDate.get(position).getFileName());
        holder.mFileSizeText.setText(String.valueOf(mDate.get(position).getFileSize())+" kb");
        if(mDate.get(position).getisFile()) {
            //如果是文件设置为另外一个类型
            holder.mFileImage.setImageResource(R.drawable.folder);
        }else{
            holder.mFileImage.setImageResource(R.drawable.file);
        }
        //若回调，则设置点击事件
        if(mOnItemClickListener != null){
            holder.mDownlowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.mDownlowButton,pos);
                }
            });
            //下载按钮设置监听
            holder.mRelateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.mRelateButton,pos);
                }
            });

            holder.mSwipeLayout.setOnTouchListener(new View.OnTouchListener() {
                float xDown=0,xUp=0;
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        Log.d("事件","点击");
                        xDown = motionEvent.getX();
                        return  true;
                    }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        Log.d("事件","取消点击");
                        xUp = motionEvent.getX();
                        if((xUp - xDown)<30&&(xUp - xDown)>-30){
                            int pos = holder.getLayoutPosition();
                            mOnItemClickListener.onItemClick(holder.mSwipeLayout,pos);
                        }
                        return true;
                    }
                    Log.d("没有进入","+10000000");
                    //没有触发时间重新辅助
                    xDown=-130;
                    xUp= -60;
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //绑定view
        private LinearLayout mSwipeLayout;
        private ImageView mFileImage;
        private TextView mFileNameText;
        private TextView mFileSizeText;
        private Button mDownlowButton;
        private Button mRelateButton;
        public MyViewHolder(View view) {
            super(view);
            mFileImage = (ImageView)view.findViewById(R.id.id_image_fileSyle);
            mFileNameText = (TextView)view.findViewById(R.id.id_text_filename);
            mFileSizeText = (TextView)view.findViewById(R.id.id_text_filesize);
            //试试绑定layout
            mDownlowButton = (Button) view.findViewById(R.id.id_swipebutton_downlow);
            mSwipeLayout = (LinearLayout) view.findViewById(R.id.up_wrapper);
            mRelateButton = (Button) view.findViewById(R.id.id_swipebutton_relate);
        }
    }
    //设置接口
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }


    //更新数据
    public int updata(String source){
        int count =0;//计数
        updataIndex =0;
        saveDateString = source;
        mDate.clear();
        Pattern pattern =Pattern.compile("#");
        final String[] rs=pattern.split(source);
        try {
            //加载前15个
            while (!rs[count].equals("end")&&count<45) {
                if (rs[count].equals(".") || rs[count].equals("..")) {
                    //do nothing
                } else {

                    IFileModle modle = new FileModel();
                    modle.setFileName(rs[count]);
                    modle.setisFile(rs[count + 1].equals("[folder]"));
                    modle.setFileSize(Integer.parseInt(rs[count + 2]));
                    mDate.add(modle);
                }
                //下一个
                updataIndex++;
                count += 3;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            mDate.clear();
            Log.d("更新失败","失败了————————————");
        }
        Log.d("已经加载了：",String.valueOf(updataIndex));
        return count/3;
    }
    //加载更多
    public int loadMoreData(final XRecyclerView xrecycler,final FileModelAdapter adapter){
        Log.d("have you log?","yes");
        int count = updataIndex*3;
        int i = updataIndex*3;
        Pattern pattern =Pattern.compile("#");
        final String[] rs=pattern.split(saveDateString);
        try {
            //加载15个
            while (!rs[count].equals("end")&&count<45+i) {
                if (rs[count].equals(".") || rs[count].equals("..")) {
                    //do nothing
                } else {

                    IFileModle modle = new FileModel();
                    modle.setFileName(rs[count]);
                    modle.setisFile(rs[count + 1].equals("[folder]"));
                    modle.setFileSize(Integer.parseInt(rs[count + 2]));
                    mDate.add(modle);
                }
                //下一个
                updataIndex++;
                count += 3;

            }
        }catch (ArrayIndexOutOfBoundsException e){
            mDate.clear();
            Log.d("更新失败","失败了————————————");
        }finally {
            Handler  handle = new Handler();
            handle.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    xrecycler.loadMoreComplete();
                }
            });
        }
        Log.d("zai加载了：",String.valueOf(updataIndex));
        return 0;
    }
    public void addPath(String string){
        sPath.add(string);
    }
    //不可移除时返回fasle
    public boolean removePath(){
        if(sPath.size()!= 1){
            sPath.remove(sPath.size()-1);
            return true;
        }
        return false;
    }
    public String getPath(){
        String res = new String();
        for (String s:sPath) {
            res +=s+"\\";
        }
        return res;
    }
    public String getRstpPath(String serverip){
        String path = "rtsp://" +serverip +"/";
        for (String s:sPath) {
            if(!s.equals(".")){
                path+=s +"/";
            }
        }
        return  path;
    }
    public void initIndex(){
        updataIndex = 0;
    }
}
