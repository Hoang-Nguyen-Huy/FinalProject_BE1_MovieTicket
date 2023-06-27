package comp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Film {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    DateFormat f = new SimpleDateFormat(DATE_FORMAT);
    protected String filmID, name, director;
    protected int duration;
    protected Date date;

    public Film(String filmID, String name, String director, int duration, Date date) {
        this.filmID = filmID;
        this.name = name;
        this.director = director;
        this.duration = duration;
        this.date = date;
    }
    public String getFilmId() {
        return filmID;
    }
    public String getName() {
        return name;
    }
    public String getDirector() {
        return director;
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
    public void setDirector(String director) {
        this.director = director;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("{\tFilm ID: %s,\n\t\tFilm Name: %s,\n\t\tDirector: %s,\n\t\tDuration: %s,\n\t\tDate: %s \n\t},",
                            filmID, name, director, (duration<60?duration:(duration/60) + "h" + (duration%60) + "m"), date);
    }
}
