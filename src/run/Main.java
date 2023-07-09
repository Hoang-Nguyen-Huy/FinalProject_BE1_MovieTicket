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
        // Hiển thị ma trận chỗ ngồi ban đầu
        TicketManager.showSeatMatrix();


        // Chọn chỗ ngồi cho user
        TicketManager.chooseSeat("user1", "Film A", 1, 2);
        TicketManager.chooseSeat("user1", "Film B", 3, 4);
        TicketManager.chooseSeat("user1", "Film C", 2, 6);

        // Hiển thị ma trận chỗ ngồi sau khi có người chọn
        TicketManager.showSeatMatrix();

        // Hiển thị số vé của user1
        System.out.println("Number of tickets for user1: " + TicketManager.numberOfTickets("user1"));

        // Xóa vé của user1 cho Film A
        TicketManager.deleteTicket("user1", "Film A");

        // Hiển thị ma trận chỗ ngồi sau khi xóa vé
        TicketManager.showSeatMatrix();

        // Hiển thị số vé của user1 sau khi xóa vé
        System.out.println("Number of tickets for user1: " + TicketManager.numberOfTickets("user1"));
    }
}