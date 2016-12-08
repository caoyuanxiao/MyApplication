package com.example.smile.fristapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MyViewHodler extends RecyclerView.ViewHolder {

    private Context mContext;
    private SparseArray<View> mSparseArray = null;
    private View mConvertView;


    public  MyViewHodler(View itemView, Context context, ViewGroup viewGroup) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mSparseArray = new SparseArray<>();
    }

    public static MyViewHodler GetViewHodler(Context context, int layoutid, ViewGroup parent) {
        View view = View.inflate(context, layoutid, null);
        return new MyViewHodler(view, context, parent);
    }


    public <T extends View> T getView(int viewId) {
        View view = mSparseArray.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public void SetTextView(String s, int viewid) {
        TextView tv = getView(viewid);
        tv.setText(s);
    }

    public void setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);

    }

    public void setOnClickListener(int viewId,
                                   View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);

    }
}
