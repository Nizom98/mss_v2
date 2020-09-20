package com.example.mssv07092020.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class Lab1 {
    private String name, surname, total;

    public Lab1() {
        this.name = "";
        this.surname = "";
        this.total = "";
    }

    public Lab1(String name, String surname, String total) {
        this.name = name;
        this.surname = surname;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
