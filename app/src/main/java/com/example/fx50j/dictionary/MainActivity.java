package com.example.fx50j.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private ListView listview;
    private SimpleAdapter simple_ad;
    private List<Map<String,Object>>dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.list);
        dataList=new ArrayList<Map<String, Object>>();
        simple_ad=new SimpleAdapter(this,getData(),R.layout.item_layout,new String[]{"it_image","title","content"},new int[]{R.id.it_image,R.id.title,R.id.content});
        listview.setAdapter(simple_ad);

        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(MainActivity.this, shortActivity.class);
                startActivity(in);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.setAlpha(0.5f);
                Intent in =new Intent(MainActivity.this,longActivity.class);
                startActivity(in);
                return false;
            }
        });
    }

    private List<Map<String,Object>> getData(){
        for(int i=0;i<20;i++)
        {
            Map<String,Object>map=new HashMap<String, Object>();
            map.put("it_image",R.drawable.tubiao);
            map.put("title","apple"+(i+1));
            map.put("content","一个简单单词的测试");
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text=listview.getItemAtPosition(position)+"";
        Toast.makeText(this, "position is "+(position+1), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"长按了位置"+(position+1),Toast.LENGTH_LONG).show();
        return false;
    }
}