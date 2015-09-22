package com.halzhang.android.mvp.presenter;

/**
 * Created by Hal on 15/4/27.
 */
public abstract class RxPresenter<ViewType extends Presenter.IView<CallbackType>, CallbackType> extends Presenter<ViewType,CallbackType> {
}
