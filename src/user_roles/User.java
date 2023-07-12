
package user_roles;

import util.FilmManager;
import util.TextFileHandler;
import util.TicketManager;
import util.AccountManager;
import comp.showTimes;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;


public class User {
    Scanner sc = new Scanner(System.in);
    private static final String filmFile = "src/data/film.txt";
    private static final String ticketFile = "src/data/ticket.txt";
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



    public void buyTicket(String userName) {
        System.out.println("-------------------------");
        // in ra danh sách các phim đang chiếu
        TextFileHandler filmFileHandler = new TextFileHandler("src/data/film.txt");
        List<String[]> filmData = filmFileHandler.readFilms();
        for (String[] line : filmData) {
            String filmID = line[0];
            String name = line[1];
            String director = line[2];
            int duration = Integer.parseInt(line[3]);
            Date date = null;
            try {
                date = new SimpleDateFormat(FilmManager.DATE_FORMAT).parse(line[4]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int price = Integer.parseInt(line[5].trim());
            System.out.println("Film ID: " + filmID);
            System.out.println("Name: " + name);
            System.out.println("Director: " + director);
            System.out.println("Duration: " + duration + " minutes");
            System.out.println("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(date));
            System.out.println("Price: $" + price);
            System.out.println("-------------------------");
        }
        // bắt người dùng nhập tên phim
        System.out.print("Enter film ID: ");
        String filmID = sc.nextLine();

        // hiển thị các suất chiếu cho phim đã chọn
        showTimes sT = new showTimes();
        sT.displayShowTimes(filmID);

        // chọn vé và cập nhật thông tin vé
        TicketManager tM = new TicketManager();
        tM.showSeatMatrix();


        // yêu cầu người dùng nhập vị trí chỗ ngồi là số dòng và số cột
        System.out.print("Enter the row number: ");
        int row = sc.nextInt();
        System.out.print("Enter the column number: ");
        int column = sc.nextInt();
        sc.nextLine(); // đọc dòng newline còn sót lại sau khi nhập số cột
        String showTimesInfo = sT.handleUserChoice(sT.getUserChoice());

        tM.chooseSeat(userName, filmID, row, column, showTimesInfo);

        String seatInfo = "";
        if (row == 1) {
            seatInfo = "A";
        } else if (row == 2) {
            seatInfo = "B";
        } else if (row == 3) {
            seatInfo = "C";
        } else if (row == 4) {
            seatInfo = "D";
        } else if (row == 5) {
            seatInfo = "E";
        }

        String ticketInfo = userName + ", "+ filmID + ", " + getDirector(filmID) + ", " + getDuration(filmID) + ", " + seatInfo + "-" + column + ", " + showTimesInfo;

        int price = getPrice(filmID);
        System.out.println(" Gia tien cua bo phim do la: " + price);

        AccountManager account = new AccountManager();
        account.fundAfterPurchasing(userName, price);

        //nhap thong tin ve vao file ticket.txt
        addingTicket(ticketInfo);
        System.out.println(" Purchase successfully ^ ^. Thanks You <3");

    }


    public void refundTicket(String filmName) {
        TicketManager.deleteTicket(this.userName, filmName);
    }

    public int getNumTickets() {
        return TicketManager.numberOfTickets(this.userName);
    }

    private String getDirector(String filmID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filmFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filmData = line.split(",");
                if (filmData[0].equals(filmID)) {
                    return filmData[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getDuration(String filmID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filmFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filmData = line.split(",");
                if (filmData[0].equals(filmID)) {
                    return Integer.parseInt(filmData[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addingTicket (String ticketInfo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ticketFile, true))) {
            writer.write(ticketInfo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getPrice (String filmID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filmFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filmData = line.split(",");
                if (filmData[0].equals(filmID)) {
                    return Integer.parseInt(filmData[5].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void showTicket(String userName) {
        System.out.println("-------------------------");
        try (BufferedReader reader = new BufferedReader(new FileReader(ticketFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data[0].equals(userName)) {
                    String filmID = data[1];
                    String director = data[2];
                    int duration = Integer.parseInt(data[3].trim());
                    String seat = data[4];
                    String showTimesInfo = data[5];

                    System.out.println("Film ID: " + filmID);
                    System.out.println("Director: " + director);
                    System.out.println("Duration: " + duration + " minutes");
                    System.out.println("Seat: " + seat);
                    System.out.println("Show Times: " + showTimesInfo);
                    System.out.println("-------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

}
