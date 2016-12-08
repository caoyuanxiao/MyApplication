package com.example.smile.fristapplication.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.example.smile.fristapplication.bean.User;
import com.example.smile.fristapplication.model.MyModelImpl;
import com.example.smile.fristapplication.model.MyModelLogListener;
import com.example.smile.fristapplication.view.MyView;

/**
 * Created by Smile on 2016/12/7.
 */

public class MyLogInPresent {

    MyView mMyView;
    MyModelImpl mMyModel;
    Handler mHandler = new Handler();

    public MyLogInPresent(MyView myView) {
        this.mMyView = myView;
        this.mMyModel = new MyModelImpl();


    }

    public void CheckParams() {

        if (!TextUtils.isEmpty(mMyView.GetUsername()) && !TextUtils.isEmpty(mMyView.GetPassword()
        )) {
            Login();
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mMyView.ShowParamsError();
                }
            });
        }
    }

    public void Login() {

        mMyView.ShowLoading();
        mMyModel.Login(mMyView.GetUsername(), mMyView.GetPassword(), new MyModelLogListener() {
            @Override
            public void SuccessLogin(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMyView.HideLoading();
                        mMyView.ToMainActivity(user);
                    }
                });
            }

            @Override
            public void FaildLogin() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMyView.HideLoading();
                        mMyView.ShowFaildMsg();
                        Clearn();
                    }
                });
            }
        });
    }

    public void Clearn() {
        mMyView.ClearPassword();
        mMyView.ClearUsername();
    }
}
