package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    Map<Integer, ArrayList<String>> menuManagement = new HashMap<>();
    int id;
    public MenuManager(Map<Integer, ArrayList<String>> menuManagement, int id) {
        this.menuManagement = menuManagement;
        this.id = id;
    }
    


}