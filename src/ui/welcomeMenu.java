package ui;

import java.util.ArrayList;
import java.util.List;

public class welcomeMenu {
    List<String> welcomeMenu = new ArrayList<>();
    public welcomeMenu(List<String> welcomeMenu) {
        this.welcomeMenu = welcomeMenu;
        welcomeMenu.add("Movie Ticket Booking System Online with Java");
        welcomeMenu.add("1. Login");
        welcomeMenu.add("2. Register");
    }
    
    
}
