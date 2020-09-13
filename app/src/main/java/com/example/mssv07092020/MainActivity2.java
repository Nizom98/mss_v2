package com.example.mssv07092020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ListView;

//import com.example.mssv07092020.adapter.Lab1Adapter;
import com.example.mssv07092020.databinding.ActivityMain2Binding;
import com.example.mssv07092020.model.Lab1;
import com.example.mssv07092020.model.Repo;
import com.example.mssv07092020.viewmodel.Lab1VM;
import com.example.mssv07092020.viewmodel.adapter.LVAdapter;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding activityMainBinding;
    private Lab1VM lab1VM;
    private ArrayList<Lab1VM> lab1VMArrayList;
    //private Lab1Adapter lab1Adapter;

    private ArrayList<Lab1> lv;
    private LVAdapter adapter;
    public Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        /*lab1VM = new Lab1VM();
        lab1VMArrayList = new ArrayList<>();
        lab1VMArrayList.add(lab1VM);
        lab1Adapter = new Lab1Adapter(this, lab1VMArrayList);
        activityMainBinding.listview.setAdapter(lab1Adapter);*/

        //------fill------0
        repo = new Repo(this);
        lv = repo.getNotes();
        /*Lab1 lab1 = new Lab1("name1", "sur1");
        Lab1 lab2 = new Lab1("name2", "sur2");
        lv = new ArrayList<>();
        lv.add(lab1);
        lv.add(lab2);*/
        //------fill------1
        adapter = new LVAdapter(this, lv);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}