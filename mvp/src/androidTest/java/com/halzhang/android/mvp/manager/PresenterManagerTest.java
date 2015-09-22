package com.halzhang.android.mvp.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.halzhang.android.mvp.TestActivity;
import com.halzhang.android.mvp.presenter.Presenter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link com.halzhang.android.mvp.manager.PresenterManager} unit AndroidTest
 * Created by Hal on 15/5/2.
 */
@RunWith(AndroidJUnit4.class)
public class PresenterManagerTest extends ActivityInstrumentationTestCase2 {
    public PresenterManagerTest() {
        super(TestActivity.class);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        PresenterManager.setInstance(new DefaultPresenterManager());
    }

    @Test
    public void testGetInstance(){
        assertNotNull(PresenterManager.getInstance());
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
        public Object createViewCallback(IView view) {
            return null;
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
