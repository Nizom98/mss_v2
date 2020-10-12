package com.example.mssv07092020.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.service.autofill.SaveRequest;
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

    private SharedPreferences preferences = null;

    public Lab1VM() {
        repo = new Repo(ctx);
    }

    public Lab1VM(Context context, Lab1 lab1, SharedPreferences sp) {
        this.lab1 = lab1;
        this.ctx = context;
        this.preferences = sp;
        repo = new Repo(context);
        result.setValue("RESULT_FRAG_!");
    }

    public String getStringsConcat(String first, String second){
        return first + second;
    }

    public String getStringsMix(String first, String second){
        StringBuilder strBuilder = new StringBuilder("");
        for (int i = 0, lenn = first.length(), lens = second.length(), total = (lenn > lens) ? lenn : lens; i < total; ++i){
            if(i < lenn) strBuilder.append(first.charAt(i));
            if(i < lens) strBuilder.append(second.charAt(i));
        }
        return  strBuilder.toString();
    }

    private boolean showMsgIfEmpty(String name, String surname){
        if(name == null || surname == null || name.length() == 0  || surname.length() == 0){
            Toast.makeText(ctx, ctx.getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void onBtnGetMixClicked(){
        if(showMsgIfEmpty(name.getValue(), surname.getValue())) return;
        String res = getStringsMix(name.getValue(), surname.getValue());
        lab1 = new Lab1(name.getValue(), surname.getValue(), res);
        repo.addNote(lab1);
        setViewFields("", "", res);
        Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
    }

    public void onBtnGetConcatClicked(){
        if(showMsgIfEmpty(name.getValue(), surname.getValue())) return;
        String res = getStringsConcat(name.getValue(), surname.getValue());
        lab1 = new Lab1(name.getValue(), surname.getValue(), res);
        repo.addNote(lab1);
        setViewFields("", "", res);
        Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
    }

    private void setViewFields(String name, String surname, String res){
        this.name.setValue(name);
        this.surname.setValue(surname);
        String prefix = ctx.getResources().getString(R.string.res_prefix);
        this.result.setValue(res);
        setActivityTitle(res);
        notifyView();
    }

    private void setActivityTitle(String s){
        if(this.ctx == null) return;
        Activity act = (Activity) this.ctx;
        act.setTitle(s);
    }

    private void notifyView(){
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.surname);
        notifyPropertyChanged(BR.result);
    }

    public ArrayList<Lab1> getArrLab1(){
        return repo.getNotes();
    }

    public void loadData(String fragmentNum){
        Lab1 l1 =  repo.loadNoteFromSharedPreferences(preferences, fragmentNum);
        setViewFields(l1.getName(), l1.getSurname(), l1.getTotal());
    }

    public void saveData(String fragmentNum){
        repo.saveNote2SharedPreferences(new Lab1(name.getValue(), surname.getValue(), result.getValue()), preferences, fragmentNum);
    }


}
