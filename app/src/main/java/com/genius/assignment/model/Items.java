package com.genius.assignment.model;

import java.util.ArrayList;

public class Items
{
    private String name;
    ArrayList<String> spinner_title;

    public Items(String name, ArrayList<String> spinner_title) {
        this.name = name;
        this.spinner_title = spinner_title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSpinner_title() {
        return spinner_title;
    }

    public void setSpinner_title(ArrayList<String> spinner_title) {
        this.spinner_title = spinner_title;
    }
}
