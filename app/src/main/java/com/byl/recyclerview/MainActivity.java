package com.byl.recyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byl.recyclerview.view.PullBaseView;
import com.byl.recyclerview.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnFooterRefreshListener {

    PullRecyclerView recyclerView;
    List<String> mDatas;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        initData();
        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        recyclerView.setOnFooterRefreshListener(this);//设置下拉监听
//        recyclerView.setCanScrollAtRereshing(true);//设置正在刷新时是否可以滑动，默认不可滑动
//        recyclerView.setCanPullDown(false);//设置是否可下拉
//        recyclerView.setCanPullUp(false);//设置是否可上拉
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            mDatas.add("TEXT" + i);
        }
    }

    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.add("TEXT更多");
                myAdapter.notifyDataSetChanged();
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.add(0, "TEXT新增");
                myAdapter.notifyDataSetChanged();
                recyclerView.onHeaderRefreshComplete();
            }
        }, 3000);
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder;
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_list, parent, false);
            holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }
    }
}
