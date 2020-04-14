package com.example.covidtracker.utils;

import android.util.Log;
import android.widget.TextView;

import com.example.covidtracker.MainActivity;
import com.example.covidtracker.R;


public class ButtonActions {


    public static void toRightButton(String direction) {
        if (direction.equalsIgnoreCase("right"))
            if (MainActivity.pageNumber == 15)
                MainActivity.pageNumber = 0;
            else
                MainActivity.pageNumber++;
        else if (direction.equalsIgnoreCase("left"))
            if (MainActivity.pageNumber == 0)
                MainActivity.pageNumber = 15;
            else
                MainActivity.pageNumber--;


        String value = MainActivity.strDataList.get(MainActivity.pageNumber);
        TextView header = MainActivity.instance.findViewById(R.id.header);
        TextView valueToBeSet = MainActivity.instance.findViewById(R.id.value);
        header.setText(value);
        String key = MainActivity.intDataList.get(MainActivity.pageNumber);
        valueToBeSet.setText(key + "");
    }


}
