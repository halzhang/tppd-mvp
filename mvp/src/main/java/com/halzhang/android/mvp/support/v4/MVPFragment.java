package com.halzhang.android.mvp.support.v4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.halzhang.android.mvp.Constants;
import com.halzhang.android.mvp.app.PresenterHelper;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base mvp {@link Fragment}
 * Created by Hal on 15/5/10.
 */
public class MVPFragment<PresenterType extends Presenter> extends Fragment {

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
        mHelper.attachView(this, getActivity());
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
