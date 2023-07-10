
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
    int price;

    public Film(String filmID, String name, String director, int duration, Date date, int price) {
        this.filmID = filmID;
        this.name = name;
        this.director = director;
        this.duration = duration;
        this.date = date;
        this.price = price;
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

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) { this.price = price; }
    @Override
    public String toString() {
        return String.format("{\tFilm ID: %s,\n\t\tFilm Name: %s,\n\t\tDirector: %s,\n\t\tDuration: %s,\n\t\tDate: %s \n\t},",
                            filmID, name, director, (duration<60?duration:(duration/60) + "h" + (duration%60) + "m"),
                            String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(date)));
    }

}

