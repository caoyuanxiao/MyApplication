package com.example.smile.fristapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Smile on 2016/12/8.
 */

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> {

    Context mContext;
    List<T> mDate = null;
    int mLayoutid;

    public CommonRecyclerViewAdapter(Context context, List<T> dates, int layoutid) {
        mContext = context;
        mDate = dates;
        mLayoutid = layoutid;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, mLayoutid, null);
        return new MyViewHodler(view, mContext, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Convert((MyViewHodler) holder, mDate.get(position));
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    public abstract void Convert(MyViewHodler myViewHodler, T t);
}

