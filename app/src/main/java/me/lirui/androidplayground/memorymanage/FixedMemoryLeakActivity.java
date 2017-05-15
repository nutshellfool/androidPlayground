package me.lirui.androidplayground.memorymanage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import me.lirui.androidplayground.R;

/**
 * Created by RichardLee on 2017/5/15.
 */

public class FixedMemoryLeakActivity extends Activity {

    private TextView mTitleTextview;
//    private WorkerAsyncTask mWorkerAsyncTask;

    private class FixedWorkerAsyncTask extends AsyncTask {
        private WeakReference<Activity> referenceActiviy;

        FixedWorkerAsyncTask(Activity activity) {
            referenceActiviy = new WeakReference<Activity>(activity);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                // mock the time intense background operation
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return _foo();
        }

        private Object _foo() {
            return new Object();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if(referenceActiviy.get() instanceof FixedMemoryLeakActivity) {
                FixedMemoryLeakActivity fixedLeakedActivity = (FixedMemoryLeakActivity) referenceActiviy.get();
                fixedLeakedActivity.updateUI("UI updated");
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_memory_leaked);
//        mTitleTextview = (TextView) findViewById(R.id.memory_leaked_act_textview_title);

        new FixedWorkerAsyncTask(this).execute();
//        mWorkerAsyncTask = new WorkerAsyncTask(this);
//        mWorkerAsyncTask.execute();
    }

    private void updateUI(String titleString) {
        mTitleTextview.setText(titleString);
    }
}
