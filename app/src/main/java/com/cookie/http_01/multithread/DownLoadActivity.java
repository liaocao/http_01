package com.cookie.http_01.multithread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cookie.http_01.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownLoadActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    private int count = 0;

    private Handler handler = new Handler(){

        public void handleMessage(Message msg){
            int result = msg.what;
            count += result;
            if(count == 3){
                textView.setText("download success");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        new Thread(){
            @Override
            public void run(){
                Download download = new Download(handler);
                download.downLoadFile("http://192.168.1.3:8080/web/pic.jpg");
            }
        }.start();
    }
}
