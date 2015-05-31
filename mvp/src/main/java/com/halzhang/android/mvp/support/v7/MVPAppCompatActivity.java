package com.halzhang.android.mvp.support.v7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.halzhang.android.mvp.Constants;
import com.halzhang.android.mvp.app.PresenterHelper;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base MVP {@link AppCompatActivity}
 * Created by Hal on 15/5/10.
 */
public abstract class MVPAppCompatActivity<PresenterType extends Presenter> extends AppCompatActivity implements Presenter.IView {


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
        mHelper.attachView(this, this);
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
