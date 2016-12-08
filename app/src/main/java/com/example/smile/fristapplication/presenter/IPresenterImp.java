package com.example.smile.fristapplication.presenter;

import com.example.smile.fristapplication.model.MyModelLogListener;
import com.example.smile.fristapplication.view.MyView;

/**
 * Created by Smile on 2016/12/7.
 */

public class IPresenterImp implements IPresentLogin {

    MyView mMyView;


    IPresenterImp (MyView myView){
       mMyView=myView;
    }
    @Override
    public void Login() {

    }

    @Override
    public void Clearn() {

    }
}
