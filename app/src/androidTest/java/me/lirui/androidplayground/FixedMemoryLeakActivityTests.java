package me.lirui.androidplayground;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.lirui.androidplayground.memorymanage.FixedMemoryLeakActivity;

/**
 * Created by RichardLee on 2017/5/15.
 */

@RunWith(AndroidJUnit4.class)
public class FixedMemoryLeakActivityTests {
    @Rule
    public ActivityTestRule<FixedMemoryLeakActivity> mActivityRule = new ActivityTestRule<FixedMemoryLeakActivity>(FixedMemoryLeakActivity.class);

    @Test
    public void testLaunch() {
        Assert.assertTrue(true);
    }
}
