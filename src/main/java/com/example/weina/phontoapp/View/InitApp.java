package com.example.weina.phontoapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.weina.phontoapp.MainActivity;
import com.example.weina.phontoapp.R;

/**
 * Created by weina on 2016/6/20.
 */
public class InitApp extends AppCompatActivity implements IInitApp,View.OnClickListener{
    private EditText bsetIp;
    private Button bcheck;
    private InitAppPresenter appPresenter;
    Toolbar toolbar;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_app_view);
        initview();
    }
    protected void initview(){
        bsetIp = (EditText) findViewById(R.id.id_setip);
        bcheck =(Button) findViewById(R.id.id_button_checkip);
        bcheck.setOnClickListener(this);
        appPresenter = new InitAppPresenter(this);
        String serverIp = appPresenter.getIpFromPreference();

        if(serverIp != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void setIp(String serIp) {
        bsetIp.setText(serIp);

    }

    @Override
    public String getIp() {
        return bsetIp.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_button_checkip:
                Log.d("click","ok");
                String serverIp = appPresenter.getIpFromTextView();
                if(serverIp != null){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                Log.d("click","defaultok");
                break;
        }
    }
}
