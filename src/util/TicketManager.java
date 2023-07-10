package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp.Seat;
import comp.Ticket;

public class TicketManager {
    static Map<String, List<Ticket>> ticketList = new HashMap<>();
    static char[][] seatMatrix = new char[5][11];
    static int col = 5;
    static int row = 11;
    static final String seatMatrixFileName = "src/data/seat.txt";

    static {
        loadSeatMatrixFromFile();
    }
    TicketManager() {
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
    public void chooseFilm() {
        System.out.println();
    }

    public void chooseSeat(String username, String filmName, int row, int col) {
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            System.out.println("Invalid seat selection.");
            return;
        }

        char selectedSeat = seatMatrix[row][col];
        if (selectedSeat == 'x') {
            System.out.println("Seat already occupied. Please choose another seat.");
            return;
        }

        seatMatrix[row][col] = 'x';
        Seat userSeat = new Seat(row, col);
        // Update ticket information
        Ticket newTicket = new Ticket(filmName, );
        ticketList.get(username).add(newTicket);

        saveSeatMatrixToFile();
    }
}