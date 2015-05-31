package com.halzhang.android.mvp.presenter;

import android.os.Bundle;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Presenter
 * Created by Hal on 15/4/27.
 */
public class Presenter<ViewType extends Presenter.IView<CallbackType>, CallbackType> {

    public interface IView<CallbackType> {
        public void setCallback(CallbackType callback);
    }

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

    public boolean addDestroyListener(OnPresenterDestroyListener listener) {
        return mListeners.add(listener);
    }

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
    }

    public void detachView() {
        onDetachView();
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
