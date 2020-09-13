package com.example.mssv07092020.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mssv07092020.BR;
import com.example.mssv07092020.R;
import com.example.mssv07092020.model.Lab1;
import com.example.mssv07092020.model.Repo;
import com.example.mssv07092020.model.room.Note;

import java.util.ArrayList;
import java.util.List;

public class Lab1VM extends BaseObservable {
    @Bindable
    public MutableLiveData<String> name = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> surname = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> result = new MutableLiveData();

    public Lab1 lab1;
    public Context ctx;
    public Repo repo;

    public Lab1VM() {
        repo = new Repo(ctx);
    }

    public Lab1VM(Context context, Lab1 lab1) {
        this.lab1 = lab1;
        this.ctx = context;
        repo = new Repo(context);
        result.setValue("RESULT_FRAG_!");
    }

    public void onBtnGetResClicked(){
        //repo.removeAllNotes();
        lab1 = new Lab1(name.getValue(), surname.getValue());
        int res = repo.addNote(lab1);
        if(res != Repo.INSERTED){
            if( res == Repo.EMPTY_FIELDS){
                Toast.makeText(ctx, ctx.getResources().getString(R.string.empty_field), Toast.LENGTH_LONG).show();
            } else if( res == Repo.EXIST_NOTE){
                Toast.makeText(ctx, ctx.getResources().getString(R.string.exists_data), Toast.LENGTH_LONG).show();
            }
            return;
        }

        String prefix = ctx.getResources().getString(R.string.res_prefix);
        result.setValue(prefix +  lab1.getName() +  lab1.getSurname());
        /*lab1.setName(name.getValue());
        lab1.setSurname(surname.getValue());*/
        name.setValue("");
        surname.setValue("");
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.surname);
        notifyPropertyChanged(BR.result);
        //notifyPropertyChanged(BR.lab1);
        Toast.makeText(ctx, prefix + lab1.getName() + " " + lab1.getSurname(), Toast.LENGTH_LONG).show();
    }

    public ArrayList<Lab1> getArrLab1(){
        return repo.getNotes();
    }


}
