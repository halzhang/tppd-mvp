package com.halzhang.android.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.halzhang.android.mvp.manager.DefaultPresenterManager;
import com.halzhang.android.mvp.manager.PresenterManager;
import com.halzhang.android.mvp.presenter.Presenter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 单元测试
 * {@link com.halzhang.android.mvp.manager.PresenterManager} unit test
 * Created by Hal on 15/5/2.
 */
public class PresenterManagerTest {

    @Before
    public void testBefore() {
        PresenterManager.setInstance(new DefaultPresenterManager());
    }

    @Test
    public void textGetInstance() {
        Assert.assertNotNull(PresenterManager.getInstance());
    }

    public static class TestPresenter extends Presenter {

        AtomicBoolean testPresenterOnCreateBundleStateAssert = new AtomicBoolean();
        AtomicBoolean testPresenterOnSave = new AtomicBoolean();

        @Override
        protected void onCreate(@NonNull Bundle saveState) {
            if(saveState != null){
                Assert.assertEquals(1, saveState.getInt("test_int"));
                testPresenterOnCreateBundleStateAssert.set(true);
            }
        }

        @Override
        protected void onSave(@NonNull Bundle saveState) {
            super.onSave(saveState);
            saveState.putInt("test_int", 1);
            testPresenterOnSave.set(true);
        }
    }

    @Test
    public void testProvide() {
        TestPresenter testPresenter = PresenterManager.getInstance().provide(TestPresenter.class, null);
        Assert.assertNotNull(testPresenter);
        Bundle bundle = PresenterManager.getInstance().save(testPresenter);
        Assert.assertNotNull(bundle);
        Assert.assertTrue(testPresenter.testPresenterOnSave.get());
        PresenterManager.setInstance(new DefaultPresenterManager());
        testPresenter = PresenterManager.getInstance().provide(TestPresenter.class, bundle);
        Assert.assertTrue(testPresenter.testPresenterOnCreateBundleStateAssert.get());
    }
}
