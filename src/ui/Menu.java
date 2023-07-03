package ui;

import util.AccountManager;

import java.util.ArrayList;
import java.util.Scanner;

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
        this.add("Welcome to Movie Ticket Booking System Online");
        this.add("1. Login.");
        this.add("2. Register.");
        this.add("0. Exit.");
        this.add("Input your choice: ");
    }


    public void userMenu() {

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
}
