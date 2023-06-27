package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp.Ticket;

public class TicketManager {
    static Map<String, List<Ticket>> ticketList = new HashMap<>();

    TicketManager() {
    }

//    public static void addTicket(String username, String filmName) {
////        for (Film f : filmsList) {
////
////        }
//        Ticket newTicket = new Ticket();
//        ticketList.get(username).add(newTicket);
//    }

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
