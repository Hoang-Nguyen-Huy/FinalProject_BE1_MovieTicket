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
        this.clear();
        this.add("\t\t\tWelcome to Movie Ticket Booking System Online");
        this.add("\t1. Login.");
        this.add("\t2. Register.");
        this.add("\t0. Exit.");
        this.add("Input your choice: ");
    }
    public void emptyBalance(int balance) {
        if (balance == 0) {
            System.out.println("Please deposit money to buy ticket!");
            System.out.print("Input an amount you want to deposit: ");
        }
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
    public int adminChoice() {
        System.out.println("\t\t\tADMIN MENU");
        System.out.println("\t1. Movie");
        System.out.println("\t2. ShowTimes");
        System.out.println("\t3. Edit ticket price");
        System.out.println("\t0. Exit");
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
}
