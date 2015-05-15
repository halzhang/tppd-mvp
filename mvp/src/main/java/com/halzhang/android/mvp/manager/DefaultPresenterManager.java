package com.halzhang.android.mvp.manager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Printer;

import com.halzhang.android.mvp.presenter.Presenter;

import java.util.HashMap;

/**
 * Default implements of {@link com.halzhang.android.mvp.manager.PresenterManager}
 * Created by Hal on 15/4/28.
 */
public class DefaultPresenterManager extends PresenterManager {

    private static final String PRESENTER_ID_KEY = "id";
    private static final String PRESENTER_STATE_KEY = "state";
    private HashMap<String, Presenter> idToPresenter = new HashMap<>(0);
    private HashMap<Presenter, String> presenterToId = new HashMap<>(0);
    private int mNextId;

    @Override
    public <T extends Presenter> T provide(Class<T> presenterClass, Bundle saveState) {
        String id = providePresenterId(presenterClass, saveState);
        if (idToPresenter.containsKey(id)) {
            return (T) idToPresenter.get(id);
        }
        Presenter presenter = instantiatePresenter(presenterClass, id);
        presenter.create(saveState == null ? null : saveState.getBundle(PRESENTER_STATE_KEY));
        return (T) presenter;
    }

    @Override
    public Bundle save(Presenter presenter) {
        Bundle bundle = new Bundle();
        bundle.putString(PRESENTER_ID_KEY,presenterToId.get(presenter));
        Bundle stateBundle = new Bundle();
        presenter.save(stateBundle);
        bundle.putBundle(PRESENTER_STATE_KEY,stateBundle);
        return bundle;
    }

    @Override
    public void destroy(Presenter presenter) {
        presenter.destroy();
        idToPresenter.remove(presenterToId.remove(presenter));
    }

    @Override
    public void print(Printer printer) {

    }

    private String providePresenterId(Class<? extends Presenter> presenterClass, Bundle savedState) {
        return savedState != null ? savedState.getString(PRESENTER_ID_KEY) :
                presenterClass.getSimpleName() + " (" + mNextId++ + "/" + System.nanoTime() + "/" + (int) (Math.random() * Integer.MAX_VALUE) + ")";
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private Presenter instantiatePresenter(Class<? extends Presenter> presenterClass, String id) {
        Presenter presenter;
        try {
            presenter = presenterClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        idToPresenter.put(id, presenter);
        presenterToId.put(presenter, id);
        return presenter;
    }

}
