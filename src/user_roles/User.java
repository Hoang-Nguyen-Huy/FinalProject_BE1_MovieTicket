package user_roles;
import java.util.List;

import com.Ticket;

public class User {
    private String userName;
    private String password;
    private List<Ticket> tickets;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public int getNumTickets() {
        return tickets.size();
    }
}
