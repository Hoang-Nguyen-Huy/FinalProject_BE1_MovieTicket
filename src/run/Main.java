
package run;

import org.w3c.dom.Text;
import ui.Menu;
import user_roles.Admin;
import user_roles.User;
import util.AccountManager;
import util.FilmManager;
import util.TextFileHandler;
import util.TicketManager;
import comp.showTimes;
import comp.Ticket;
import comp.Film;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import java.text.ParseException;

/*
Nguyễn Huy Hoàng
Lê Quang Trung
Final Project BE1 - Movie Ticket Booking System Online with Java
 */
public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        Menu menu = new Menu();
        AccountManager accounts = new AccountManager();
        FilmManager films = new FilmManager();
        TicketManager tickets;
        TextFileHandler fileHandler;
        showTimes showTimes = new showTimes();
        do {
            menu.welcomeMenu();
            menu.printAll();
            choice = menu.getInteger();
            fileHandler = new TextFileHandler("src/data/film.txt");
            List<String[]> filmData = fileHandler.readFilms();
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
                Film temp = new Film(filmID, name, director, duration, date, price);
                films.put(filmID, temp);
            }

            switch (choice) {
                case 1 -> {
                    String username = menu.getUsername();
                    String password = menu.getPassword();
                    User user = accounts.loginAccount(username, password);
                    if (user.getClass() == User.class) {
                        // bat nhap fund neu fund == 0
                        if (menu.checkAccountBalance(accounts, username) == 0) {
                            accounts.updateFund(username);
                        }
                        menu.userMenu();
                        int userChoice;
                        do {
                            menu.printAll();
                            userChoice = menu.getChoice();
                            switch (userChoice) {
                                case 1 -> user.buyTicket(username);
                            }
                        } while (userChoice != 0);


                    } else if (user.getClass() == Admin.class) {
                        System.out.println("Welcome Admin ^^");
                        menu.adminMenu();
                        int adminChoice;
                        do {
                            menu.printAll();
                            adminChoice = menu.getChoice();
                            switch (adminChoice) {
                                case 1 -> films.addNewFilm();
                                case 2 -> films.showAllFilms();
                                case 3 -> films.updateFilm();   //bổ sung bên Film Manager
                                case 4 -> films.deleteFilm();

                                // case 5->8: Ông tạo thêm 1 biến chứa các show của các bộ phim
                                // tạo txt chứa "filmid, 1, 2, 3" cho từng ca chiếu chẳng hạn
                                // đọc file lưu showTimes vào showTimes manager - làm tương tự các manager khác
//                                5. Create New Show.
//                                6. Show all shows.
//                                7. Update show times.
//                                8. Delete show.
                                // tạo các method như này
                                // có gì căn chỉnh output cho mấy cái khác cho nó oke nha

                            }
                        } while (adminChoice != 0);



                    } else {
                            System.out.println("Wrong username or password! Try again or Register.");
                    }

                } // end case 1
                case 2 -> accounts.registerNewUser();
                case 0 -> System.out.println("Bye! See you again.");
            }
        } while (choice != 0);

    }

}