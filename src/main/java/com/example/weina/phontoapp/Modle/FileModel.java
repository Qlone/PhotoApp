package com.example.weina.phontoapp.Modle;

/**
 * Created by weina on 2016/6/17.
 */
public class FileModel implements IFileModle{
    private  boolean isFile;
    private String fileName;
    private double fileSize;
    private int donwlowState;

    public void setisFile(boolean isFile){
        this.isFile = isFile;
    }

    @Override
    public void setFileSize(double FileSize) {
        this.fileSize = FileSize;
    }

    @Override
    public void setDownLowState(int downLowState) {
        this.donwlowState = downLowState;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public boolean getisFile(){
        return isFile;
    }

    @Override
    public double getFileSize() {
        return fileSize;
    }

    @Override
    public int getDownLowState() {
        return donwlowState;
    }

    public String getFileName(){
        return fileName;
    }
}
