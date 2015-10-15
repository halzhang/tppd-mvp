package com.halzhang.android.mvpexample;

import android.os.AsyncTask;

import com.halzhang.android.mvp.presenter.Presenter;
import com.halzhang.android.mvpexample.models.LoginInfo;

/**
 * 登录
 * Created by zhanghanguo@yy.com on 2015/9/23.
 */
public class LoginPresenter extends Presenter<LoginPresenter.ILoginView, LoginPresenter.ILoginCallback> {

    private LoginTask mLoginTask;

    @Override
    public ILoginCallback createViewCallback(IView view) {
        return new ILoginCallback() {
            @Override
            public void login(LoginInfo loginInfo) {
                if (mLoginTask == null) {
                    mLoginTask = new LoginTask();
                    mLoginTask.execute(loginInfo);
                }
            }
        };
    }

    @Override
    protected void onAttachView(ILoginView view) {
        super.onAttachView(view);
    }

    private class LoginTask extends AsyncTask<LoginInfo, Void, Boolean> {

        @Override
        protected Boolean doInBackground(LoginInfo... params) {
            LoginInfo loginInfo = params[0];
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "admin".equals(loginInfo.mUsername) && "admin".equals(loginInfo.mPassword);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            ILoginView login = getView();
            if (login == null) {
                return;
            }
            if (aBoolean) {
                login.onLoginSuccess();
            } else {
                login.onLoginFailure();
            }
            mLoginTask = null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mLoginTask = null;
        }
    }

    /**
     * 业务回调接口
     */
    public interface ILoginView extends Presenter.IView<ILoginCallback> {
        public void onLoginSuccess();

        public void onLoginFailure();
    }

    /**
     * 业务接口
     */
    public interface ILoginCallback {
        public void login(LoginInfo loginInfo);
    }
}
