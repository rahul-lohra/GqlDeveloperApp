package com.rahullohra.gqldeveloperapp;

import java.util.ArrayList;

import javax.inject.Inject;

public class Usecase {

    private ArrayList<String> arrayList;

    @Inject
    Usecase(){
        this.arrayList = new ArrayList<>();
    }
}
