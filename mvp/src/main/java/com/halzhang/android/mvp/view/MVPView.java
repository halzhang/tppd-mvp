package com.halzhang.android.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.halzhang.android.mvp.Constants;
import com.halzhang.android.mvp.app.PresenterHelper;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base MVP {@link View}
 * Created by Hal on 15/4/27.
 */
public abstract class MVPView<PresenterType extends Presenter> extends View implements Presenter.IView {

    private PresenterHelper<PresenterType> mHelper = new PresenterHelper<>();

    public PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    public MVPView(Context context) {
        this(context, null);
    }

    public MVPView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MVPView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBundle(Constants.PRESENTER_STATE_KEY, mHelper.savePresenter());
        bundle.putParcelable(Constants.PARENT_STATE_KEY, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        super.onRestoreInstanceState(bundle.getParcelable(Constants.PARENT_STATE_KEY));
        mHelper.requestPresenter(getClass(), bundle.getBundle(Constants.PRESENTER_STATE_KEY));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            mHelper.attachView(this);
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHelper.detachView();
        mHelper.destroyPresenter();
    }
}
