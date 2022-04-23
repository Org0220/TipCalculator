package com.example.tipcalculator1;

import androidx.fragment.app.Fragment;

import java.text.ParseException;

public interface Connector {

    public void respond() throws ParseException;
    public void respond2();
}
