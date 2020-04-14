package com.example.covidtracker.thread;

import android.widget.TextView;

import com.example.covidtracker.MainActivity;
import com.example.covidtracker.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class CheckDataThread extends Thread {


    public static boolean isit = false;

    @Override
    public void run() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36 OPR/67.0.3575.115";
        Document doc = null;
        try {
            doc = Jsoup.connect("https://covid19.saglik.gov.tr").userAgent(userAgent).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements element = doc.select("li");
        Iterator<Element> iterator = element.iterator();
        while (iterator.hasNext()) {
            Element str = iterator.next();
            String[] split = str.text().split("I ");
            String key = split[0] + "I";
            MainActivity.strDataList.add(key);
            MainActivity.intDataList.add(split[1]);
            MainActivity.data += str.text();
            MainActivity.componentNumber++;
            iterator.remove();
        }
        MainActivity.instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView header = MainActivity.instance.findViewById(R.id.header);
                TextView value = MainActivity.instance.findViewById(R.id.value);
                String headerText = MainActivity.strDataList.get(0);
                String valueWillBeSetTo = MainActivity.intDataList.get(0);
                header.setText(headerText);
                value.setText(valueWillBeSetTo);
            }
        });


    }

}
