package comp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class showTimes {
    private static final String filmFile = "src/data/film.txt";

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
                    int choice = getUserChoice();
                    handleUserChoice(choice);
                    return;
                }
            }
            System.out.println("Film ID not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        return choice;
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You selected 9am show time.");
                break;
            case 2:
                System.out.println("You selected 2pm show time.");
                break;
            case 3:
                System.out.println("You selected 7pm show time.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}
