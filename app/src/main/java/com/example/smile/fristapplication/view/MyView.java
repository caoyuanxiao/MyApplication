package com.example.smile.fristapplication.view;

import com.example.smile.fristapplication.bean.User;

/**
* Created by Smile on 2016/12/07
*/

public interface MyView{

    String GetUsername();
    String GetPassword();

    void ToMainActivity(User user);
    void ShowFaildMsg();
    void ShowParamsError();

    void ShowLoading();
    void HideLoading();

    void ClearUsername();
    void ClearPassword();


}
