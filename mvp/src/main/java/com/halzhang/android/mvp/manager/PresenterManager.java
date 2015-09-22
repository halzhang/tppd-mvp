package com.halzhang.android.mvp.manager;

import android.os.Bundle;
import android.util.Printer;

import com.halzhang.android.mvp.presenter.Presenter;

/**
 * Manager
 * Created by Hal on 15/4/27.
 */
public abstract class PresenterManager {

    private static PresenterManager sInstance = new DefaultPresenterManager();

    public static PresenterManager getInstance(){
        return sInstance;
    }

    public static void setInstance(PresenterManager manager){
        sInstance = manager;
    }

    public abstract <T extends Presenter> T provide(Class<T> presenterClass,Bundle saveState);

    public abstract Bundle save(Presenter presenter);

    public abstract void destroy(Presenter presenter);

    /**
     * 打印 Presenter View
     * @param printer
     */
    public abstract void print(Printer printer);



}
