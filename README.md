# PullRecyclerView
支持下拉刷新上拉加载的RecyclerView（带头部动画）

![这里写图片描述](http://img.blog.csdn.net/20160526095448143)

**使用方法：**

布局：

        <com.byl.recyclerview.view.PullRecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"/>

代码：

        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        recyclerView.setOnFooterRefreshListener(this);//设置上拉监听
        recyclerView.setCanScrollAtRereshing(true);//设置正在刷新时是否可以滑动，默认不可滑动
        recyclerView.setCanPullDown(false);//设置是否可下拉
        recyclerView.setCanPullUp(false);//设置是否可上拉
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

**注意：**

在数据量较少时（不满一屏），可根据当前item数量进行判断，来禁止上拉加载，
如：if (itemcounts<20) recyclerView.setCanPullUp(false);


CSDN博客：http://blog.csdn.net/baiyuliang2013
