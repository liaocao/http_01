package com.cookie.http_01;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cookie.http_01.HttpClient.HttpClientRegistActivity;
import com.cookie.http_01.multithread.DownLoadActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] {"title"}, new int[] {android.R.id.text1}));
    }

    protected List<Map<String, Object>> getData(){
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
        addItem(myData, "SimpleActivity", new Intent(this, SimpleActivity.class));
        addItem(myData, "ImageActivity", new Intent(this, ImageActivity.class));
        addItem(myData, "RegistActivity", new Intent(this, RegistActivity.class));
        addItem(myData, "HttpClientRegistActivity", new Intent(this, HttpClientRegistActivity.class));
        addItem(myData, "DownLoadActivity", new Intent(this, DownLoadActivity.class));
        return myData;
    }

    private void addItem(List<Map<String, Object>> data, String title, Intent intent){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", title);
        map.put("intent", intent);

        data.add(map);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent i = (Intent) map.get("intent");
        startActivity(i);
    }
}