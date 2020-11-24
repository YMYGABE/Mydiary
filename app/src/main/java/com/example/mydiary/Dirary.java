package com.example.mydiary;

import java.util.Date;

public class Dirary {
    private int Id;

    private String Title;

    private String MainContext;
    private byte[] Image;
    private String time;

    public Dirary(int id, String title, String mainContext, byte[] image, String time) {
        this.Id = id;
        this.Title = title;
        this.MainContext = mainContext;
        this.Image = image;
        this.time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMainContext() {
        return MainContext;
    }

    public void setMainContext(String mainContext) {
        MainContext = mainContext;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
