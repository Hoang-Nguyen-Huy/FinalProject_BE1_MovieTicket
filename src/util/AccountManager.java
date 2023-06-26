package util;

import java.util.HashMap;
import java.util.Map;

import user_roles.User;

public class AccountManager {
    Map<String, User> users = new HashMap<>();
    private String filename;
    public AccountManager(String filename) {
        this.filename = filename;
        users = loadUsersFromFile();
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

            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addNewUser(User newUser) {
        if (isUserExists(newUser.getUserName())) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        users.put(newUser.getUserName(), newUser);
        saveUsersToFile();
    }
    public User getUser(String userName) {
        return users.get(userName);
    }

    public boolean isUserExists(String userName) {
        return users.containsKey(userName);
    }
}
