package util;

import comp.showTimes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.*;
import java.text.ParseException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class ShowTimesManager extends HashMap<String, List<showTimes>> {
    final static String ShowTimes_URL = "src/data/showtimes.txt";
    public static String DATE_FORMAT = "dd/MM/yyyy";

    final static String prevShowTimes_URL = "src/data/prevShowTimes.txt";

    final static String Film_URL = "src/data/film.txt";
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
        System.out.println("-------------------------");
        TimesFileHandler timesFileHandler = new TimesFileHandler(ShowTimes_URL);
        List<String[]> timesData = timesFileHandler.readShowTimes();
        for (String[] line : timesData) {
            String filmID = line[0];
            String theaterID = line[1];
            Date date = null;
            try {
                date = new SimpleDateFormat(DATE_FORMAT).parse(line[2]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int seats = Integer.parseInt(line[3].trim());
            System.out.println("Film ID: " + filmID);
            System.out.println("Theater ID: " + theaterID);
            System.out.println("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(date));
            System.out.println("Available Seats: " + seats);
            System.out.println("-------------------------");
        }
    }

    public void updateShowTime() {
        System.out.print("Enter the film ID of the showtime you want to update: ");
        String filmID = sc.nextLine();

        System.out.print("Enter the theater ID of the showtime you want to update: ");
        String theaterID = sc.nextLine();

        TimesFileHandler timesFileHandler = new TimesFileHandler(ShowTimes_URL);
        List<String[]> timesData = timesFileHandler.readShowTimes();
        boolean foundShowTime = false;

        for (String[] line : timesData) {
            String currentFilmID = line[0];
            String currentTheaterID = line[1];

            if (currentFilmID.equals(filmID) && currentTheaterID.equals(theaterID)) {
                foundShowTime = true;

                System.out.println("Show Time Found:");
                System.out.println("Film ID: " + currentFilmID);
                System.out.println("Theater ID: " + currentTheaterID);
                System.out.println("Date: " + line[2]);
                System.out.println("Available Seats: " + line[3]);

                System.out.println("Which information do you want to update?");
                System.out.println("1. Date");
                System.out.println("2. Available Seats");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the new date (dd/MM/yyyy): ");
                        String newDateStr = sc.nextLine();
                        if (isDateValid(newDateStr)) {
                            line[2] = newDateStr;
                            System.out.println("Date updated successfully.");
                        } else {
                            System.out.println("Invalid date format. Date will not be updated.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter the new number of available seats: ");
                        int newSeats = Integer.parseInt(sc.nextLine());
                        line[3] = String.valueOf(newSeats);
                        System.out.println("Available seats updated successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. No information will be updated.");
                }
                break;
            }
        }

        if (!foundShowTime) {
            System.out.println("Show Time with Film ID " + filmID + " and Theater ID " + theaterID + " not found.");
        } else {
            // Write the updated showtimes data back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ShowTimes_URL))) {
                for (String[] line : timesData) {
                    String updatedLine = String.join(",", line);
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Show times updated successfully!!!");
        }
    }


    public void deleteShowTime() {
        System.out.print("Enter the film ID of the showtime you want to delete: ");
        String filmID = sc.nextLine();

        System.out.print("Enter the theater ID of the showtime you want to delete: ");
        String theaterID = sc.nextLine();

        TimesFileHandler timesFileHandler = new TimesFileHandler(ShowTimes_URL);
        List<String[]> timesData = timesFileHandler.readShowTimes();
        boolean foundShowTime = false;

        for (Iterator<String[]> iterator = timesData.iterator(); iterator.hasNext();) {
            String[] line = iterator.next();
            String currentFilmID = line[0];
            String currentTheaterID = line[1];

            if (currentFilmID.equals(filmID) && currentTheaterID.equals(theaterID)) {
                foundShowTime = true;
                iterator.remove();
                System.out.println("Showtime deleted successfully!");
                break;
            }
        }

        if (!foundShowTime) {
            System.out.println("Show Time with Film ID " + filmID + " and Theater ID " + theaterID + " not found.");
        } else {
            // Write the updated showtimes data back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ShowTimes_URL))) {
                for (String[] line : timesData) {
                    String updatedLine = String.join(",", line);
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void seatsAfterPurchased(String filmID, String theater) {
        String tempFileName = "temp.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(ShowTimes_URL));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(filmID) && data[1].equals(theater)) {
                    int availableSeats = Integer.parseInt(data[3].trim());
                    int updatedSeats = availableSeats - 1;
                    line = data[0] + "," + data[1] + "," + data[2] + "," + updatedSeats;
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Xóa tệp gốc và đổi tên tệp tạm thành tên tệp gốc
        File originalFile = new File(ShowTimes_URL);
        originalFile.delete();

        File tempFile = new File(tempFileName);
        tempFile.renameTo(originalFile);
    }



    public void displayShowTimes(String filmID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Film_URL))) {
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

    public static String handleUserChoice(int choice) {
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

    public void seatsAfterRefund(String filmID, String theater) {
        String tempFileName = "temp.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(ShowTimes_URL));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(filmID) && data[1].equals(theater)) {
                    int availableSeats = Integer.parseInt(data[3].trim());
                    int updatedSeats = availableSeats + 1;
                    line = data[0] + "," + data[1] + "," + data[2] + "," + updatedSeats;
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Xóa tệp gốc và đổi tên tệp tạm thành tên tệp gốc
        File originalFile = new File(ShowTimes_URL);
        originalFile.delete();

        File tempFile = new File(tempFileName);
        tempFile.renameTo(originalFile);
    }
}
