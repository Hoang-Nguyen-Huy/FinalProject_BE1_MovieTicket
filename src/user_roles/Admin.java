package user_roles;

import util.FilmManager;

public class Admin  extends User {
    
    public void addNewFilm() {
        FilmManager.addNewFilm(this);
    }
    public void deleteFilm() {
        FilmManager.deleteFilm();
    }
}
