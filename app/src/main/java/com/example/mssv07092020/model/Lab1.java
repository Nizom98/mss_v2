package com.example.mssv07092020.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mssv07092020.BR;

public class Lab1 {
    private String name, surname;

    public Lab1() {
        this.name = "";
        this.surname = "";
    }

    public Lab1(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
