package com.example.mssv07092020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

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

    Button btn, btn_open;
    Boolean isFrag1 = true;
    Fragment1 fragment1;
    Fragment2 fragment2;
    EditText link;
    TextView tv_total_count;
    //BackgroundTasks backTask = null;



    SharedPreferences shPref;

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
        LongAndComplicatedTask longTask = new LongAndComplicatedTask(); // Создаем экземпляр
        longTask.execute(); // запускаем
        //longTask.cancel(true);

        /*saveToTxt("19981120");
        String s = readfromtxt();
        Log.d("TXTX_DATA", s);
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();*/
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

    public String countF(){
        int i = 0;
        for(; i < 10; ++i){
            Log.d("COUNT_FUNC","ITEM: " + (i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return i + "";
    }

    class LongAndComplicatedTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... noargs) {
            return countF();
        }
        @Override
        protected void onPostExecute(String result) {
            activity3VM.saveTotalCount(result);
            //save2txt(result);
            String s = activity3VM.getTotalCount();//readfromtxt();
            Log.d("COUNT::::", s);//activity3VM.getTotalCount()
            tv_total_count.setText(getResources().getString(R.string.text_total_count_prefix) + s);
            //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
    }

    public void save2txt(String s){
        try {
            FileOutputStream fos = openFileOutput("totalCount1.txt", MODE_PRIVATE);
            fos.write(s.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    final static String fileName = "data.txt";

    public void saveToTxt(String s){
        try {
            File file = new File(this.getFilesDir()+ fileName);
            if (!file.exists()) {
                Log.d("TAG4", "CREATING_FILE");
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,false);
            fileOutputStream.write(s.getBytes());

        }  catch(FileNotFoundException ex) {
            Log.d("TAG1", ex.getMessage());
        }  catch(IOException ex) {
            Log.d("TAG2", ex.getMessage());
        }
    }

    public String readfromtxt(){
        String ret = "";
        String line = "";

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(this.getFilesDir() + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line);
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d("TAG3", ex.getMessage());
        }
        catch(IOException ex) {
            Log.d("TAG4", ex.getMessage());
        }
        return line;
    }

    public class BackgroundTasks extends  Thread {
        int count;
        int interval;
        TextView tv;
        BackgroundTasks(int count, int interval, TextView textView){
            this.count = count;
            this.interval = interval;
            this.tv = textView;
        }
        @Override
        public void run() {
            for(int i = 0; i < count; ++i){

                Log.d("COUNT_T","Count: " + (i + 1));
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

}
