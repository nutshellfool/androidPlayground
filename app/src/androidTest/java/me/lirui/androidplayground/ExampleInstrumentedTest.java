package me.lirui.androidplayground;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.WeakReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("me.lirui.androidplayground", appContext.getPackageName());
    }

    @Test
    public void testWeakRef() throws Exception {
        WeakReference<Object> weakRef = new WeakReference<Object>(new Object());
        forceGCRecycle();
//        Assert.assertNotNull(weakRef.get());
        assertNotNull(weakRef.get());
    }

    private void forceGCRecycle() {
        int available = (int) getCurrentAviliableMemory();
        createRectangleImageByWith((int) Math.round(Math.sqrt(available)) - 1024);
    }

    private Bitmap createRectangleImageByWith(int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ALPHA_8);
        Log.d("GC test", String.format("bitmap size %d", bitmap.getByteCount()));
        return bitmap;
    }

    private long getCurrentAviliableMemory() {
        Runtime rt = Runtime.getRuntime();
        long a = rt.maxMemory() - rt.freeMemory();
        Log.d("GC test", String.format("aviliable memory %d", a));
        return a;
    }
}
