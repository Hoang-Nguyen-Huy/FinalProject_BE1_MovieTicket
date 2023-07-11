
package user_roles;

import util.TicketManager;
import java.util.Scanner;

public class User {
    Scanner sc = new Scanner(System.in);
    protected String userName;
    protected String password;
    protected int fund;

    public User(String userName, String password, int fund) {
        this.userName = userName;
        this.password = password;
        this.fund = fund > 0 ? fund : 0;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = Math.max(fund, 0);
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



    //    public void buyTicket(String filmName) {
//        TicketManager.addTicket(this.userName, filmName);
//    }


    public void refundTicket(String filmName) {
        TicketManager.deleteTicket(this.userName, filmName);
    }

    public int getNumTickets() {
        return TicketManager.numberOfTickets(this.userName);
    }
}
