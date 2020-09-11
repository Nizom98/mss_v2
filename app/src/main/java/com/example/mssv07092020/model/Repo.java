package com.example.mssv07092020.model;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.mssv07092020.model.interfaces.IDao;
import com.example.mssv07092020.model.room.DB;
import com.example.mssv07092020.model.room.Note;

import java.util.List;

public class Repo {
    private IDao iDao;
    private MutableLiveData<List<Note>> allNotes;
    private static DB db = null;

    public Repo(Context context){
        if(db != null) return;
        db = Room.databaseBuilder(context.getApplicationContext(),
                DB.class, "notedb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public void addNote(String name, String surname){
        this.addNote(new Note(name, surname));
    }

    public void addNote(Note n){
        db.Dao().insert(n);
    }

    public List<Note> getNotes(){
       return db.Dao().getAllNotes();
    }

    public Note getNote(int id){
        List<Note> notes = this.getNotes();
        for(Note n : notes){
            if(n.getId() == id) return  n;
        }
        return  null;
    }

    public Note getNote(String name, String surname){
        List<Note> notes = this.getNotes();
        for(Note n : notes){
            if(n.getName() == name && n.getSurname() == surname) return  n;
        }
        return  null;
    }

    public void removeNote(Note n){
        db.Dao().deleteNote(n);
    }

    public void removeAllNotes(){
        db.Dao().deleteAllNotes();
    }
}
