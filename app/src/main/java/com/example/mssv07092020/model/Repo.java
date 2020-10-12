package com.example.mssv07092020.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.mssv07092020.MainActivity3;
import com.example.mssv07092020.R;
import com.example.mssv07092020.model.files.TxtFile;
import com.example.mssv07092020.model.interfaces.IDao;
import com.example.mssv07092020.model.room.DB;
import com.example.mssv07092020.model.room.Note;

import java.util.ArrayList;
import java.util.List;

public class Repo {
    private IDao iDao;
    private MutableLiveData<List<Note>> allNotes;
    private static DB db = null;
    private Context ctx;
    public final static int INSERTED = 1;
    public final static int EXIST_NOTE = 2;


    private TxtFile txtFile;

    public Repo(Context context){
        txtFile = new TxtFile(context);
        if(db != null) return;
        db = Room.databaseBuilder(context.getApplicationContext(),
                DB.class, "notedb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        ctx = context;
    }

    /*Добавляем новую запись в БД*/
    public boolean addNote(String name, String surname, String total){
        if(this.getNote(name, surname, total) == null){ //если запись с такими параметрами уже существует, то новую запись НЕ вставляем
            this.addNote(new Note(name, surname, total));
            return true; //сигнал о то, что удалось добавить запись
        }
        return false; //не удалось добавить запись
    }

    /*Добавляем новую запись в БД*/
    public int addNote(Lab1 lab1){
        if(this.getNote(lab1.getName(), lab1.getSurname(), lab1.getTotal()) == null){ //если запись с такими параметрами уже существует, то новую запись НЕ вставляем
            this.addNote(new Note(lab1.getName(), lab1.getSurname(), lab1.getTotal()));
            return INSERTED; //сигнал о то, что удалось добавить запись
        }
        return EXIST_NOTE; //не удалось добавить запись
    }

    public Lab1 getNote(Lab1 l){
        ArrayList<Lab1> notes = this.getNotes();
        for(Lab1 n : notes){
            if(n.getName() == l.getName() && n.getSurname() == l.getSurname() && n.getTotal() == l.getTotal()) return  n;
        }
        return  null;
    }

    //Получаем все записи
    public ArrayList<Lab1> getNotes(){
       List<Note> notes =  db.Dao().getAllNotes();
       ArrayList<Lab1> lab1s = new ArrayList<>();
       for(Note n : notes){
            lab1s.add(new Lab1(n.getName(), n.getSurname(), n.getTotal()));
       }
       return  lab1s;
    }

    //Получаем запись по "name" и "surname"
    public Lab1 getNote(String name, String surname, String total){
        ArrayList<Lab1> notes = this.getNotes();
        for(Lab1 n : notes){
            if(n.getName() == name && n.getSurname() == surname && n.getTotal() == total) return  n;
        }
        return  null;
    }

    //Удаляем запись по "name" и "surname"
    public void deleteNote(String name, String surname, String total){
        List<Note> notes =  db.Dao().getAllNotes();
        for(Note n : notes){
            if(n.getName() == name && n.getSurname() == surname && n.getTotal() == total) this.removeNote(n);
        }
    }

    public void saveTotalCount(String s){
        Log.d("COUNT_TEST_SAVI", s);

        txtFile.write(s);
    }

    public String getTotalCount(){
        Log.d("COUNT_TEST_SAVI2", "f");
        return txtFile.read();
    }

    public void saveNote2SharedPreferences(Lab1 lab1, SharedPreferences sp, String postfix){
        if(sp == null) return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name" + postfix, lab1.getName());
        editor.putString("surname" + postfix, lab1.getSurname());
        editor.putString("total" + postfix, lab1.getTotal());
        editor.commit();
    }

    public Lab1 loadNoteFromSharedPreferences(SharedPreferences sp, String postfix){
        if(sp == null) return  new Lab1();
        Lab1 lab1 = new Lab1(
                sp.getString("name" + postfix,""),
                sp.getString("surname" + postfix,""),
                sp.getString("total" + postfix,"")
        );
        return  lab1;
    }

    //Удаляем все записи в БД
    public void removeAllNotes(){  db.Dao().deleteAllNotes(); }

    private void addNote(Note n){  db.Dao().insert(n); }

    private void removeNote(Note n){  db.Dao().deleteNote(n); }

}
