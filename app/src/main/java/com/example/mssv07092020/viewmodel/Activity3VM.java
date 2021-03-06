package com.example.mssv07092020.viewmodel;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import com.example.mssv07092020.MainActivity3;
import com.example.mssv07092020.R;
import com.example.mssv07092020.model.Repo;

import static com.example.mssv07092020.MainActivity3.PENDING_INTENT_KEY;

public class Activity3VM extends BaseObservable {
    @Bindable
    public MutableLiveData<String> tv_count = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> et_link = new MutableLiveData<>();

    FragmentTransaction transaction;

    public Context ctx;
    public Repo repo;

    public Activity3VM(Context context, String query) {
        this.ctx = context;
        repo = new Repo(context);
        et_link.setValue(query);//"https://e.sfu-kras.ru/login/index.php"
    }

    public void onBtnShowTotalCountCklicked(){
        /*repo.stopCount();
        int count = repo.getTotalCount();
        tv_count.setValue("" + count);*/
    }

    public void onBtnOpenLinkClicked(){
        Uri uri = Uri.parse(et_link.getValue().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        ctx.startActivity(Intent.createChooser(intent, "Choose browser"));

    }

    public void saveTotalCount(String str){
        repo.saveTotalCount(str);
    }

    public void onStartCountClicked(){
        ((MainActivity3)ctx).startCountClicked();
    }

    public void onStopCountClicked(){
        ((MainActivity3)ctx).stopService();
    }
}
