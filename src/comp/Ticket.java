package comp;

import java.util.Date;
import java.time.LocalDate;

public class Ticket extends Film {

    private String username;
    private Seat seat;
    private int price;
    private Date exportDate = java.sql.Date.valueOf(LocalDate.now());


    public Ticket(String filmID, String name, String director, int duration, Date date, Seat seat, int price) {
        super(filmID, name, director, duration, date);
        this.seat = seat;
        this.price = price>0? price*1000:1000;
    }

    @Override
    public String toString() {
        return String.format("Film Name: %s,\n Director: %s,\n Duration: %s",
                name, director, 
                (duration < 60 ? duration + "m" : (duration / 60) + "h" + duration%60*60 + "m"));
    }

}
