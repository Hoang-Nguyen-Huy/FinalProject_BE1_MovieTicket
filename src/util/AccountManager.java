package util;

import java.io.*;
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
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String userID = data[0];
                    String userName = data[1];
                    String password = data[2];
                    int fund = Integer.parseInt(data[3]);

                    User user = new User(userID, userName, password, fund);
                    loadedUsers.put(userName, user);
                }
            }
        } catch (FileNotFoundException e) {
            // File does not exist, return an empty map
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }

    private void saveUsersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : this.values()) {
                String line = user.getUserID() + "," + user.getUserName() + "," + user.getPassword() + ","
                        + user.getFund();
                bw.write(line);
                bw.newLine();
            }
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