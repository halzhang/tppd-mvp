package com.halzhang.android.mvp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.halzhang.android.mvp.Constants;
import com.halzhang.android.mvp.app.PresenterHelper;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base MVP Dialog
 * Created by Hal on 15/4/27.
 */
public abstract class MVPDialog<PresenterType extends Presenter> extends Dialog implements Presenter.IView{

    private PresenterHelper<PresenterType> mHelper = new PresenterHelper<>();

    public PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    public MVPDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper.requestPresenter(getClass(), savedInstanceState == null ? null : savedInstanceState.getBundle(Constants.PRESENTER_STATE_KEY));
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBundle(Constants.PRESENTER_STATE_KEY, mHelper.savePresenter());
        return bundle;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHelper.attachView(this, (Activity) getContext());
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHelper.detachView();
    }



}
