package com.cookie.http_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age_tv)
    TextView ageTv;
    @BindView(R.id.age)
    EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    public void regist(View view) {
        String url = "http://192.168.1.107:8080/web/MyServlet";
        new RegistThread(url, name.getText().toString(), age.getText().toString()).start();
    }
}
