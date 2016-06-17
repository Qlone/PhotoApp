package com.example.weina.phontoapp.Modle;

/**
 * Created by weina on 2016/6/17.
 */
public class FileModel {
    private  boolean isFile;
    private String fileName;

    public void setisFile(boolean isFile){
        this.isFile = isFile;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public boolean getisFile(){
        return isFile;
    }
    public String getFileName(){
        return fileName;
    }
}
