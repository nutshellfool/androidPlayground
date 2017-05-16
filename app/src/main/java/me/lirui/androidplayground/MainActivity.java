package me.lirui.androidplayground;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.lirui.androidplayground.memorymanage.FixedMemoryLeakActivity;
import me.lirui.androidplayground.memorymanage.MemoryLeakedActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Object obj = new Object();
//        WeakReference<Object> weakRef = new WeakReference<Object>(obj);
//        forceGCRecycle();
//        Assert.assertNotNull(weakRef.get());
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

    public void jumpToLeakedActivity(View view) {
        Intent intent = new Intent(this, MemoryLeakedActivity.class);
        startActivity(intent);
    }

    public void jumpToFixedLeakedActivity(View view) {
        Intent intent = new Intent(this, FixedMemoryLeakActivity.class);
        startActivity(intent);
    }
}
