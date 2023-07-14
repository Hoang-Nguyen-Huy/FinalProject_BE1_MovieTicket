package ui;

import util.AccountManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends ArrayList<String> {
    Scanner sc = new Scanner(System.in);
    public Menu() {
        super();
        welcomeMenu();
    } //default constructors

    public int getInteger() {
        return Integer.parseInt(sc.nextLine());
    }
    public void welcomeMenu() {
        this.clear();
        this.add("\t\t\tWelcome to Movie Ticket Booking System Online");
        this.add("\t1. Login.");
        this.add("\t2. Register.");
        this.add("\t0. Exit.");
        this.add("Input your choice: ");
    }

    public int checkAccountBalance(AccountManager accountManager, String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(AccountManager.fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4 && data[1].equals(username)) {
                    int fund = Integer.parseInt(data[3].trim());
                    if (fund == 0) {
                        return 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void userMenu() {
        this.clear();
        this.add("\t1. Buy Ticket.");
        this.add("\t2. Show Your Ticket.");
        this.add("\t3. Remove Ticket (user get back 50% ticket price).");
        this.add("\t0.Exit.");
    }

    public void adminMenu() {
        this.clear();

        this.add("Adjust Movie:");
        this.add("\t1. Add new movie.");
        this.add("\t2. Show all movie.");
        this.add("\t3. Update movie.");
        this.add("\t4. Delete Movie");
        this.add("Adjust Show Times:");
        this.add("\t5. Create New Show.");
        this.add("\t6. Show all shows.");
        this.add("\t7. Update show times.");
        this.add("\t8. Delete show.");
        this.add("\t0.Exit.");
    }
    public int getChoice() {
        System.out.print("Input your choice:");
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }


    public void printAll() {
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            System.out.println("Menu:");
            for (String m : this)
                System.out.println(m);
        }
    }

    public String getUsername() {
        System.out.print("Input your username: ");
        return sc.nextLine();
    }

    public String getPassword() {
        System.out.print("Input your password: ");
        return sc.nextLine();
    }


}
