package util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import comp.Film;

class InvalidDateException extends Exception {
}

public class FilmManager extends HashMap<String, Film> {
    public static String DATE_FORMAT = "dd/MM/yyyy";
    DateFormat f = new SimpleDateFormat(DATE_FORMAT);
    final static String Film_URL = "src/data/film.txt";
    final static String prevFilm_URL = "src/data/prevFilm.txt";
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
        System.out.println("-------------------------");
        TextFileHandler filmFileHandler = new TextFileHandler(Film_URL);
        List<String[]> filmData = filmFileHandler.readFilms();
        // in ra danh sach cac bo phim dang chieu
        for (String[] line : filmData) {
            String filmID = line[0];
            String name = line[1];
            String director = line[2];
            int duration = Integer.parseInt(line[3].trim());
            Date date = null;
            try {
                date = new SimpleDateFormat(DATE_FORMAT).parse(line[4]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int price = Integer.parseInt(line[5].trim());

            System.out.println("Film ID: " + filmID);
            System.out.println("Film name: " + name);
            System.out.println("Director: " + director);
            System.out.println("Duration: " + duration);
            System.out.println("Date: " + new SimpleDateFormat("dd//MM//yyyy").format(date));
            System.out.println("Price: $" + price);
            System.out.println("-------------------------");
        }

        System.out.print("Enter the film ID you want to update: ");
        String filmIDToUpdate = sc.nextLine();
        // bat dau hoi muon update thong tin nao
        for (String[] line : filmData) {
            String filmID = line[0];
            if (filmID.equals(filmIDToUpdate)) {
                System.out.println("Which information do you want to update?");
                System.out.println("1. Film Name");
                System.out.println("2. Director");
                System.out.println("3. Duration");
                System.out.println("4. Date");
                System.out.println("5. Price");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the new film name: ");
                        String newName = sc.nextLine();
                        line[1] = newName;
                        break;
                    case 2:
                        System.out.print("Enter the new director: ");
                        String newDirector = sc.nextLine();
                        line[2] = newDirector;
                        break;
                    case 3:
                        System.out.print("Enter the new duration: ");
                        int newDuration = Integer.parseInt(sc.nextLine());
                        line[3] = String.valueOf(newDuration);
                        break;
                    case 4:
                        System.out.print("Enter the new date (dd/MM/yyyy): ");
                        String newDateStr = sc.nextLine();
                        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                        try {
                            Date newDate = dateFormat.parse(newDateStr);
                            line[4] = dateFormat.format(newDate);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. The date will not be updated.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter the new price: ");
                        int newPrice = Integer.parseInt(sc.nextLine());
                        line[5] = String.valueOf(newPrice);
                        break;
                    default:
                        System.out.println("Invalid choice. No information will be updated.");
                }
                break;
            }
        }
        // Write the updated film data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Film_URL))) {
            for (String[] line : filmData) {
                String updatedLine = String.join(",", line);
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Film updated successfully!");

    }
}
