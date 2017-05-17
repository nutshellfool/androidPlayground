package me.lirui.androidplayground;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;


/**
 * Created by RichardLee on 2017/5/12.
 */

public class MemoryManageUnitTest {
    @Test
    public void strongReferenceTest() throws Exception {
        Object object = new Object();
        Object otherObject = object;
        Assert.assertNotNull(object);
        Assert.assertNotNull(otherObject);

        object = null;
        Assert.assertNull(object);
        Assert.assertNotNull(otherObject);
    }

    @Test
    public void weakReferenceTest() throws Exception {
        WeakReference r = new WeakReference(new String("I'm here"));
        WeakReference sr = new WeakReference("I'm here");
        Assert.assertNotNull(r.get());
        Assert.assertNotNull(r.get());

        System.gc();
        Thread.sleep(100);

        Assert.assertNull(r.get());
        Assert.assertNotNull(sr.get());
    }

    @Test
    public void softReferenceTest() throws Exception {
        SoftReference r = new SoftReference(new String("soft string reference"));
        Assert.assertNotNull(r.get());

        System.gc();
        Thread.sleep(100);

        // Here should make a situation that memory may go drain.
        // and then we can assert the soft referenced object be released
//        Assert.assertNull(r.get());
    }
}
