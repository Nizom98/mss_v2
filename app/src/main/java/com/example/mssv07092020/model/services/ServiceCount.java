package com.example.mssv07092020.model.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mssv07092020.MainActivity3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceCount extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService es;
    MyRun myRun;



    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        myRun.stop();
        Log.d(LOG_TAG, "MyService onDestroy");
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");

        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity3.PENDING_INTENT_KEY);
        myRun = new MyRun(pendingIntent);
        es.execute(myRun);
        //startCount();
        return super.onStartCommand(intent, flags, startId);
    }

    void startCount(){
        Thread thread = new Thread(myRun,"R1");
        thread.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    class MyRun implements Runnable {
        Boolean stopCount;
        PendingIntent pi;

        public MyRun(PendingIntent pi) {
            this.pi = pi;
            this.stopCount = false;
            Log.d(LOG_TAG, "MyRun# create");
        }

        public void run() {
            int i = 0;
            for(; i < 9999999 && !stopCount; ++i){
                Log.d("COUNT_FUNC","ITEM: " + (i + 1));
                try {
                    Thread.sleep( 3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sayParent(new Intent().putExtra(MainActivity3.COUNTER_ANSWER_KEY, i),
                        MainActivity3.COUNTER_ANSWER);
            }
        }

        void stop() {
            this.stopCount = true;
        }

        void sayParent(Intent intent, int code){
            try {
                pi.send(ServiceCount.this, code, intent);
            }
            catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
        }
    }
}
