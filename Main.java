import Users.*;
import Utils.MyFiles;
import Utils.MyInput;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final int NAME_MIN_LEN = 3;
    private static final int NAME_MAX_LEN = 32;

    private static final String DB_USERS_DAT = "db/users.dat";

    public static void main(String[] args) {

        File fileUsersDB = new File(DB_USERS_DAT);
        MyFiles.createFile(fileUsersDB);

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US); // To make dot in double available

        Store store = new Store("Computer Store");

        ArrayList<User> userArrayList = new ArrayList<>();

        if (fileUsersDB.length() > 0) userArrayList = MyFiles.deserializeArrayList(fileUsersDB);

        System.out.println(store.getName());

        while (true) {

            // TODO: Delete
//            userArrayList.add(new Admin("aaa", "aaa", "111"));
//            userArrayList.add(new Manager("mmm", "mmm", "middle1", LocalDate.of(2001,1,1),"Junior1",
//                    "+380123123123", "111", 15000));
//            userArrayList.add(new Customer("ccc", "ccc", "+380678654444", "111"));
//            userArrayList.add(new Manager("first2", "sur2", "middle2", LocalDate.of(2002,2,2),"Junior2",
//                    "+380123123123", "123", 18000));
//            userArrayList.add(new Customer("first2", "sur2", "+380678654444", "111"));

            System.out.println("Choose action:");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("0 - Exit");

            switch (MyInput.inputInt(in, 0, 2)) {

                // Login
                case 1:

                    User userLoggedIn = login(userArrayList, inString);

                    if (userLoggedIn != null) {

                        System.out.println("Type: " + userLoggedIn.getType().toString());

                        switch (userLoggedIn.getType()) {
                            case CUSTOMER:

                                store.runAsCustomer(userLoggedIn, userArrayList);
                                break;

                            case MANAGER:

                                store.runAsManager(userLoggedIn, userArrayList);
                                break;

                            case ADMIN:

                                store.runAsAdmin(userLoggedIn, userArrayList);
                                break;

                            default:

                                System.out.println("Error! Unknown type of user");
                        }
                    }
                    else System.out.println("Such user was not found!");
                    break;

                // Register
                case 2:
                    System.out.println("Choose role:");
                    System.out.println("1 - Customer");
                    System.out.println("2 - Manager");
                    System.out.println("3 - Admin");
                    System.out.println("0 - Go back");

                    switch (MyInput.inputInt(in, 0, 3)) {

                        // Register customer
                        case 1:

                            registerCustomer(userArrayList, inString);
                            break;

                        // Register manager
                        case 2:

                            registerManager(userArrayList, in, inString);
                            break;

                        // Register admin
                        case 3:

                            registerAdmin(userArrayList, inString);
                            break;

                        // Go back
                        case 0:

                            break;
                    }

                    break;

                // Exit
                case 0:

                    MyFiles.serializeArrayList(userArrayList, fileUsersDB, false);
                    System.exit(0);
            }
        }
    }

    private static void registerManager(ArrayList<User> managerArrayList, Scanner in, Scanner inString) {

        int birthDay;
        int birthMonth;
        int birthYear;
        LocalDate birthDate;
        String firstName;
        String surName;
        String middleName;
        String phone;
        String jobTitle;
        String password;
        int salary;

        System.out.println("Registering a new manager!");

        // Basic information
        System.out.print("First name: ");
        firstName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Second name: ");
        surName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Middle name: ");
        middleName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.println("Birth date: ");
        System.out.print("Day: ");
        birthDay = MyInput.inputInt(in, 1, 31);
        System.out.print("Month: ");
        birthMonth = MyInput.inputInt(in, 1, 12);
        System.out.print("Year: ");
        birthYear = MyInput.inputInt(in, 1900, 2004);
        System.out.print("Job title (1 - Trainee, 2 - Junior, 3 - Senior): ");
        int tmpInt = MyInput.inputInt(in, 1, 3);

        switch (tmpInt) {
            case 1:

                jobTitle = "Trainee";
                salary = 10000;
                break;
            case 2:

                jobTitle = "Junior";
                salary = 15000;
                break;
            case 3:

                jobTitle = "Senior";
                salary = 25000;
                break;
            default:
                jobTitle = "Unnamed";
                salary = 3500;
        }

        System.out.print("Phone: +380");
        phone = "+380" + MyInput.inputString(inString, 9, 9);
        System.out.print("Password: ");
        password = MyInput.inputString(inString, 3, 12);

        birthDate = LocalDate.of(birthYear, birthMonth, birthDay);

        managerArrayList.add(new Manager(firstName, surName, middleName, birthDate, jobTitle, phone, password, salary));
    }

    private static void registerCustomer(ArrayList<User> customerArrayList, Scanner inString) {

        String firstName;
        String surName;
        String phone;
        String password;

        System.out.println("Registering a new customer!");

        // Basic information
        System.out.print("First name: ");
        firstName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Second name: ");
        surName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Phone: +380");
        phone = "+380" + MyInput.inputString(inString, 9, 9);
        System.out.print("Password: ");
        password = MyInput.inputString(inString, 3, 12);

        customerArrayList.add(new Customer(firstName, surName, phone, password));
    }

    private static void registerAdmin(ArrayList<User> customerArrayList, Scanner inString) {

        String firstName;
        String surName;
        String password;

        System.out.println("Registering a new admin!");

        // Basic information
        System.out.print("First name: ");
        firstName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Second name: ");
        surName = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Password: ");
        password = MyInput.inputString(inString, 3, 12);

        customerArrayList.add(new Admin(firstName, surName, password));
    }

    // Login into the shop
    // Return: user
    private static User login(ArrayList<User> userArrayList, Scanner inString) {

        String login;
        String password;

        System.out.print("Login (name and surname): ");
        login = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);

        User curLoginUser = findUserByLogin(userArrayList, login);

        if (curLoginUser != null) {

            boolean success = false;
            while (!success) {

                System.out.print("Password: ");
                password = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);

                if (password.equals(curLoginUser.getPassword())) {

                    System.out.println("Succeeded login!");
                    System.out.println("Welcome, " + login);
                    success = true;
                } else System.out.println("Incorrect password. Please, try again:");
            }
            return curLoginUser;
        } else return null;
    }

    // Return user by its login
    private static User findUserByLogin(ArrayList<User> userArrayList, String login) {

        for (User u : userArrayList) {

            if ((u.getFirstName() + " " + u.getSurName()).equals(login)) {

                return u;
            }
        }
        return null;
    }
}
