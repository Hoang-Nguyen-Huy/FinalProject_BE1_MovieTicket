package ui;

import java.util.ArrayList;

public class Menu extends ArrayList<String> { 
    public Menu() {
        super();    //default constructors
    }

    public void printAll() {
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            System.out.println("Menu:");
            for (String m : this) 
                System.out.println(m);   
        }
    }
}
