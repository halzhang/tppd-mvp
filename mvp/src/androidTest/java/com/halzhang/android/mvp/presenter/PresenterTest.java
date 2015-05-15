package com.halzhang.android.mvp.presenter;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.halzhang.android.mvp.TestActivity;

import org.junit.runner.RunWith;

/**
 * Test {@link Presenter}
 * Created by Hal on 15/5/4.
 */
@RunWith(AndroidJUnit4.class)
public class PresenterTest extends ActivityInstrumentationTestCase2 {

    public PresenterTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
