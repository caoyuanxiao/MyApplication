package com.example.smile.fristapplication.CustemScrollView;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Smile on 2016/12/8.
 */

public abstract class EndlessRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {


    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;



    public EndlessRecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        /*scrollState：即滑动的状态。分为三种 0，1，2

                =0 表示停止滑动的状态 SCROLL_STATE_IDLE

                =1表示正在滚动，用户手指在屏幕上 SCROLL_STATE_TOUCH_SCROLL

                =2表示正在滑动。用户手指已经离开屏幕 SCROLL_STATE_FLING
        */

        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        System.out.println("visibleItemCount:" + visibleItemCount + "totalItemCount:" +
                totalItemCount + "firstVisibleItem:" + firstVisibleItem);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            //滑到了最底部  现在开始加载数据
            onLoadMore(currentPage);
            currentPage++;
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);


}
