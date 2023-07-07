package user_roles;

import util.TicketManager;

public class User {
    protected String userName;
    protected String password;
<<<<<<< HEAD

=======
>>>>>>> 53bb5336d225469356a5f2a07d3b890e33a2392d
    protected int fund;

    public User(String userName, String password, int fund) {
        this.userName = userName;
        this.password = password;
<<<<<<< HEAD
        this.fund = fund>0?fund:1000;

=======
        this.fund = fund > 0 ? fund : 1000;
>>>>>>> 53bb5336d225469356a5f2a07d3b890e33a2392d
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

<<<<<<< HEAD

    //    public void buyTicket(String filmName) {
//        TicketManager.addTicket(this.userName, filmName);
//    }

=======
>>>>>>> 53bb5336d225469356a5f2a07d3b890e33a2392d
    public void refundTicket(String filmName) {
        TicketManager.deleteTicket(this.userName, filmName);
    }

    public int getNumTickets() {
        return TicketManager.numberOfTickets(this.userName);
    }
}
