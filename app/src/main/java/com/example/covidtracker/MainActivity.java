package com.example.covidtracker;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.covidtracker.thread.CheckDataThread;
import com.example.covidtracker.utils.ButtonActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    public static String data = "\n\n";
    public static List<String> strDataList = new ArrayList<>();
    public static List<String> intDataList = new ArrayList<>();
    public static MainActivity instance;
    public static int pageNumber = 0;
    public static int componentNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        requestPermissions(new String[]
                        {Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}
                , 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rightButton = findViewById(R.id.rightbutton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonActions.toRightButton("right");
            }
        });
        Button leftButton = findViewById(R.id.leftbutton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonActions.toRightButton("left");
            }
        });
        Thread thread = new CheckDataThread();
        thread.start();
    }

    public static List<String> setToList(Set<Map.Entry<String,Integer>> set) {
        List<String> list = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String,Integer> str : set) {
            list.add(str.getKey());
            i++;
        }
        return list;
    }
}
