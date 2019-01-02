package com.cookie.http_01;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView1;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView1 = findViewById(R.id.imageView1);

        new ImageHttpThread("http://img4.imgtn.bdimg.com/it/u=3335258443,1321329535&fm=11&gp=0.jpg"
        , imageView1, handler).start();
        //严重注意，这里的url不能为https
    }
}
