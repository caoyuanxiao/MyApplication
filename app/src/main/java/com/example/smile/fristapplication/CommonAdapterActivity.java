package com.example.smile.fristapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smile.fristapplication.Adapter.CommonRecyclerViewAdapter;
import com.example.smile.fristapplication.Adapter.MultiItemCommonAdapter;
import com.example.smile.fristapplication.Adapter.MyViewHodler;
import com.example.smile.fristapplication.bean.ChatMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Smile on 2016/12/8.
 */

public class CommonAdapterActivity extends AppCompatActivity {
    @BindView(R.id.common_recyclerview)
    RecyclerView mCommonRecyclerview;

    List<String> mStrings;
    List<ChatMessage> mChatMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        ButterKnife.bind(this);
        InitDate();
        InitChatData();
        mCommonRecyclerview.setLayoutManager(new LinearLayoutManager(this));
      /*  CommonRecyclerViewAdapter mCommonRecyclerViewAdapter = new
      CommonRecyclerViewAdapter<String>
                (this, mStrings, R.layout.commentrecyclerview_item) {
            @Override
            public void Convert(MyViewHodler myViewHodler, final String s) {
                myViewHodler.SetTextView(s, R.id.tv_commentadapter);
                myViewHodler.setOnClickListener(R.id.tv_commentadapter, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CommonAdapterActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };*/

        MultiItemCommonAdapter.MultiItemTypeSupport multiItemTypeSupport = new
                MultiItemCommonAdapter.MultiItemTypeSupport<ChatMessage>() {


                    @Override
                    public int getLayoutId(int itemType) {

                        if (itemType == 1) {
                            return R.layout.left_chat;
                        } else if (itemType == 2) {
                            return R.layout.right_chat;
                        }
                        return 0;
                    }

                    @Override
                    public int getItemViewType(int position, ChatMessage chatMessage) {

                        return chatMessage.getType();
                    }

                };
        MultiItemCommonAdapter multiItemCommonAdapter = new MultiItemCommonAdapter<ChatMessage>
                (this,
                        mChatMessages, multiItemTypeSupport) {
            @Override
            public void Convert(MyViewHodler myViewHodler, ChatMessage chatMessage) {
                myViewHodler.SetTextView(chatMessage.getName(), R.id.tv_chat);

            }
        };

        mCommonRecyclerview.setAdapter(multiItemCommonAdapter);


    }

    private void InitChatData() {
        mChatMessages = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            ChatMessage chatMessage;
            if (i % 2 == 0) {
                chatMessage = new ChatMessage(1, "yuanxiao" + i);
            } else {
                chatMessage = new ChatMessage(2, "yuanxiao:" + i);
            }
            mChatMessages.add(chatMessage);
        }
    }

    private void InitDate() {
        mStrings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mStrings.add("加油 加油:" + i);
        }
    }
}
