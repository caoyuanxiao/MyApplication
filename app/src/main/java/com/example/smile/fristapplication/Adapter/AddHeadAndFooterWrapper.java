package com.example.smile.fristapplication.Adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smile.fristapplication.LogSuccessActivity;
import com.example.smile.fristapplication.R;

/**
 * Created by Smile on 2016/12/8.
 */

public class AddHeadAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    public static final int LOADING_MORE = 0;
    public static final int LOADING_COMPLTE = 1;

    public int LoadMoreState;


    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    RecyclerView.Adapter mInnerAdapter;


    public AddHeadAndFooterWrapper(RecyclerView.Adapter innerAdapter) {
        this.mInnerAdapter = innerAdapter;
    }


    public void AddHeaderView(View HeaderView) {
        if (HeaderView != null && mHeaderViews != null) {
            mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, HeaderView);
        }
    }

    public int GetHeaderCount() {
        return mHeaderViews.size();
    }

    public void AddFooterView(View FooterView) {
        if (FooterView != null && mHeaderViews != null) {
            mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, FooterView);
        }
    }

    public boolean IsHeaderView(int position) {
        return position < mHeaderViews.size();
    }

    public boolean IsFooterView(int position) {
        return position >= (mHeaderViews.size() + GetRealCount());
    }

    public int GetRealCount() {
        return mInnerAdapter.getItemCount();
    }

    public void SetLoadingStatu(int Statu) {
        LoadMoreState = Statu;
    }

    public void RemoveFooter() {
        mFootViews.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return new MyHeaderHolderView(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            return new MyFooterHolderView(mFootViews.get(viewType));
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (IsHeaderView(position)) {
            return;
        }
        if (IsFooterView(position)) {
            MyFooterHolderView mMyFooterHolderView = (MyFooterHolderView) holder;
            if (LoadMoreState == LOADING_MORE) {
                mMyFooterHolderView.mTextView.setText("加载中。。。");
            } else if (LoadMoreState == LOADING_COMPLTE) {
                mMyFooterHolderView.mTextView.setText("正在获取数据。。");
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - mHeaderViews.size());
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mFootViews.size() + GetRealCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (IsHeaderView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (IsFooterView(position)) {
            return mFootViews.keyAt(position - GetRealCount() - mHeaderViews.size());
        }
        return mInnerAdapter.getItemViewType(position);
    }

}


class MyHeaderHolderView extends RecyclerView.ViewHolder {

    public MyHeaderHolderView(View itemView) {
        super(itemView);
    }
}

class MyFooterHolderView extends RecyclerView.ViewHolder {

    TextView mTextView;

    public MyFooterHolderView(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_loading);
    }
}
