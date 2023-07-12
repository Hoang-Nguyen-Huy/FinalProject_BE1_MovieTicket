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
    public static String DATE_FORMAT = "dd/MM/yyyy";
    DateFormat f = new SimpleDateFormat(DATE_FORMAT);
    final static String Film_URL = "src\\data\\film.txt";
    final static String prevFilm_URL = "src\\data\\prevFilm.txt";
    Scanner sc = new Scanner(System.in);


    public FilmManager() {
        super();
    }

    public void loadFilmsToList() {
        TextFileHandler loadFilm = new TextFileHandler(Film_URL);
        for (String[] line : loadFilm.readFilms()) {    //go through every line in text
            for (int i=0; i<6; i++) {
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
                int price = Integer.parseInt(line[5]);
                Film temp = new Film(filmID, name, director, duration, date, price);
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
        int counter = this.size() + 1;
        String filmID = String.format("f%03d", counter);
        while (this.containsKey(filmID)) {
            filmID = String.format("f%03d", ++counter);
        }
        System.out.print("Enter film name: ");
        String filmName = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter film's length in minute(s): ");
        int duration = Integer.parseInt(sc.nextLine());
        Date date = null;
        String iDate;
        do {
            System.out.print("Enter premier date: ");
            DateFormat f = new SimpleDateFormat(DATE_FORMAT);
            try {
                iDate = sc.nextLine();
                date = f.parse(iDate);
                if (!isDateValid(iDate)) {
                    throw new InvalidDateException();
                }

            } catch (InvalidDateException y) {
                System.out.println("Invalid date, try again!");
                System.gc();
            } catch (Exception e) {
                System.out.println("Wrong format, please type again!");
                System.gc();
            }
        } while (date == null);
        System.out.print("Enter film ticket price: ");
        int price = Integer.parseInt(sc.nextLine());

        Film newFilm = new Film(filmID, filmName, author, duration, date, price);
        this.put(filmID, newFilm);
        storeNewFilm(this, filmID);
    }

    public void deleteFilm() {
        System.out.print("Enter Film ID that you wanna delete: ");
        String filmID = sc.nextLine();
        if (isFilmExist(filmID)) {
            storeOldFilm(this, filmID);
            this.remove(filmID);
        } else {
            System.out.println("Wrong ID or film not exist!");
        }

    }

    public boolean isFilmExist(String filmID) {
        return this.containsKey(filmID);
    }
    public void storeOldFilm(FilmManager filmList, String filmID) {
        TextFileHandler storePrevFilm = new TextFileHandler(prevFilm_URL);
        storePrevFilm.writeFilm(this, filmID);
    }

    public void storeNewFilm(FilmManager filmList, String filmID) {
        TextFileHandler storeNewFilm = new TextFileHandler(Film_URL);
        storeNewFilm.writeFilm(this, filmID);
    }

    public void reloadFilms() {

    }

    public void showAllFilms() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            System.out.println("Film list:");
            this.forEach((k, v) -> {
                System.out.println("\tid: "+ k + " " + v );
            });
        }
    }

    public void adjustFilmPrice() {
        System.out.print("Input film ID you want to adjust: ");
        String filmID = sc.nextLine();
        System.out.print("Input new price: ");
        int newPrice = Integer.parseInt(sc.nextLine());
        for (String id: this.keySet()) {
            if (id.equals(filmID)) {
                this.get(id).setPrice(newPrice);
                System.out.println("Adjust price successfully");
                return;
            }
        }
        System.out.println("Wrong film ID or getting error!");
    }

    public void updateFilm() {
        // show các thành phần và hỏi xem cần thay đổi gì
        // rồi set lại giá trị
    }
}
