package util;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.Film;

class InvalidDateException extends Exception {
}

public class FilmManager {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    DateFormat f = new SimpleDateFormat(DATE_FORMAT);
    Scanner sc = new Scanner(System.in);
    Map<String, Film> films = new HashMap<>();

    public FilmManager() {}

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
        System.out.print("Enter studio's name: ");
            String studio = sc.nextLine();
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
        } while (date==null);  
            
        Film newFilm = new Film(filmName, filmName, author, studio, duration, date);
        
        films.put(newFilm.getFilmId(), newFilm);
    }

    public void deleteFilm() {
        System.out.print("Enter Film ID that you wanna delete: ");
        String filmID = sc.nextLine();
        films.remove(filmID);
    }
    
    public void showAllFilms() {
        films.forEach( (k,v) -> {
            System.out.println("FilmID: " + k + "\nFilm Information: " + v);
        });
    }
}
