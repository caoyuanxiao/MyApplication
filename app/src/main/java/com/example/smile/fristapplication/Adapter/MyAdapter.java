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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Smile on 2016/12/7.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View
        .OnClickListener {


    public static final int HeaderItem = 0;
    public static final int Item = 1;
    public static final int FooterItem = 2;

    List<String> mStrings;
    Context mContext;
    OnItemOnclickListener mOnItemOnclickListener = null;


    public MyAdapter(Context context) {
        mContext = context;
        InitData();

    }

    public void SetOnItemOnclickListener(OnItemOnclickListener onItemOnclickListener) {
        this.mOnItemOnclickListener = onItemOnclickListener;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            Toast.makeText(mContext, "点击了TextView"+v.getTag(), Toast.LENGTH_SHORT).show();
        } else if (mOnItemOnclickListener != null) {
            mOnItemOnclickListener.OnItemOnClick(v, (String) v.getTag());
        }
    }

    public interface OnItemOnclickListener {
        void OnItemOnClick(View view, String position);
    }

    private void InitData() {
        mStrings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mStrings.add("position" + i);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case HeaderItem:
                view = View.inflate(mContext, R.layout.recyclerview_header, null);
                view.setOnClickListener(this);
                return new HeaderItemViewHodler(view);
            case Item:
                view = View.inflate(mContext, R.layout.recyclerview_item, null);
                view.setOnClickListener(this);
                return new ItemViewHodler(view);

            case FooterItem:
                view = View.inflate(mContext, R.layout.recyclerview_header, null);
                view.setOnClickListener(this);
                return new HeaderItemViewHodler(view);

        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderItemViewHodler) {
            ((HeaderItemViewHodler) holder).mTvHeader.setOnClickListener(this);
            ((HeaderItemViewHodler) holder).mTvHeader.setTag(position);
            ((HeaderItemViewHodler) holder).itemView.setTag(mStrings.get(position));
            //((HeaderItemViewHodler) holder).mTvHeader.setText("Header:" + position);
        } else if (holder instanceof ItemViewHodler) {
            ((ItemViewHodler) holder).mTvRecyclerviewItem.setTag(position);
            ((ItemViewHodler) holder).mTvRecyclerviewItem.setOnClickListener(this);
            ((ItemViewHodler) holder).itemView.setTag(mStrings.get(position));
            ((ItemViewHodler) holder).mTvRecyclerviewItem.setText("点击了HeaderItem:" + position);

        }


    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HeaderItem;
        } else if (position == mStrings.size() - 1) {
            return FooterItem;
        } else {
            return Item;
        }

    }

    class HeaderItemViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_header)
        TextView mTvHeader;

        public HeaderItemViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recyclerview_item)
        ImageView mIvRecyclerviewItem;
        @BindView(R.id.tv_recyclerview_item)
        TextView mTvRecyclerviewItem;

        public ItemViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
