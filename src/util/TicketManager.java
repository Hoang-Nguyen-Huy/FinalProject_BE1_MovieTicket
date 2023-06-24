package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.Ticket;

public class TicketManager {
    private Map<String, List<Ticket>> ticketList = new HashMap<>();

    TicketManager() {
    }

    public void addTicket(String username,Ticket t) {
        // BufferedWriter bw = null;
		// FileWriter fw = null; 
        ticketList.get(username).add(t);
    }

    public void delete(String username, Ticket t) {
        ticketList.get(username).remove(t);
    }

    public void showTicket() {
        for (Ticket t : ticketList) {
            System.out.println(t);
        }
    }
}
