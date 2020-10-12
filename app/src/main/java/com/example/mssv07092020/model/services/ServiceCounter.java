package com.example.mssv07092020.model.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.mssv07092020.model.files.CountTxt;
import com.example.mssv07092020.model.files.TxtFile;

public class ServiceCounter extends Service {
    public int countRes = 0;
    public String fdir_n_name = "";
    public ServiceCounter() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        CountTxt txtFile = new CountTxt(fdir_n_name);
        txtFile.write(countRes + "");
        Log.d("COUNTER_SERV", "onDestroy:result:" + countRes + "");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        fdir_n_name =  (String) intent.getExtras().get("fdir");
        Log.d("COUNTER_SERV", "onStart:" + fdir_n_name);
        count();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void count(){
        int i = 0;
        for(; i < 10; ++i){
            Log.d("COUNT_FUNC","ITEM: " + (i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countRes = i;
    }
}
