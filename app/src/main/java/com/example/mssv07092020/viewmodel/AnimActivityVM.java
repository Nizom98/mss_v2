package com.example.mssv07092020.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.databinding.BaseObservable;

import com.example.mssv07092020.R;
import com.example.mssv07092020.model.Repo;

public class AnimActivityVM extends BaseObservable {
    public Context ctx;
    public int id_tv;
    public Repo repo;
    public AnimActivityVM(Context context, int id_tv) {
        this.ctx = context;
        this.id_tv = id_tv;
    }

    public void onBtnAnimScaleClicked(){
        setAnimation(R.anim.scale);
    }

    public void onBtnAnimRotateClicked(){
        setAnimation(R.anim.rotate);
    }

    public void onBtnAnimTransparencyClicked(){
        setAnimation(R.anim.transparency);
    }

    public void onBtnAnimTranslateClicked(){
        setAnimation(R.anim.transfer);
    }

    public void setAnimation(int anim_id){
        Activity act = (Activity) this.ctx;
        TextView tv = act.findViewById(id_tv);
        tv.startAnimation(AnimationUtils.loadAnimation(ctx, anim_id));
    }

}
