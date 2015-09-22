package com.halzhang.android.mvp.app;

import android.app.Activity;
import android.os.Bundle;

import com.halzhang.android.mvp.annotation.RequiresPresenter;
import com.halzhang.android.mvp.manager.PresenterManager;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * 封装{@link com.halzhang.android.mvp.manager.PresenterManager}接口
 * <p/>
 * Created by Hal on 15/4/29.
 */
public class PresenterHelper<PresenterType extends Presenter> {

    private PresenterType mPresenter;

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

    public void attachView(Presenter.IView view) {
        if (mPresenter == null) {
            requestPresenter(view.getClass(), null);
        }
        if (mPresenter != null) {
            mPresenter.attachView(view);
        }
    }

    public void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private Class<PresenterType> findPresenterClass(Class<?> viewClass) {
        RequiresPresenter annotation = viewClass.getAnnotation(RequiresPresenter.class);
        return annotation == null ? null : (Class<PresenterType>) annotation.value();
    }
}
