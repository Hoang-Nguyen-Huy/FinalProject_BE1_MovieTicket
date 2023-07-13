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


    public void displayShowTimes(String filmID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filmFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filmData = line.split(",");
                if (filmData[0].equals(filmID)) {
                    System.out.println("Show Times for Film: " + filmData[1]);
                    System.out.println("1. 9am");
                    System.out.println("2. 2pm");
                    System.out.println("3. 7pm");
                    return;
                }
            }
            System.out.println("Film Name not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter show times above (1-3): ");
        int choice = scanner.nextInt();
        return choice;
    }

    public String handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                return "9am";
            case 2:
                return "2pm";
            case 3:
                return "7pm";
            default:
                return "Invalid choice";
        }
    }
}
