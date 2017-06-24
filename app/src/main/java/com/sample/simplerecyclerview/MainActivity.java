package com.sample.simplerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rv;
    private SimpleRecyclerViewAdapter adapter;
    private List<Bean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new StickyDecoration(new GroupListener() {
            @Override
            public String getUserName(int position) {
                Log.d(TAG, "getUserName position:" + position);
                if (position >= 0){
                    return list.get(position).getUsername();
                }
                return null;
            }
        }));
        adapter = new SimpleRecyclerViewAdapter(list);
        rv.setAdapter(adapter);
        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        //用Callback构造ItemTouchHelper
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        helper.attachToRecyclerView(rv);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
        list.add(new Bean("狄仁杰", "下午1:22", "元芳，你怎么看？"));
        list.add(new Bean("李元芳", "下午1:22", "我不看"));
    }
}
