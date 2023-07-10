
package run;

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

import java.text.ParseException;

/*
Nguyễn Huy Hoàng
Lê Quang Trung
Final Project BE1 - Movie Ticket Booking System Online with Java
 */
public class Main {
    public static void main (String[] args) {
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
            fileHandler = new TextFileHandler("data/film.txt");
            films = (FilmManager) fileHandler.readFilms();

            switch (choice) {
                case 1 -> {
                    User user = accounts.loginAccount(menu.getUsername(), menu.getPassword());
                    if (user.getClass() == User.class) {
                        System.out.println("Login Successfully!");
                        menu.emptyBalance(user.getFund());
                        menu.userMenu();
                        int userChoice;
                        do {
                            menu.printAll();
                            userChoice = menu.getChoice();
                            switch (userChoice) {

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

                }
                case 2 -> accounts.registerNewUser();
                case 0 -> System.out.println("Bye! See you again.");
            }
        } while (choice != 0);

    }

}