package util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

import comp.Film;

class InvalidDateException extends Exception {
}

public class FilmManager extends HashMap<String, Film> {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    DateFormat f = new SimpleDateFormat(DATE_FORMAT);
    final static String fileURL = "src\\data\\film.txt";

    Scanner sc = new Scanner(System.in);
    TextFileHandler loadFilm = new TextFileHandler(fileURL);

    public FilmManager() {
        super();
    }

    public void loadFilmsToList() {

        for (String[] line : loadFilm.readFilms()) {    //go through every line in text
            for (int i=0; i<5; i++) {
                String filmID = line[0];
                String name = line[1];
                String director = line[2];
                int duration = Integer.parseInt(line[3]);
                Date date = null;
                try {
                    date = f.parse(line[4]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Film temp = new Film(filmID, name, director, duration, date);
                this.put(filmID, temp);
            }
        }
        System.out.println(this.size());

    }

    private boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void addNewFilm() {
        System.out.print("Enter film name: ");
        String filmName = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter film's length in minute(s): ");
        int duration = Integer.parseInt(sc.nextLine());
        Date date = null;
        do {
            System.out.print("Enter premier date: ");
            DateFormat f = new SimpleDateFormat(DATE_FORMAT);
            try {
                String iDate = sc.nextLine();
                if (!iDate.matches(DATE_FORMAT)) {
                    throw new Exception();
                }
                if (!isDateValid(iDate)) {
                    throw new InvalidDateException();
                }
                date = f.parse(iDate);
            } catch (InvalidDateException y) {
                System.out.println("Invalid date, try again!");
                System.gc();
            } catch (Exception e) {
                System.out.println("Wrong format, please type again!");
                System.gc();
            }
        } while (date == null);

        Film newFilm = new Film(filmName, filmName, author, duration, date);

        this.put(newFilm.getFilmId(), newFilm);
    }

    public void deleteFilm() {
        System.out.print("Enter Film ID that you wanna delete: ");
        String filmID = sc.nextLine();
        this.remove(filmID);
    }

    public void showAllFilms() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            System.out.println("Film list:");
            this.forEach((k, v) -> {
                System.out.println("\t" + v);
            });
        }
    }

}
