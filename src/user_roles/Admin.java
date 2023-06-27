package user_roles;

import util.FilmManager;

public class Admin  extends User {

    public Admin(String adminID, String userName, String password, int fund, FilmManager fManager) {
        super(adminID, userName, password, fund);
        this.fManager = fManager;
        this.role = 1;
    }

    FilmManager fManager = new FilmManager();
    public void addNewFilm() {
//        fManager.addNewFilm();
    }
//    public void deleteFilm() {
//        fManager.deleteFilm();
//    }

    
}
