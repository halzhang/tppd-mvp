package com.halzhang.android.mvpexample;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.halzhang.android.mvp.annotation.RequiresPresenter;
import com.halzhang.android.mvp.support.v7.MVPAppCompatActivity;
import com.halzhang.android.mvpexample.models.LoginInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends MVPAppCompatActivity<LoginPresenter> implements LoginPresenter.ILoginView {

    private LoginPresenter.ILoginCallback mLoginCallback;

    @Bind(R.id.username)
    EditText mUsernameET;
    @Bind(R.id.password)
    EditText mPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginBtnClicked(View view) {
        if (mLoginCallback != null) {
            String username = mUsernameET.getText().toString();
            String password = mPasswordET.getText().toString();
            // TODO: 2015/10/15 check username & password
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.mPassword = password;
            loginInfo.mUsername = username;
            mLoginCallback.login(loginInfo);
        }
    }


    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginFailure() {
        Toast.makeText(this, "Login Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCallback(LoginPresenter.ILoginCallback callback) {
        mLoginCallback = callback;
    }
}
