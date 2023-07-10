
package comp;

import java.util.Date;
import java.time.LocalDate;

public class Ticket extends Film {

    String username;
    String seat;

    Date exportDate = java.sql.Date.valueOf(LocalDate.now());
    String showTimes;

    public Ticket(String filmID, String name, String director, int duration, Date date, String seat, int price, String showTimes) {
        super(filmID, name, director, duration, date);
        this.seat = seat;
        this.price = price>0? price*1000:1000;
        this.showTimes = showTimes;
        //exportDate auto generate at the time buying ticket.
    }

    public String getSeat () {
        return seat;
    }
    public String getShowTimes () {
        return showTimes;
    }

    @Override
    public String toString() {
        return String.format("Film Name: %s,\n Director: %s,\n Duration: %s, \n Show Times: %s",
                name, director, 
                (duration < 60 ? duration + "m" : (duration / 60) + "h" + duration%60*60 + "m"), showTimes);
    }
    
}
