package com.baway.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.checked;
import static com.baway.myapplication.R.id.rc1;

public class MainActivity extends AppCompatActivity {
    private List<Data.DataBean> list = new ArrayList<>();
    private List<Data1>list1=new ArrayList<>();
    private RecyclerView rcl;
    private MyAdapter adapter;
    private RadioButton but;
    private TextView tv;
    float userprice;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = msg.obj.toString();
            Gson gson = new Gson();
            Data bean = gson.fromJson(result, Data.class);
            list.addAll(bean.getData());
            adapter.notifyDataSetChanged();
            for (Data.DataBean  hh:list
                 ) {
                list1.add(new Data1(false,hh.getImage_url(),35.5f));
            }
        }
    };
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcl = (RecyclerView) findViewById(rc1);
        but= (RadioButton) findViewById(R.id.bt_all);
        tv= (TextView) findViewById(R.id.text);
        init();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcl.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(list1, this);
        rcl.setAdapter(adapter);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.all();

            }
        });
    }

    private void init() {
        OkHttpClient okc = new OkHttpClient();
        Request req = new Request.Builder()
                .url("http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one")
                .build();
        okc.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String result = response.body().string();
                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        });

    }
    public void SetpPriceCount(float tprice){
        //接受传过来总价，总量
        userprice=tprice;

        tv.setText("总价："+userprice+"RMB");

    }
    public void  checked(boolean b){
        but.setChecked(b);

    }



}
