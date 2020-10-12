package com.example.mssv07092020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mssv07092020.databinding.ActivityAnimBinding;
import com.example.mssv07092020.databinding.ActivityMain2Binding;
import com.example.mssv07092020.viewmodel.AnimActivityVM;

public class AnimActivity extends AppCompatActivity {

    TextView tv;
    private ActivityAnimBinding activityAnimBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_anim);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityAnimBinding = DataBindingUtil.setContentView(this, R.layout.activity_anim);
        activityAnimBinding.setAnimvm(new AnimActivityVM(this, R.id.tv_anim));
    }
}