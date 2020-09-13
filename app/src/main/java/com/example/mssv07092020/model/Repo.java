package com.example.mssv07092020.model;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.mssv07092020.R;
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
    public final static int EMPTY_FIELDS = 1;
    public final static int INSERTED = 2;
    public final static int EXIST_NOTE = 3;

    public Repo(Context context){
        if(db != null) return;
        db = Room.databaseBuilder(context.getApplicationContext(),
                DB.class, "notedb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        ctx = context;
    }

    /*Добавляем новую запись в БД*/
    public boolean addNote(String name, String surname){
        if(this.getNote(name, surname) == null){ //если запись с такими параметрами уже существует, то новую запись НЕ вставляем
            this.addNote(new Note(name, surname));
            return true; //сигнал о то, что удалось добавить запись
        }
        return false; //не удалось добавить запись
    }

    /*Добавляем новую запись в БД*/
    public int addNote(Lab1 lab1){
        if(lab1.getName() == null || lab1.getName().length() == 0 || lab1.getSurname() == null || lab1.getSurname().length() == 0){
            return EMPTY_FIELDS;
        }
        if(this.getNote(lab1.getName(), lab1.getSurname()) == null){ //если запись с такими параметрами уже существует, то новую запись НЕ вставляем
            this.addNote(new Note(lab1.getName(), lab1.getSurname()));
            return INSERTED; //сигнал о то, что удалось добавить запись
        }
        return EXIST_NOTE; //не удалось добавить запись
    }

    //Получаем все записи
    public ArrayList<Lab1> getNotes(){
       List<Note> notes =  db.Dao().getAllNotes();
       ArrayList<Lab1> lab1s = new ArrayList<>();
       for(Note n : notes){
            lab1s.add(new Lab1(n.getName(), n.getSurname()));
       }
       return  lab1s;
    }

    /*public Note getNote(int id){
        ArrayList<Lab1> notes = this.getNotes();
        for(Lab1 n : notes){
            if(n.getId() == id) return  n;
        }
        return  null;
    }*/

    //Получаем запись по "name" и "surname"
    public Lab1 getNote(String name, String surname){
        ArrayList<Lab1> notes = this.getNotes();
        for(Lab1 n : notes){
            if(n.getName() == name && n.getSurname() == surname) return  n;
        }
        return  null;
    }

    //Удаляем запись по "name" и "surname"
    public void deleteNote(String name, String surname){
        List<Note> notes =  db.Dao().getAllNotes();
        for(Note n : notes){
            if(n.getName() == name && n.getSurname() == surname) this.removeNote(n);
        }
    }

    //Удаляем все записи в БД
    public void removeAllNotes(){  db.Dao().deleteAllNotes(); }

    private void addNote(Note n){  db.Dao().insert(n); }

    private void removeNote(Note n){  db.Dao().deleteNote(n); }
}
