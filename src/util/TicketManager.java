package util;

import util.FilmManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Film;
import com.Ticket;

public class TicketManager {
    static Map<String, List<Ticket>> ticketList = new HashMap<>();

    TicketManager() {
    }

    public static void addTicket(String username, String filmName) {
        for (Film f : filmsLis t) {

        }
        // Ticket newTicket = new Ticket();
        ticketList.get(username).add(newTicket);
    }

    public static void deleteTicket(String username, String filmName) {
        ticketList.get(username).removeIf(p -> p.getName()
                        .equalsIgnoreCase(filmName)
        );
    }

    public static int numberOfTickets(String username) {
        return ticketList.get(username).size();
    }

    public static void showTicket(String username) {
        for (Ticket t : ticketList.get(username)) {
            System.out.println(t);
        }
    }
}
