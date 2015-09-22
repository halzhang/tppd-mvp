package com.halzhang.android.mvp.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.halzhang.android.mvp.Constants;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base MVP activity.
 * Created by Hal on 15/4/27.
 */
public abstract class MVPActivity<PresenterType extends Presenter> extends Activity {

    private PresenterHelper<PresenterType> mHelper = new PresenterHelper<>();

    protected PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper.requestPresenter(getClass(), savedInstanceState == null ? null : savedInstanceState.getBundle(Constants.PRESENTER_STATE_KEY));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constants.PRESENTER_STATE_KEY, mHelper.savePresenter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this instanceof Presenter.IView) {
            mHelper.attachView((Presenter.IView) this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHelper.detachView();
    }

    @Override
    protected void onDestroy() {
        mHelper.destroyPresenter();
        super.onDestroy();
    }
}
