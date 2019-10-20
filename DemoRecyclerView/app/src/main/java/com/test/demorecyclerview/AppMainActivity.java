package com.test.demorecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.demorecyclerview.first.FirstActivity;
import com.test.demorecyclerview.second.SecondActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考链接：
 * 1. https://www.jianshu.com/p/c1090314efbf
 * 2. https://blog.csdn.net/fitaotao/article/details/82256840
 * 3. https://blog.csdn.net/Life_s/article/details/81298823
 */
public class AppMainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> datas = new ArrayList<>();

    private final Class[] activities = {
            FirstActivity.class,
            SecondActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        listView = findViewById(R.id.mainAct_listView);
        datas.add("RecyclerView实现复杂布局1");
        datas.add("RecyclerView实现复杂布局2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(AppMainActivity.this, activities[position]));
            }
        });
    }
}
