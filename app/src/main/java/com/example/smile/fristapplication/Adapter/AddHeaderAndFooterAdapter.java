package com.example.smile.fristapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smile.fristapplication.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Smile on 2016/12/8.
 */

public class AddHeaderAndFooterAdapter extends RecyclerView.Adapter<AddHeaderAndFooterAdapter
        .MyViewHodler> implements View.OnClickListener {

    Context mContext;
    List<String> mStringList;

    //添加Header和Footer


    public AddHeaderAndFooterAdapter(Context context, List<String> StringList) {
        mContext = context;
        mStringList = StringList;


    }


    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = View.inflate(mContext, R.layout.recyclerview_item, null);
        view.setOnClickListener(this);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        holder.mTvRecyclerviewItem.setText(mStringList.get(position));
        holder.mTvRecyclerviewItem.setOnClickListener(this);
        holder.mTvRecyclerviewItem.setTag(position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    @Override
    public void onClick(View v) {
       if (mAddOnClickListener != null) {
            mAddOnClickListener.OnItemClick(v, (Integer) v.getTag());
        }


    }

    AddOnClickListener mAddOnClickListener;

    public interface AddOnClickListener {
        void OnItemClick(View view, int msg);

    }

    public void setAddOnClickListener(AddOnClickListener addOnClickListener) {
        this.mAddOnClickListener = addOnClickListener;
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recyclerview_item)
        ImageView mIvRecyclerviewItem;
        @BindView(R.id.tv_recyclerview_item)
        TextView mTvRecyclerviewItem;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


