package run;

import util.FilmManager;

import java.text.ParseException;

/*
Nguyễn Huy Hoàng
Lê Quang Trung
Final Project BE1 - Movie Ticket Booking System Online with Java
 */
public class Main {
    public static void main (String[] args) {
        FilmManager filmManager = new FilmManager();
        filmManager.loadFilmsToList();
//        filmManager.deleteFilm();
//        filmManager.addNewFilm();
        filmManager.showAllFilms();
    }
}