package util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp.Ticket;

public class TicketManager {
    private static Map<String, List<Ticket>> ticketList = new HashMap<>();
    private static char[][] seatMatrix = new char[5][11];
    private static final String seatMatrixFileName = "src/data/seat.txt";

    static {
        loadSeatMatrixFromFile();
    }

    private static void loadSeatMatrixFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(seatMatrixFileName))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < seatMatrix[row].length; col++) {
                    seatMatrix[row][col] = line.charAt(col * 4 + 3);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Thay đổi trạng thái ban đầu khi chưa có ai đặt vé
        for (int row = 0; row < seatMatrix.length; row++) {
            for (int col = 0; col < seatMatrix[row].length; col++) {
                if (seatMatrix[row][col] != 'x') {
                    seatMatrix[row][col] = Character.forDigit(col, 10);
                }
            }
        }
    }

    private static void saveSeatMatrixToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(seatMatrixFileName))) {
            for (int row = 0; row < seatMatrix.length; row++) {
                for (int col = 0; col < seatMatrix[row].length; col++) {
                    writer.write("  " + seatMatrix[row][col] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showSeatMatrix() {
        System.out.println("\n\t\t\tScreen\n");
        System.out.print("  ");
        for (int col = 0; col < seatMatrix[0].length; col++) {
            System.out.print("   " + col + " ");
        }
        System.out.println();
        char rowLabel = 'A';
        for (int row = 0; row < seatMatrix.length; row++) {
            System.out.print(rowLabel + " ");
            for (int col = 0; col < seatMatrix[row].length; col++) {
                System.out.print("  " + seatMatrix[row][col] + " ");
            }
            System.out.println();
            rowLabel++;
        }
        System.out.println();
    }

    public static void chooseSeat(String username, String filmName, int row, int col, String showTimes) {
        if (row < 0 || row >= seatMatrix.length || col < 0 || col >= seatMatrix[0].length) {
            System.out.println("Invalid seat selection.");
            return;
        }

        char selectedSeat = seatMatrix[row][col];
        if (selectedSeat == 'x') {
            System.out.println("Seat already occupied. Please choose another seat.");
            return;
        }

        seatMatrix[row][col] = 'x';

        // Update ticket information
        Ticket newTicket = new Ticket("", filmName, "", 0, null, row + "-" + col, 0, "");

        List<Ticket> userTickets = ticketList.get(username);
        if (userTickets != null) {
            userTickets.add(newTicket);
        } else {
            userTickets = new ArrayList<>();
            userTickets.add(newTicket);
            ticketList.put(username, userTickets);
        }

        saveSeatMatrixToFile();
    }

    public static void deleteTicket(String username, String filmName) {
        List<Ticket> tickets = ticketList.get(username);
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                if (ticket.getName().equals(filmName)) {
                    int row = Integer.parseInt(ticket.getSeat().split("-")[0]);
                    int col = Integer.parseInt(ticket.getSeat().split("-")[1]);
                    seatMatrix[row][col] = Character.forDigit(col, 10);
                    tickets.remove(ticket);
                    saveSeatMatrixToFile();
                    return;
                }
            }
        }
    }

    public static int numberOfTickets(String username) {
        List<Ticket> tickets = ticketList.get(username);
        if (tickets != null) {
            return tickets.size();
        }
        return 0;
    }

    public static void printSeatMatrix() {
        System.out.println("---------------------------- Screen ----------------------------");
        char rowLabel = 'A';
        for (int row = 0; row < seatMatrix.length; row++) {
            System.out.print(rowLabel + " ");
            for (int col = 0; col < seatMatrix[row].length; col++) {
                System.out.print("  " + seatMatrix[row][col] + " ");
            }
            System.out.println();
            rowLabel++;
        }
        System.out.println();
    }
}