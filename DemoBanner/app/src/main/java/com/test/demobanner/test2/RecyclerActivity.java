package com.test.demobanner.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.test.demobanner.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private View view1, view2, view3, view4, view5;
    private View[] views = new View[5];
    private List<Item> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        initData();

        initView();
    }

    private void initData() {

        datas.add(new Item(R.drawable.image_a, "a"));
        datas.add(new Item(R.drawable.image_b, "b"));
        datas.add(new Item(R.drawable.image_c, "c"));
        datas.add(new Item(R.drawable.image_d, "d"));
        datas.add(new Item(R.drawable.image_e, "e"));
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        view1 = findViewById(R.id.view_01);
        view2 = findViewById(R.id.view_02);
        view3 = findViewById(R.id.view_03);
        view4 = findViewById(R.id.view_04);
        view5 = findViewById(R.id.view_05);

        views[0] = view1;
        views[1] = view2;
        views[2] = view3;
        views[3] = view4;
        views[4] = view5;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        System.out.println("width-display :" + dm.widthPixels);
        System.out.println("heigth-display :" + dm.heightPixels);
        RecyclerAdapter adapter = new RecyclerAdapter(this, datas, dm.widthPixels - 10, dm.heightPixels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }
}
