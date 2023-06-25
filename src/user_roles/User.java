package user_roles;
import java.util.List;

import util.TicketManager;

public class User {
    private String userID;
    private String userName;
    private String password;
    private int role = 0;
    private int fund;

    public User(String userID, String userName, String password, int fund) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fund = fund>0?fund:0;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

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

    public void buyTicket(String filmName) {
        TicketManager.addTicket(this.userName, filmName);
    }

    public void refundTicket(String filmName) {
        TicketManager.deleteTicket(this.userName, filmName);
    }

    public int getNumTickets() {
        return TicketManager.numberOfTickets(this.userName);
    }
}
