package com.halzhang.android.mvp.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.halzhang.android.mvp.Constants;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base MVP Fragment
 * Created by Hal on 15/4/27.
 */
public abstract class MVPFragment<PresenterType extends Presenter> extends Fragment {

    private PresenterHelper<PresenterType> mHelper = new PresenterHelper<>();

    protected PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper.requestPresenter(getClass(), savedInstanceState == null ? null : savedInstanceState.getBundle(Constants.PRESENTER_STATE_KEY));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this instanceof Presenter.IView) {
            mHelper.attachView((Presenter.IView) this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mHelper.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHelper.destroyPresenter();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constants.PRESENTER_STATE_KEY, mHelper.savePresenter());
    }
}
