package com;

import java.sql.Date;

public class Ticket extends Film {
    String username;
    int price;
    Date exportDate;
    public Ticket(String filmID, String name, String author, String studio, int duration, Date date, int price, Date exportDate) {
        super(filmID, name, author, studio, duration, date);
        this.price = price;
        this.exportDate = exportDate;
    }
    @Override
    public String toString() {
        return String.format("Film Name: %s,\n Author: %s,\n Duration: %s", null);
    }
    
}
