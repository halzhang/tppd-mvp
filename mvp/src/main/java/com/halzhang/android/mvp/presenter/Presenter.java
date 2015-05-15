package com.halzhang.android.mvp.presenter;

import android.os.Bundle;

import com.halzhang.android.mvp.view.IView;

import java.util.Vector;

/**
 * Presenter
 * Created by Hal on 15/4/27.
 */
public class Presenter<ViewType> {

    /**
     * Listen Presenter destroy.
     */
    public interface OnPresenterDestroyListener {
        void onDestroy(Presenter presenter);
    }

    private ViewType mView;

    private Vector<OnPresenterDestroyListener> mListeners = new Vector<OnPresenterDestroyListener>(0);

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
