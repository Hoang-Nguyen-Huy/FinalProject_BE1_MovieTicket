package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import user_roles.User;

public class AccountManager extends HashMap<String, User> {
    final static String fileName = "src\\data\\account.txt";

    public AccountManager() {
        super();
    }

    private Map<String, User> loadUsersFromFile() {
        Map<String, User> loadedUsers = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            loadedUsers = (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File does not exist, return an empty map
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }

    private void saveUsersToFile() {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addNewUser(User newUser) {
        if (isUserExists(newUser.getUserName())) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        this.put(newUser.getUserName(), newUser);
        saveUsersToFile();
    }
    public User getUser(String userName) {
        return this.get(userName);
    }

    public boolean isUserExists(String userName) {
        return this.containsKey(userName);
    }
}
