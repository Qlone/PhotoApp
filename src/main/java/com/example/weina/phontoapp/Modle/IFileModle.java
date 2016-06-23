package com.example.weina.phontoapp.Modle;

/**
 * Created by weina on 2016/6/20.
 */
public interface IFileModle {
    public String getFileName();
    public boolean getisFile();
    public double getFileSize();
    public int getDownLowState();
    //设置
    public void setFileName(String FileName);
    public void setisFile(boolean isFile);
    public void setFileSize(double FileSize);
    public void setDownLowState(int downLowState);
}
