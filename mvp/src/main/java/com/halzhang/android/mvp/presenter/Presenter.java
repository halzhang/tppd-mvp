package com.halzhang.android.mvp.presenter;

import android.os.Bundle;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Presenter
 * Created by Hal on 15/4/27.
 */
public class Presenter<ViewType extends Presenter.IView<CallbackType>, CallbackType> {

    /**
     * 视图业务接口
     *
     * @param <CallbackType> 视图回调接口，定义了从 Presenter 调用 view 的相关接口
     */
    public interface IView<CallbackType> {
        public void setCallback(CallbackType callback);
    }

    /**
     * 创建视图回调接口,对于不同业务，回调是不同的
     *
     * @param view 视图业务接口
     * @return 视图回调接口
     */
    public CallbackType createViewCallback(IView view) {
        return onCreateViewCallback(view);
    }

    /**
     * 子类需要重写
     *
     * @param view
     * @return
     */
    protected CallbackType onCreateViewCallback(IView view) {
        return null;
    }

    /**
     * Listen Presenter destroy.
     */
    public interface OnPresenterDestroyListener {
        void onDestroy(Presenter presenter);
    }

    private ViewType mView;

    private CopyOnWriteArraySet<OnPresenterDestroyListener> mListeners = new CopyOnWriteArraySet<OnPresenterDestroyListener>();

    public ViewType getView() {
        return mView;
    }

    /**
     * Add {@link Presenter} destroy listener
     *
     * @param listener {@link com.halzhang.android.mvp.presenter.Presenter.OnPresenterDestroyListener}
     * @return {@code true} add success
     */
    public boolean addDestroyListener(OnPresenterDestroyListener listener) {
        return mListeners.add(listener);
    }

    /**
     * Remove {@link Presenter} destroy listener
     *
     * @param listener {@link com.halzhang.android.mvp.presenter.Presenter.OnPresenterDestroyListener}
     * @return
     */
    private boolean removeDestroyListener(OnPresenterDestroyListener listener) {
        return mListeners.remove(listener);
    }

    public void create(Bundle saveState) {
        onCreate(saveState);
    }

    public void save(Bundle saveState) {
        onSave(saveState);
    }

    public void attachView(ViewType view) {
        mView = view;
        onAttachView(view);
        mView.setCallback(createViewCallback(view));
    }

    public void detachView() {
        onDetachView();
        mView.setCallback(null);
        mView = null;
    }

    public void destroy() {
        onDestroy();
    }

    protected void onDestroy() {
    }

    protected void onSave(Bundle saveState) {
    }

    protected void onCreate(Bundle saveState) {
    }

    protected void onAttachView(ViewType view) {
    }

    protected void onDetachView() {
    }

}
