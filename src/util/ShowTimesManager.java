package util;

import comp.showTimes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.*;

public class ShowTimesManager extends HashMap<String, List<showTimes>> {
    final static String ShowTimes_URL = "src/data/showtimes.txt";
    final static String prevShowTimes_URL = "src/data/prevShowTimes.txt";
    Scanner sc = new Scanner(System.in);

    public ShowTimesManager() {
        super();
    }

    public void loadShowTimesToList() {
        TimesFileHandler loadShowTimes = new TimesFileHandler(ShowTimes_URL);
        for (String[] line : loadShowTimes.readShowTimes()) {
            String filmID = line[0];
            String theaterID = line[1];
            Date date = null;
            try {
                date = new SimpleDateFormat(FilmManager.DATE_FORMAT).parse(line[2]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int availableSeats = Integer.parseInt(line[3]);
            showTimes showTime = new showTimes(filmID, theaterID, date, availableSeats);
            this.addShowTime(showTime);
        }
        System.out.println(this.size());
    }

    public void addNewShowTime() {
        System.out.print("Enter film ID: ");
        String filmID = sc.nextLine();
        System.out.print("Enter theater ID: ");
        String theaterID = sc.nextLine();
        Date date = null;
        String iDate;
        do {
            System.out.print("Enter showtime date (dd/MM/yyyy): ");
            DateFormat f = new SimpleDateFormat(FilmManager.DATE_FORMAT);
            try {
                iDate = sc.nextLine();
                date = f.parse(iDate);
                if (!isDateValid(iDate)) {
                    throw new InvalidDateException();
                }
            } catch (InvalidDateException e) {
                System.out.println("Invalid date, try again!");
                System.gc();
            } catch (Exception e) {
                System.out.println("Wrong format, please type again!");
                System.gc();
            }
        } while (date == null);
        System.out.print("Enter available seats: ");
        int availableSeats = Integer.parseInt(sc.nextLine());

        showTimes showTime = new showTimes(filmID, theaterID, date, availableSeats);
        this.addShowTime(showTime);
        storeNewShowTime(showTime);
    }

    public void showAllShowTimes() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            System.out.println("Show Times list:");
            this.forEach((filmID, showTimes) -> {
                System.out.println("Film ID: " + filmID);
                for (showTimes showTime : showTimes) {
                    System.out.println("\tTheater ID: " + showTime.getTheaterID());
                    System.out.println("\tDate: " + new SimpleDateFormat(FilmManager.DATE_FORMAT).format(showTime.getDate()));
                    System.out.println("\tAvailable Seats: " + showTime.getAvailableSeats());
                }
            });
        }
    }

    public void updateShowTime() {
        System.out.println("-------------------------");
        System.out.print("Enter the film ID of the showtime you want to update: ");
        String filmID = sc.nextLine();
        if (!this.containsKey(filmID)) {
            System.out.println("Film ID not found!");
            return;
        }

        List<showTimes> showTimes = this.get(filmID);
        System.out.println("Show Times for Film ID " + filmID + ":");
        for (int i = 0; i < showTimes.size(); i++) {
            System.out.println("Show Time " + (i + 1));
            System.out.println("\tTheater ID: " + showTimes.get(i).getTheaterID());
            System.out.println("\tDate: " + new SimpleDateFormat(FilmManager.DATE_FORMAT).format(showTimes.get(i).getDate()));
            System.out.println("\tAvailable Seats: " + showTimes.get(i).getAvailableSeats());
        }

        System.out.print("Enter the index of the showtime you want to update: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        if (index < 0 || index >= showTimes.size()) {
            System.out.println("Invalid index!");
            return;
        }

        ShowTime showTimeToUpdate = showTimes.get(index);
        System.out.println("Which information do you want to update?");
        System.out.println("1. Theater ID");
        System.out.println("2. Date");
        System.out.println("3. Available Seats");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter the new theater ID: ");
                String newTheaterID = sc.nextLine();
                showTimeToUpdate.setTheaterID(newTheaterID);
                break;
            case 2:
                System.out.print("Enter the new date (dd/MM/yyyy): ");
                String newDateStr = sc.nextLine();
                DateFormat dateFormat = new SimpleDateFormat(FilmManager.DATE_FORMAT);
                try {
                    Date newDate = dateFormat.parse(newDateStr);
                    showTimeToUpdate.setDate(newDate);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. The date will not be updated.");
                }
                break;
            case 3:
                System.out.print("Enter the new available seats: ");
                int newAvailableSeats = Integer.parseInt(sc.nextLine());
                showTimeToUpdate.setAvailableSeats(newAvailableSeats);
                break;
            default:
                System.out.println("Invalid choice. No information will be updated.");
        }

        storeAllShowTimes();
        System.out.println("Showtime updated successfully!");
    }

    public void deleteShowTime() {
        System.out.print("Enter the film ID of the showtime you want to delete: ");
        String filmID = sc.nextLine();
        if (!this.containsKey(filmID)) {
            System.out.println("Film ID not found!");
            return;
        }

        List<showTimes> showTimes = this.get(filmID);
        System.out.println("Show Times for Film ID " + filmID + ":");
        for (int i = 0; i < showTimes.size(); i++) {
            System.out.println("Show Time " + (i + 1));
            System.out.println("\tTheater ID: " + showTimes.get(i).getTheaterID());
            System.out.println("\tDate: " + new SimpleDateFormat(FilmManager.DATE_FORMAT).format(showTimes.get(i).getDate()));
            System.out.println("\tAvailable Seats: " + showTimes.get(i).getAvailableSeats());
        }

        System.out.print("Enter the index of the showtime you want to delete: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        if (index < 0 || index >= showTimes.size()) {
            System.out.println("Invalid index!");
            return;
        }

        ShowTime deletedShowTime = showTimes.remove(index);
        storeAllShowTimes();
        System.out.println("Showtime deleted successfully!");
    }

    private void addShowTime(showTimes showTime) {
        String filmID = showTime.getFilmID();
        if (this.containsKey(filmID)) {
            List<showTimes> showTimes = this.get(filmID);
            showTimes.add(showTime);
        } else {
            List<showTimes> showTimes = new ArrayList<>();
            showTimes.add(showTime);
            this.put(filmID, showTimes);
        }
    }

    private void storeNewShowTime(showTimes showTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ShowTimes_URL, true))) {
            String filmID = showTime.getFilmID();
            String theaterID = showTime.getTheaterID();
            String dateStr = new SimpleDateFormat(FilmManager.DATE_FORMAT).format(showTime.getDate());
            int availableSeats = showTime.getAvailableSeats();

            String line = filmID + "," + theaterID + "," + dateStr + "," + availableSeats;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void storeAllShowTimes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ShowTimes_URL))) {
            for (Map.Entry<String, List<showTimes>> entry : this.entrySet()) {
                String filmID = entry.getKey();
                List<showTimes> showTimes = entry.getValue();
                for (showTimes showTime : showTimes) {
                    String theaterID = showTime.getTheaterID();
                    String dateStr = new SimpleDateFormat(FilmManager.DATE_FORMAT).format(showTime.getDate());
                    int availableSeats = showTime.getAvailableSeats();

                    String line = filmID + "," + theaterID + "," + dateStr + "," + availableSeats;
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(FilmManager.DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
