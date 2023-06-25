package com;

import java.sql.Date;
import java.time.LocalDate;

public class Ticket extends Film {

    String username;
    String seat;
    int price;
    Date exportDate = java.sql.Date.valueOf(LocalDate.now());

    public Ticket(String filmID, String name, String author, String studio, int duration, Date date, String seat, int price) {
        super(filmID, name, author, studio, duration, date);
        this.seat = seat;
        this.price = price;
        //exportDate auto generate at the time buying ticket.
    }

    @Override
    public String toString() {
        return String.format("Film Name: %s,\n Author: %s,\n Duration: %s",
                name, author, (duration < 60 ? duration : (duration / 60) + "h"));
    }

}
