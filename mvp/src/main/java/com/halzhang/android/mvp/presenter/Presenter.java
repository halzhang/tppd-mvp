package com.halzhang.android.mvp.presenter;

import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Presenter,ViewType 业务回调接口，监听业务执行结果；CallbackType 业务接口
 * Created by Hal on 15/4/27.
 */
public abstract class Presenter<ViewType extends Presenter.IView<CallbackType>, CallbackType> {

    /**
     * 业务回调接口
     */
    public interface IView<CallbackType> {
        public void setCallback(CallbackType callback);
    }

    /**
     * 创建业务接口
     */
    public abstract CallbackType createViewCallback(IView view);

    private ViewType mView;

    private CopyOnWriteArraySet<OnPresenterDestroyListener> mListeners = new CopyOnWriteArraySet<OnPresenterDestroyListener>();

    public ViewType getView() {
        return mView;
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

    /**
     * Listen Presenter destroy.
     */
    public interface OnPresenterDestroyListener {
        void onDestroy(Presenter presenter);
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

}
