package com.example.mssv07092020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mssv07092020.databinding.ActivityMain3Binding;
import com.example.mssv07092020.model.services.ServiceCount;
import com.example.mssv07092020.model.services.ServiceCounter;
import com.example.mssv07092020.viewmodel.Activity3VM;
import com.example.mssv07092020.viewmodel.fragments.Fragment1;
import com.example.mssv07092020.viewmodel.fragments.Fragment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity3 extends AppCompatActivity {


    public static final String PENDING_INTENT_KEY = "pending_intent";
    public static final String COUNTER_ANSWER_KEY = "pending_intent";

    private static final int COUNTER_SERVICE = 1;

    public static final int COUNTER_START = 1;
    public static final int COUNTER_ANSWER = 2;
    public static final int COUNTER_FINISH = 3;

    Button btn;
    Boolean isFrag1 = true;
    Fragment1 fragment1;
    Fragment2 fragment2;
    EditText link;
    TextView tv_total_count;
    private ActivityMain3Binding activityMain3Binding;
    private Activity3VM activity3VM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String query = "";
        if (intent != null) {
            String it = intent.getType();
            if(it != null){
                Log.d("ACT3:getType", it);
            } else Log.d("ACT3:getType", "null");
            query = intent.getStringExtra(Intent.EXTRA_TEXT);
        } else {
            Log.d("ACT3:INTENT_NULL", "NO_INTENT");
        }

        activityMain3Binding = DataBindingUtil.setContentView(this, R.layout.activity_main3);
        activity3VM = new Activity3VM(this, query);
        activityMain3Binding.setAct3vm(activity3VM );

        btn = findViewById(R.id.btn_change_fragment);
        tv_total_count = findViewById(R.id.tv_count);
       // btn_open = findViewById(R.id.btn_open_link);
        link = findViewById(R.id.et_link);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFrag1) showFragmen1();
                else showFragmen2();
                isFrag1 = !isFrag1;
            }
        });
    }

    private  void showFragmen1(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments_linear, fragment1);
        transaction.commit();
    }

    private  void showFragmen2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments_linear, fragment2);
        transaction.commit();
    }


    public void startCountClicked(){
        Log.d("MA3", "startCountClicked");
        PendingIntent pendingIntent = createPendingResult(COUNTER_SERVICE, new Intent(), 0);
        Intent intent = new Intent(this, ServiceCount.class);
        intent.putExtra(PENDING_INTENT_KEY, pendingIntent);
        startService(intent);
    }

    public void stopService(){
        Log.d("MA3", "stopService");
        Intent intent = new Intent(this, ServiceCount.class);
        stopService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == COUNTER_SERVICE) {
            int counter = data.getIntExtra(COUNTER_ANSWER_KEY, 0);
            switch (resultCode) {
                case COUNTER_ANSWER:
                    Log.d("MA3", "onActivityResult");
                    Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    break;
                case COUNTER_FINISH:
                    activity3VM.saveTotalCount(String.valueOf(counter));
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
