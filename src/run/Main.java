package run;

import ui.Menu;
import user_roles.Admin;
import user_roles.User;
import util.FilmManager;
import util.TicketManager;

import java.text.ParseException;

/*
Nguyễn Huy Hoàng
Lê Quang Trung
Final Project BE1 - Movie Ticket Booking System Online with Java
 */
public class Main {
    public static void main (String[] args) {
        TicketManager.showSeatMatrix();

        // Chọn chỗ ngồi cho người dùng
        TicketManager.chooseSeat("user1", "Film 1", 2, 3);
        TicketManager.chooseSeat("user2", "Film 2", 1, 5);
        TicketManager.chooseSeat("user1", "Film 3", 4, 8);

        // Hiển thị lại ma trận chỗ ngồi sau khi đã chọn
        TicketManager.printSeatMatrix();

        // Xóa vé của người dùng
        TicketManager.deleteTicket("user1", "Film 1");

        // Hiển thị lại ma trận chỗ ngồi sau khi đã xóa vé
        TicketManager.printSeatMatrix();

        // Số lượng vé của người dùng
        int numberOfTickets = TicketManager.numberOfTickets("user1");
        System.out.println("Number of tickets for user1: " + numberOfTickets);
    }
}