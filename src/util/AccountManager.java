
package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import user_roles.Admin;
import user_roles.User;

public class AccountManager extends HashMap<String, User> {
    public static String fileName = "src\\data\\account.txt";
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
                    int fund = Integer.parseInt(data[3].trim());
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
            for (Map.Entry<String, User> entry : this.entrySet()) {
                String userID = entry.getKey();
                User user = entry.getValue();
                String line = String.format("%s, %s, %s, %d",
                        userID, user.getUserName(), user.getPassword(), user.getFund());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loginAccount(String username, String password) {
        User user = getUserByUsernameAndPassword(username, password);
        if (user != null) {
            System.out.println("Login Successfully!");
            return user;
        } else {
            System.out.println("Wrong username or password!");
            return null;
        }
    }

    private User getUserByUsernameAndPassword(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 4) {
                    String userID = data[0];
                    String userName = data[1];
                    String pw = data[2];
                    int fund = Integer.parseInt(data[3].trim());
                    if (userName.equals(username) && pw.equals(password)) {
                        if (userID.charAt(0) == 'A') {
                            return new Admin(userName, pw, fund);
                        } else {
                            return new User(userName, pw, fund);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File does not exist
            System.out.println("Error: Account file not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public void updateFund(String userName) {
        String tempFileName = "temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 4 && data[1].equals(userName)) {
                    int fund = Integer.parseInt(data[3].trim());
                    if (fund == 0) {
                        System.out.print("Input the amount of money you want to deposit (minimum 100): ");
                        int newFund = Integer.parseInt(sc.nextLine());
                        while (newFund < 100) {
                            System.out.print("Invalid amount! Please input at least 100: ");
                            newFund = Integer.parseInt(sc.nextLine());
                        }
                        line = String.format("%s, %s, %s, %d", data[0], data[1], data[2], newFund);
                    }
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Xóa tệp gốc và đổi tên tệp tạm thành tên tệp gốc
        File originalFile = new File(fileName);
        originalFile.delete();

        File tempFile = new File(tempFileName);
        tempFile.renameTo(originalFile);
    }


    public void fundAfterPurchasing (String username, int price) {
        String tempFileName = "temp.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 4 && data[1].equals(username)) {
                    int fund = Integer.parseInt(data[3].trim());
                    if (fund > price) {
                        int newFund = fund - price;
                        line = String.format("%s, %s, %s, %d", data[0], data[1], data[2], newFund);
                    } else {
                        System.out.println(" You don't have enough money in your account.");
                    }
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Xóa tệp gốc và đổi tên tệp tạm thành tên tệp gốc
        File originalFile = new File(fileName);
        originalFile.delete();

        File tempFile = new File(tempFileName);
        tempFile.renameTo(originalFile);
    }
}
