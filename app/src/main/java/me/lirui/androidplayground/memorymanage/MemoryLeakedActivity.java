package me.lirui.androidplayground.memorymanage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.lirui.androidplayground.R;

public class MemoryLeakedActivity extends AppCompatActivity {

    private TextView mTitleTextview;

    private class WorkerAsyncTask extends AsyncTask {
        private Activity referenceActiviy;

        WorkerAsyncTask(Activity activity) {
            referenceActiviy = activity;
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

            if (referenceActiviy instanceof MemoryLeakedActivity) {
                MemoryLeakedActivity leakedActivity = (MemoryLeakedActivity) referenceActiviy;
                leakedActivity.updateUI("UI updated");
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leaked);
        mTitleTextview = (TextView) findViewById(R.id.memory_leaked_act_textview_title);

        new WorkerAsyncTask(this).execute();
    }

    private void updateUI(String titleString) {
        mTitleTextview.setText(titleString);
    }
}
