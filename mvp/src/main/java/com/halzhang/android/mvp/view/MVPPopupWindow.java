package com.halzhang.android.mvp.view;

import android.app.Activity;
import android.view.View;
import android.widget.PopupWindow;

import com.halzhang.android.mvp.app.PresenterHelper;
import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Base MVP {@link PopupWindow}
 * Created by Hal on 15/4/27.
 */
public abstract class MVPPopupWindow<PresenterType extends Presenter> extends PopupWindow implements Presenter.IView {

    private PresenterHelper<PresenterType> mHelper = new PresenterHelper<>();

    public PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    private OnDismissListener mOuterDismissListener;

    private Activity mActivity;

    public MVPPopupWindow(Activity activity) {
        mActivity = activity;
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mOuterDismissListener != null) {
                    mOuterDismissListener.onDismiss();
                }
                mHelper.detachView();
                mHelper.destroyPresenter();
            }
        });
        mHelper.requestPresenter(getClass(), null);
    }

    protected void onShow() {
        mHelper.attachView(this);
    }

    @Override
    public void showAsDropDown(View anchor) {
        onShow();
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        onShow();
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        onShow();
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        onShow();
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOuterDismissListener = onDismissListener;
    }
}
