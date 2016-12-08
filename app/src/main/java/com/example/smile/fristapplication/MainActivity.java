package com.example.smile.fristapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.smile.fristapplication.bean.User;
import com.example.smile.fristapplication.presenter.MyLogInPresent;
import com.example.smile.fristapplication.view.MyView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.progressbar_loading)
    ProgressBar mProgressbarLoading;
    @BindView(R.id.edt_username)
    EditText mEdtUsername;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_clear)
    Button mBtnClear;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

    MyLogInPresent mMyLogInPresent = new MyLogInPresent(this);
    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initWindow();

    }

    @OnClick(R.id.btn_login)
    public void Login() {
        mMyLogInPresent.CheckParams();
    }

    @OnClick(R.id.btn_clear)
    public void Clearn() {
        mMyLogInPresent.Clearn();
    }

    @Override
    public String GetUsername() {
        return mEdtUsername.getText().toString().trim();
    }

    @Override
    public String GetPassword() {
        return mEdtPassword.getText().toString().trim();
    }


    @Override
    public void ToMainActivity(User user) {
        Intent intent = new Intent(this, LogSuccessActivity.class);
        intent.putExtra("user", (Serializable) user);
        startActivity(intent);
        finish();
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            mTintManager = new SystemBarTintManager(this);

            mTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorAccent));
            mTintManager.setStatusBarTintEnabled(true);
        }
    }

    @Override
    public void ShowFaildMsg() {
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowParamsError() {
        Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowLoading() {
        mProgressbarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideLoading() {
        mProgressbarLoading.setVisibility(View.GONE);
    }

    @Override
    public void ClearUsername() {
        mEdtUsername.setText("");
    }

    @Override
    public void ClearPassword() {
        mEdtPassword.setText("");
    }
}
