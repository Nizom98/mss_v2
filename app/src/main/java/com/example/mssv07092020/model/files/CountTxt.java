package com.example.mssv07092020.model.files;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountTxt extends AppCompatActivity {
    private String fname = "";
    private String fdir = "";
    private final static String TAG = "TxtFile:";

    public CountTxt(String fdir){
        this.fdir = fdir;
        this.fname = "totalCount.txt";
    }

    public void write(String s){
        try {
            File file = new File(fdir + fname);
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

    public String read(String fdir){
        String line = "";
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(fdir + fname));
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
