package com.halzhang.android.mvpexample;

import android.os.Bundle;

import com.halzhang.android.mvp.annotation.RequiresPresenter;
import com.halzhang.android.mvp.app.MVPActivity;
import com.halzhang.android.mvpexample.models.LoginInfo;

@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends MVPActivity<LoginPresenter> implements LoginPresenter.ILogin {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public void setCallback(LoginPresenter.ILoginCallback callback) {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }
}
