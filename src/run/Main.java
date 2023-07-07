package run;

import comp.Film;
import ui.Menu;
import user_roles.Admin;
import user_roles.User;
import util.AccountManager;
import util.FilmManager;
import util.TicketManager;

import java.text.ParseException;

/*
Nguyễn Huy Hoàng
Lê Quang Trung
Final Project BE1 - Movie Ticket Booking System Online with Java
 */
public class Main {
    public static void main (String[] args) {
        int choice;
        do {
            Menu menu = new Menu();
            AccountManager accounts = new AccountManager();
            FilmManager films = new FilmManager();
//        TicketManager tickets = new TicketManager();
            menu.welcomeMenu();

            menu.printAll();
            choice = menu.getInteger();

            switch (choice) {
                case 1:
                    if (accounts.checkAccount(menu.getUsername(), menu.getPassword())) {
                        System.out.println("Login Successfully!");
                    } else {
                        System.out.println("Wrong username or password! Try again or Register.");
                    }
                    break;
                case 2:

                    break;
            }

        } while (choice != 0);

    }
}