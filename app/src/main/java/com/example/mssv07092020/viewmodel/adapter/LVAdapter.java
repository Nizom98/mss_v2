package com.example.mssv07092020.viewmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mssv07092020.R;
import com.example.mssv07092020.model.Lab1;

import java.util.ArrayList;

public class LVAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater linf;
    ArrayList<Lab1> data;

    public LVAdapter(Context ctx, ArrayList<Lab1> data) {
        this.ctx = ctx;
        //this.linf = linf;
        this.linf = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = view1;
        if(view == null){
            view = linf.inflate(R.layout.list_row, viewGroup, false);
        }
        Lab1 lab1 = (Lab1) getItem(i);
        ((TextView) view.findViewById(R.id.tv_name)).setText(lab1.getName());
        ((TextView) view.findViewById(R.id.tv_surname)).setText(lab1.getSurname());
        return  view;
    }
}
