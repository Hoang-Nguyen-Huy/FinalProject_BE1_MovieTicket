package run;


import ui.Menu;
import util.AccountManager;
import util.FilmManager;
import util.TextFileHandler;

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

            TextFileHandler fileHandler;
            fileHandler = new TextFileHandler("data/film.txt");
            films = (FilmManager) fileHandler.readFilms();
            System.out.println(films);
//            switch (choice) {
//                case 1 -> {
//                    if (accounts.loginAccount(menu.getUsername(), menu.getPassword())) {
//                        System.out.println("Login Successfully!");
//
//                    } else {
//                        System.out.println("Wrong username or password! Try again or Register.");
//                    }
//                }
//                case 2 -> accounts.registerNewUser();
//
////                    String username, password;
////                    do {
////                        username = menu.getUsername();
////                        password = menu.getPassword();
////                    } while (!accounts.isUserExists(username));
////                    accounts.registerAccount(username, password);
//
//            }
//


        } while (choice != 0);

    }
}