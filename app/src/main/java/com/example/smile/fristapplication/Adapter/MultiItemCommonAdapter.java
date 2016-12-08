package com.example.smile.fristapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.smile.fristapplication.R;

import java.util.List;

/**
 * Created by Smile on 2016/12/8.
 */

public abstract class MultiItemCommonAdapter<T> extends CommonRecyclerViewAdapter<T> {

    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int itemType);

        int getItemViewType(int position, T t);
    }

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, datas, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDate.get(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutid = mMultiItemTypeSupport.getLayoutId(viewType);


        return MyViewHodler.GetViewHodler(mContext, layoutid, parent);
    }
}
