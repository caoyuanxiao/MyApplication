package com.example.smile.fristapplication;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smile.fristapplication.Adapter.AddHeadAndFooterWrapper;
import com.example.smile.fristapplication.Adapter.AddHeaderAndFooterAdapter;
import com.example.smile.fristapplication.Adapter.MyAdapter;
import com.example.smile.fristapplication.CustemScrollView.EndlessRecyclerOnScrollListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Smile on 2016/12/7.
 */

public class LogSuccessActivity extends AppCompatActivity implements SwipeRefreshLayout
        .OnRefreshListener {
    @BindView(R.id.RecyclerViw)
    RecyclerView mRecyclerViw;
    @BindView(R.id.RecyclerViw_refresh)
    SwipeRefreshLayout mRecyclerViwRefresh;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    boolean AllAddFooter = true;

    private SystemBarTintManager mTintManager;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            System.out.println("加载数据");
            InitDate(12);
            mAddHeadAndFooterWrapper.SetLoadingStatu(AddHeadAndFooterWrapper.LOADING_COMPLTE);
            mAddHeadAndFooterWrapper.RemoveFooter();
            AllAddFooter = true;

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);
        ButterKnife.bind(this);
        initWindow();
        InitReCyClerView();
        // InitRefresh();
    }

    @OnClick(R.id.btn_add)
    public void setBtnAdd() {
        //addHeaderAndFooterAdapter.notifyItemChanged(3);
        mAddHeadAndFooterWrapper.notifyItemChanged(3);
    }

    private void InitRefresh() {
        mRecyclerViwRefresh.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);

        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mRecyclerViwRefresh.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mRecyclerViwRefresh.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mRecyclerViwRefresh.setSize(SwipeRefreshLayout.LARGE);
        //设置下拉刷新的监听
        mRecyclerViwRefresh.setOnRefreshListener(this);
    }


    //MyAdapter myAdapter;
    AddHeaderAndFooterAdapter addHeaderAndFooterAdapter;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager mGridLayoutManager;
    AddHeadAndFooterWrapper mAddHeadAndFooterWrapper;

    private void InitReCyClerView() {
        InitDate(10);
        linearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerViw.setLayoutManager(linearLayoutManager);
        //  myAdapter = new MyAdapter(this);
        addHeaderAndFooterAdapter = new AddHeaderAndFooterAdapter
                (this, mStringList);
        mAddHeadAndFooterWrapper = new AddHeadAndFooterWrapper(addHeaderAndFooterAdapter);
        final TextView textView = new TextView(this);
        textView.setText("head1");
        mAddHeadAndFooterWrapper.AddHeaderView(textView);
        mRecyclerViw.setAdapter(mAddHeadAndFooterWrapper);

        addHeaderAndFooterAdapter.setAddOnClickListener(new AddHeaderAndFooterAdapter
                .AddOnClickListener() {

            @Override
            public void OnItemClick(View view, int msg) {
                mStringList.set(msg, "哈哈哈");
                mAddHeadAndFooterWrapper.notifyItemChanged(msg + mAddHeadAndFooterWrapper
                        .GetHeaderCount(),"nihao");
                Toast.makeText(LogSuccessActivity.this, "点击了:" + msg, Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerViw.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (AllAddFooter) {
                    AllAddFooter = false;
                    View LoadingView = View.inflate(LogSuccessActivity.this, R.layout
                            .recyclerview_loadmore, null);
                    mAddHeadAndFooterWrapper.AddFooterView(LoadingView);
                    mAddHeadAndFooterWrapper.notifyDataSetChanged();
                    mHandler.sendEmptyMessageDelayed(1, 2000);
                }

            }
        });
       /* mRecyclerViw.setAdapter(addHeaderAndFooterAdapter);
        mRecyclerViw.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {

                System.out.println("数据为:" + mStringList.size());
                mHandler.sendEmptyMessageDelayed(2, 2000);
                //addHeaderAndFooterAdapter.notifyDataSetChanged();
            }
        });*/

    }


    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            mTintManager = new SystemBarTintManager(this);

            mTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorAccent));
            mTintManager.setStatusBarTintEnabled(true);
        }
    }


    @Override
    public void onRefresh() {

        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    List<String> mStringList;

    private void InitDate(int size) {
        if (mStringList != null && mStringList.size() > 0) {

            for (int i = 0; i < size; i++) {
                mStringList.add("yuanxiao" + i);
            }
        } else {
            mStringList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                mStringList.add("yuanxiao" + i);
            }
        }

    }
}
