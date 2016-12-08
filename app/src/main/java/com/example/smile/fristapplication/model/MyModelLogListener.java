package com.example.smile.fristapplication.model;

import com.example.smile.fristapplication.bean.User;

/**
 * Created by Smile on 2016/12/7.
 */

public interface MyModelLogListener {

     void SuccessLogin(User user);
     void FaildLogin();

}
