package com.example.myhandlerlopper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnstart, btnstop;
    private TextView textviewThreadCount;
    int count = 0;
    Handler handler;
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart = (Button) findViewById(R.id.button);
        btnstop = (Button) findViewById(R.id.button2);
        textviewThreadCount = (TextView) findViewById(R.id.textView);

        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
         handler = new Handler(getApplicationContext().getMainLooper());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                mStopLoop = true;
                Toast.makeText(this,"Thread started",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mStopLoop) {
                            try {
                                Thread.sleep(1000);
                                count++;
                            } catch (InterruptedException e) {
                                Log.i(TAG, e.getMessage());
                            }
                            Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId() + ", Count : " + count);
                            handler.post(new Runnable() {
                               @Override
                               public void run() {
                                   textviewThreadCount.setText(" " + count);
                               }
                           });
                        }
                    }

                }).start();
                break;
            case R.id.button2:
                mStopLoop = false;
                Toast.makeText(this,"Thread stoped",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}