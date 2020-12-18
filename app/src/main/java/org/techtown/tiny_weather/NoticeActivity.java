package org.techtown.tiny_weather;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> listArray;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        list = (ListView)findViewById(R.id.list);

        listArray = new ArrayList<String>();

        listArray.add("1 . 더미데이터");
        listArray.add("2 . 더미데이터");
        listArray.add("3 . 더미데이터");
        listArray.add("4 . 더미데이터");
        listArray.add("5 . 더미데이터");
        listArray.add("6 . 더미데이터");
        listArray.add("7 . 더미데이터");
        listArray.add("8 . 더미데이터");
        listArray.add("9 . 더미데이터");
        listArray.add("10 . 더미데이터");
        listArray.add("11 . 더미데이터");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);
        list.setAdapter(adapter);

    }
}
