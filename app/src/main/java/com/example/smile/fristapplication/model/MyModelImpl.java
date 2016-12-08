package com.example.smile.fristapplication.model;


import android.text.TextUtils;
import android.widget.Toast;

import com.example.smile.fristapplication.bean.User;

/**
 * Created by Smile on 2016/12/07
 */

public class MyModelImpl implements MyModel {

    @Override
    public void Login(final String Username, final String Password, final MyModelLogListener
            myModelLogListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    if (Username.equals("cyx") && Password.equals("123456")) {
                        myModelLogListener.SuccessLogin(new User(Username, Password));
                    } else {
                        myModelLogListener.FaildLogin();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
