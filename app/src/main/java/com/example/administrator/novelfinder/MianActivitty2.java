package com.example.administrator.novelfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MianActivitty2 extends AppCompatActivity {
    private List<Date> dates = new ArrayList<Date>();
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar1 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar1);
        toolbar1.setNavigationIcon(R.drawable.ic_back);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initdata();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(dates, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(manager);

    }

    private void initdata() {
        HttpConnect httpConnect = new HttpConnect("https://www.apiopen.top/novelSearchApi?name=盗墓笔记");
        httpConnect.sendRequestWithHttpURLConnection(new HttpConnect.Callback() {
            @Override
            public void finish(String respone) {
                parseJSON(respone);
            }
        });
    }

    private void parseJSON(String respone) {
        try {
            JSONObject jsonObject = new JSONObject(respone);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            for (int i = 0; i < jsonArray.length(); i++) {
                Date date = new Date(jsonArray.get(i).toString());
                dates.add(date);
            }
//            String a =jsonObject.getString("data");
//            Log.d("main",a);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerAdapter.notifyDataSetChanged();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
