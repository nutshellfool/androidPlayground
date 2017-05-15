package me.lirui.androidplayground;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import junit.framework.Assert;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<Object>(obj);
        forceGCRecycle();
        Assert.assertNotNull(weakRef.get());
    }

    private void forceGCRecycle() {
        int available = (int)getCurrentAviliableMemory();
        createRectangleImageByWith((int)Math.round(Math.sqrt(available)) - 1024);
    }

    private Bitmap createRectangleImageByWith(int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ALPHA_8);
        Log.d("GC test", String.format("bitmap size %d",bitmap.getByteCount()));
        return bitmap;
    }

    private long getCurrentAviliableMemory() {
        Runtime rt = Runtime.getRuntime();
//        long a = rt.totalMemory() - rt.freeMemory();
        long a = rt.maxMemory() - rt.freeMemory();
        Log.d("GC test", String.format("aviliable memory %d", a));
        return a;
    }
}
