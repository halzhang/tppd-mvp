package com.halzhang.android.mvp.app;

import android.app.Activity;
import android.os.Bundle;

import com.halzhang.android.mvp.annotation.RequiresPresenter;
import com.halzhang.android.mvp.manager.PresenterManager;
import com.halzhang.android.mvp.presenter.Presenter;
import com.halzhang.android.mvp.view.IView;

/**
 * 封装{@link com.halzhang.android.mvp.manager.PresenterManager}接口
 * <p/>
 * Created by Hal on 15/4/29.
 */
public class PresenterHelper<PresenterType extends Presenter> {

    private PresenterType mPresenter;
    private Activity mActivity;

    public PresenterType getPresenter() {
        return mPresenter;
    }

    public void destroyPresenter() {
        if (mPresenter != null) {
            PresenterManager.getInstance().destroy(mPresenter);
            mPresenter = null;
        }
    }

    public void requestPresenter(Class viewClass, Bundle presenterState) {
        if (mPresenter == null) {
            Class<PresenterType> presenterClass = findPresenterClass(viewClass);
            if (presenterClass != null) {
                mPresenter = PresenterManager.getInstance().provide(presenterClass, presenterState);
            }
        }
    }

    public Bundle savePresenter() {
        return mPresenter == null ? null : PresenterManager.getInstance().save(mPresenter);
    }

    public void attachView(Object view, Activity activity) {
        if (mPresenter == null) {
            requestPresenter(view.getClass(), null);
        }
        if (mPresenter != null) {
            mPresenter.attachView(view);
        }
        mActivity = activity;
    }

    public void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mActivity.isFinishing()) {
            destroyPresenter();
        }
        mActivity = null;
    }

    private Class<PresenterType> findPresenterClass(Class<?> viewClass) {
        RequiresPresenter annotation = viewClass.getAnnotation(RequiresPresenter.class);
        return annotation == null ? null : (Class<PresenterType>) annotation.value();
    }
}
