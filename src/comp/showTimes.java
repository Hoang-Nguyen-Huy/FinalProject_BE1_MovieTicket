package comp;

import util.TicketManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class showTimes {
    private static final String filmFile = "src/data/film.txt";

    final static String DATE_FORMAT = "dd/MM/yyyy";

    DateFormat f = new SimpleDateFormat(DATE_FORMAT);

    protected String filmID, theaterID;

    protected Date date;

    protected int availableSeats;

    public showTimes(String filmID, String theaterID, Date date, int availableSeats) {
        this.filmID = filmID;
        this.theaterID = theaterID;
        this.date = date;
        this.availableSeats = availableSeats;
    }

    public String getFilmID() {
        return filmID;
    }
    public String getTheaterID() {
        return theaterID;
    }
    public Date getDate() {
        return date;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }
    public void setTheaterID(String theaterID) {
        this.theaterID = theaterID;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
