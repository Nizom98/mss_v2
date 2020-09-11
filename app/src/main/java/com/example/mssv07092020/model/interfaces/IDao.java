package com.example.mssv07092020.model.interfaces;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mssv07092020.model.room.Note;

import org.w3c.dom.Node;

import java.util.List;

@Dao
public interface IDao {
    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Delete
    void deleteNote(Note note);

    @Insert
    void insert(Note note);

    /*@Update
    void update(Note note);*/

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();



}
