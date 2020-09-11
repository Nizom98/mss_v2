package com.example.mssv07092020.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mssv07092020.model.Lab1;
import com.example.mssv07092020.model.Repo;

public class Lab1VM extends ViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> surname = new MutableLiveData<>();
    public Lab1 lab1;
    public Context context;
    public Repo repo;

    public Lab1VM(Context context, Lab1 lab1) {
        this.lab1 = lab1;
        this.context = context;
        repo = new Repo(context);
    }

    public void onBtnGetResClicked(){
        lab1.setName(name.getValue());
        lab1.setSurname(surname.getValue());
        Toast.makeText(context, lab1.getName() + " " + lab1.getSurname(), Toast.LENGTH_LONG).show();
        repo.addNote(lab1.getName(), lab1.getSurname());
        repo.getNote(lab1.getName(), lab1.getSurname());
        Toast.makeText(context, lab1.getName() + ":" + lab1.getSurname(), Toast.LENGTH_LONG).show();
    }
}
