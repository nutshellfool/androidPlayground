package me.lirui.androidplayground;

import org.junit.Assert;
import org.junit.Test;


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
//        ActivityManager.getMemoryClass()

    }
}
