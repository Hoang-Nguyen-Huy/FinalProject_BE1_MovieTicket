package ui;

import java.util.ArrayList;
import java.util.List;

public class Menu extends ArrayList<String> { 
    public Menu {
        super();    //default constructors
    }

    public void printAll() {
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            System.out.println("Menu:");
            for (Menu m : this) 
                System.out.println(m);   
        }
    }
}
