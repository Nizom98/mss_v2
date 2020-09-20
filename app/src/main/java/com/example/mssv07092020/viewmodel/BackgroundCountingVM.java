package com.example.mssv07092020.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.mssv07092020.model.Lab1;
import com.example.mssv07092020.model.Repo;

public class BackgroundCountingVM extends BaseObservable {
    @Bindable
    public MutableLiveData<String> tv_count = new MutableLiveData<>();

    public Context ctx;
    public Repo repo;

    public BackgroundCountingVM(Context context) {
        this.ctx = context;
        repo = new Repo(context);
        //repo.startCount();
    }

    public void onBtnShowTotalCountCklicked(){
        //repo.stopCount();
        //int count = repo.getTotalCount();
        //tv_count.setValue("" + count);
    }
}
