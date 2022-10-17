import People.Admin;
import People.Customer;
import People.Manager;
import People.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final int NAME_MIN_LEN = 3;
    private static final int NAME_MAX_LEN = 32;

    private static final String DB_CUSTOMERS_DAT = "db/customers.dat";
    private static final String DB_MANAGERS_DAT = "db/managers.dat";
    private static final String DB_ADMIN_DAT = "db/admin.dat";

    public static void main(String[] args) {

        File customerFileDB = new File(DB_CUSTOMERS_DAT);
        File managerFileDB = new File(DB_MANAGERS_DAT);
        File adminFileDB = new File(DB_ADMIN_DAT);
        MyFiles.createFile(customerFileDB);
        MyFiles.createFile(managerFileDB);
        MyFiles.createFile(adminFileDB);

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US); // To make dot in double available

        Store store = new Store("Computer Store");

        ArrayList<Manager> managerArrayList = new ArrayList<>();
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        Admin admin = null;

        if (managerFileDB.length() > 0) managerArrayList = MyFiles.deserializeArrayList(managerFileDB);
        if (customerFileDB.length() > 0) customerArrayList = MyFiles.deserializeArrayList(customerFileDB);
        if (adminFileDB.length() > 0) admin = MyFiles.deserializeObject(adminFileDB);

        System.out.println(store.getName());

        while (true) {

            System.out.println("Choose action:");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("0 - Exit");

            switch (MyInput.inputInt(in, 0, 2)) {

                case 1:

                    // List of all users


                    String login;
                    String password;

                    System.out.print("Login: ");
                    login = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);



                    System.out.print("Password: ");
                    password = MyInput.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);

                    break;

                case 2:
                    System.out.println("Who are you:");
                    System.out.println("1 - Customer");
                    System.out.println("2 - Manager");
                    System.out.println("3 - Admin");
                    System.out.println("0 - Go back");

                    switch (MyInput.inputInt(in, 0, 3)) {

                        // Register customer
                        case 1:

                            registerCustomer(customerArrayList, inString);
                            MyFiles.serializeArrayList(customerArrayList, customerFileDB);

                            break;

                        // Register manager
                        case 2:
                            registerManager(managerArrayList, in, inString);
                            MyFiles.serializeArrayList(managerArrayList, managerFileDB);
                            break;

                        // Register admin
                        case 3:

                            if (admin == null) {

                                admin = registerAdmin(inString);
                                MyFiles.serializeObject(admin, adminFileDB);
                            }
                            else {

                                System.out.println("Admin is already registered!");
                                System.out.println("To restore password, delete " + DB_ADMIN_DAT);
                            }
                            break;

                        // Go back
                        case 0:
                            break;
                    }

                    break;

                // Exit
                case 0:
                    System.exit(0);
            }
        }
    }

    private static void registerManager(ArrayList<Manager> managerArrayList, Scanner in, Scanner inString) {

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
        System.out.print("Job title (1 - Trainee, 2 - Worker, 3 - Loader): ");
        int tmpInt = MyInput.inputInt(in, 1, 3);

        switch (tmpInt) {
            case 1:     jobTitle = "Trainee"; break;
            case 2:     jobTitle = "Worker";  break;
            case 3:     jobTitle = "Loader";  break;
            default:    jobTitle = "Unnamed";
        }

        System.out.print("Phone: +380");
        phone = "+380" + MyInput.inputString(inString, 9, 9);
        System.out.print("Password: ");
        password = MyInput.inputString(inString, 3, 12);

        birthDate = LocalDate.of(birthYear, birthMonth, birthDay);

        managerArrayList.add(new Manager(firstName, surName, middleName, birthDate, jobTitle, phone, password));
    }

    private static void registerCustomer(ArrayList<Customer> customerArrayList, Scanner inString) {

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

    private static Admin registerAdmin(Scanner inString) {

        String password;

        System.out.print("Password: ");
        password = MyInput.inputString(inString, 3, 12);

        return new Admin(password);
    }
}
