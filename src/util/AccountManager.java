
package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import user_roles.Admin;
import user_roles.User;

public class AccountManager extends HashMap<String, User> {
    final static String fileName = "src\\data\\account.txt";
    Scanner sc = new Scanner(System.in);
    public AccountManager() {
        super();
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String userID = data[0];
                    String userName = data[1];
                    String password = data[2];
                    int fund = Integer.parseInt(data[3]);
                    if (userID.charAt(0) == 'A') {
                        Admin admin = new Admin(userName, password, fund);
                        this.put(userID, admin);
                    } else {
                        User user = new User(userName, password, fund);
                        this.put(userName, user);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File does not exist, return an empty map
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsersToFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            this.forEach((k, v) -> {



                String line = String.format("%s, %s, %s, %s",


                        k, v.getUserName(), v.getPassword(), v.getFund()
                );
                try {
                    bw.write(line);
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void registerNewUser() {
        int counter = this.size() + 1;
        String userID = String.format("f%03d", counter);
        while (this.containsKey(userID)) {
            userID = String.format("f%03d", ++counter);
        }
        String userName, password;
        do {
            System.out.print("Input your username: ");
            userName = sc.nextLine();
            System.out.print("Input your password: ");
            password = sc.nextLine();
        } while (isUserExists(userName));

        System.out.println("------------Congrats, your account has been registed!------------");

        this.put(userID, new User(userName, password, 0));
        saveUsersToFile();
    }
    public User getUserByUserName(String userName) {
        for (User user : this.values()) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    public User getUserByUserID(String userID) {
        for (String ID : this.keySet()) {
            if (ID.equals(userID)) {
                return this.get(userID);
            }
        }
        return null;
    }
    public boolean isUserExists(String userName) {
        for (User user : this.values()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

}
