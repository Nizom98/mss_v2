package com.example.mssv07092020.model.files;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TxtFile extends AppCompatActivity {
    private String fname = "";
    private Context ctx;
    private final static String TAG = "TxtFile:";

    public TxtFile(Context context){
        this.ctx = context;
        this.fname = "totalCount.txt";
    }

    public void write(String s){
        try {
            File file = new File(ctx.getFilesDir()+ fname);
            if (!file.exists()) {
                Log.d(TAG, "CREATING_FILE");
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(s.getBytes());
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        Log.d(TAG, "END_OF_WRITE_FUNC");
    }

    public String read(){
        String line = "";
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(ctx.getFilesDir() + fname));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ( (line = bufferedReader.readLine()) != null ) {
                stringBuilder.append(line);
            }
            fileInputStream.close();
            line = stringBuilder.toString();
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        Log.d(TAG, "END_OF_READ_FUNC");
        return line;
    }
}
