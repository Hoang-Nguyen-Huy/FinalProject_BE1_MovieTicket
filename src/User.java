import java.util.List;
import java.util.ArrayList;

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

     public void refund (boolean returnTicket) {   //true nếu người dùng có yêu cầu trả vé, false ngược lại
        if (returnTicket) {
            if (tickets.size() > 0) {  //kiểm tra xem trong danh mục mua của người này đã có vé hay chưa
                int refundAmount = 0.5 * totalPrice; // với totalPrice là giá tiền của vé
                Systeml.out.println("Refund successfully! You received " + refundAmount + " money back.");

            } else {
                System.out.println("You have not purchased any tickets.");
            }
        } else {
            System.out.println("No ticket to return.");
        }
     }


}
