package com.halzhang.android.mvp.annotation;

import com.halzhang.android.mvp.presenter.Presenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Presenter annotation
 * Created by Hal on 15/4/29.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends Presenter> value();
}
