package com.example.mssv07092020.model.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mssv07092020.model.interfaces.IDao;

@Database(entities = {Note.class}, version = 1)
public abstract  class DB extends RoomDatabase {
    public abstract IDao Dao();
}
