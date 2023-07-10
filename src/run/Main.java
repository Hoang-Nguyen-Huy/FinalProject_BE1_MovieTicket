
package run;

import ui.Menu;
import user_roles.Admin;
import user_roles.User;
import util.FilmManager;
import util.TicketManager;
import comp.showTimes;
import comp.Ticket;
import comp.Film;

import java.text.ParseException;

/*
Nguyễn Huy Hoàng
Lê Quang Trung
Final Project BE1 - Movie Ticket Booking System Online with Java
 */
public class Main {
    public static void main (String[] args) {
        /*
        // Hiển thị ma trận chỗ ngồi ban đầu
        TicketManager.showSeatMatrix();

        // Chọn chỗ ngồi cho các người dùng
        chooseSeatsForUser("user1", "Film A", 1, 2);
        chooseSeatsForUser("user2", "Film B", 3, 4);
        chooseSeatsForUser("user3", "Film C", 2, 6);

        // Hiển thị ma trận chỗ ngồi sau khi có người chọn
        TicketManager.showSeatMatrix();

        // Hiển thị số vé của từng người dùng
        printNumberOfTicketsForUser("user1");
        printNumberOfTicketsForUser("user2");
        printNumberOfTicketsForUser("user3");

        // Xóa vé của user1 cho Film A
        TicketManager.deleteTicket("user1", "Film A");

        // Hiển thị ma trận chỗ ngồi sau khi xóa vé
        TicketManager.showSeatMatrix();

        // Hiển thị số vé của từng người dùng sau khi xóa vé
        printNumberOfTicketsForUser("user1");
        printNumberOfTicketsForUser("user2");
        printNumberOfTicketsForUser("user3");
    */

        showTimes.displayShowTimes("f001");
    }
        /*
    private static void chooseSeatsForUser(String username, String filmName, int row, int col) {
        TicketManager.chooseSeat(username, filmName, row, col);
        System.out.println(username + " has chosen seat (" + row + ", " + col + ") for " + filmName);
    }

    private static void printNumberOfTicketsForUser(String username) {
        int numberOfTickets = TicketManager.numberOfTickets(username);
        System.out.println(username + " has " + numberOfTickets + " ticket(s).");
    }

         */
}