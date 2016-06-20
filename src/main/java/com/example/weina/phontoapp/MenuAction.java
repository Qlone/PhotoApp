package com.example.weina.phontoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weina.phontoapp.SharedPreference.CreateSharedPreference;
import com.example.weina.phontoapp.SharedPreference.ICreateSharedPreference;

/**
 * Created by weina on 2016/6/20.
 */
public class MenuAction {
    static private ICreateSharedPreference sharedPreference;
    static public void setIp(final Context context){
        sharedPreference = new CreateSharedPreference();
        LayoutInflater factory = LayoutInflater.from((MainActivity)context);//提示框
        final View view = factory.inflate(R.layout.dialog_set_serverip, null);//这里必须是final的
        final EditText edit=(EditText)view.findViewById(R.id.id_dialog_setip);//获得输入框对象
        edit.setText(sharedPreference.getServerIp(context));//设置数值
        new AlertDialog.Builder((MainActivity)context)
                .setTitle("更改ip")//提示框标题
                .setView(view)
                .setPositiveButton("确定",//提示框的两个按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //事件
                                if(edit.getText().toString() == null || edit.getText().toString().equals("")){
                                    Toast.makeText(context,"更改失败",Toast.LENGTH_SHORT).show();
                                }else{
                                    sharedPreference.setServerIp(context,edit.getText().toString());
                                    Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("取消", null).create().show();

    }

}
