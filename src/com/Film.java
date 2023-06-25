package com;

import java.util.Date;

public class Film {
    
    protected String filmID, name, author, studio;
    protected int duration;
    protected Date date;

    public Film(String filmID, String name, String author, String studio, int duration, Date date) {
        this.filmID = filmID;
        this.name = name;
        this.author = author;
        this.studio = studio;
        this.duration = duration;
        this.date = date;
    }
    public String getFilmId() {
        return filmID;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getStudio() {
        return studio;
    }
    public int getDuration() {
        return duration;
    }
    public Date getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[ Film ID: %s,\n Film Name: %s,\n Author: %s,\n Duration: %s,\n Date: %s ]",
                            filmID, name, author, studio, (duration<60?duration:(duration/60) + "h" + (duration%60*60) + "m"), date);
    }
    


    


}
