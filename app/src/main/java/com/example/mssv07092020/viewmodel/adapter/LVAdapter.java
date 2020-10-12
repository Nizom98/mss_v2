package com.example.mssv07092020.viewmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        final Lab1 lab1 = (Lab1) getItem(i);
        final TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_name.setText(lab1.getName());
        final TextView tv_surname =(TextView) view.findViewById(R.id.tv_surname);
        tv_surname.setText(lab1.getSurname());
        final TextView tv_res =(TextView) view.findViewById(R.id.tv_result);
        tv_res.setText(lab1.getTotal());
        final Button btn = (Button)view.findViewById(R.id.btn_show_toast_res);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_surname.setVisibility(View.GONE);
                tv_res.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
                Toast.makeText(ctx, lab1.getTotal(), Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }
}
