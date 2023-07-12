package comp;

import util.TicketManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class showTimes {
    private static final String filmFile = "src/data/film.txt";

    public void displayShowTimes(String filmName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filmFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filmData = line.split(",");
                if (filmData[1].equals(filmName)) {
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
