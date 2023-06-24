package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Ticket;

public class TicketManager {
    private Map<String, List<Ticket>> ticketList = new HashMap<>();

    TicketManager() {
    }

    public void addTicket(String username,Ticket newTicket) {
        // BufferedWriter bw = null;
		// FileWriter fw = null; 
        ticketList.get(username).add(newTicket);
    }

    public void deleteTicket(String username, Ticket deleteTicket) {
        ticketList.get(username).remove(deleteTicket);
    }

    public void showTicket(String username) {
        for (Ticket t : ticketList.get(username)) {
            System.out.println(t);
        }
    }
}
