package com.halzhang.android.mvpexample;

import com.halzhang.android.mvp.presenter.Presenter;
import com.halzhang.android.mvpexample.models.LoginInfo;

/**
 * 登录
 * Created by zhanghanguo@yy.com on 2015/9/23.
 */
public class LoginPresenter extends Presenter<LoginPresenter.ILogin, LoginPresenter.ILoginCallback> {

    @Override
    public ILoginCallback createViewCallback(IView view) {
        return new ILoginCallback() {
            @Override
            public void login(LoginInfo loginInfo) {
                // TODO: 2015/9/23 登录
            }
        };
    }

    @Override
    protected void onAttachView(ILogin view) {
        super.onAttachView(view);
    }

    /**
     * 业务回调接口
     */
    public interface ILogin extends Presenter.IView<ILoginCallback> {
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
