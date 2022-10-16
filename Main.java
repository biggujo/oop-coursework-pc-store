import People.Customer;
import People.Manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String SHOP_NAME = "Computer Store";

    private static final int NAME_MIN_LEN = 3;
    private static final int NAME_MAX_LEN = 32;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);

        ArrayList<Manager> managerArrayList = new ArrayList<>();
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        System.out.println(SHOP_NAME);

        while (true) {

            System.out.println("Choose action:");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("0 - Exit");

            switch (Input.inputInt(in, 0, 2)) {


                case 2:
                    System.out.println("Who are you:");
                    System.out.println("1 - Customer");
                    System.out.println("2 - Manager");
                    System.out.println("0 - Go back");

                    switch (Input.inputInt(in, 0, 2)) {

                        case 1:

                            registerCustomer(customerArrayList, inString);
                            break;

                        case 2:
                            registerManager(managerArrayList, in, inString);
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

    public static void registerManager(ArrayList<Manager> managerArrayList, Scanner in, Scanner inString) {

        int birthDay;
        int birthMonth;
        int birthYear;
        LocalDate birthDate;
        String firstName;
        String surName;
        String middleName;
        String jobTitle;
        String password;

        System.out.println("Registering a new manager!");

        // Basic information
        System.out.print("First name: ");
        firstName = Input.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Second name: ");
        surName = Input.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Middle name: ");
        middleName = Input.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.println("Birth date: ");
        System.out.print("Day: ");
        birthDay = Input.inputInt(in, 1, 31);
        System.out.print("Month: ");
        birthMonth = Input.inputInt(in, 1, 12);
        System.out.print("Year: ");
        birthYear = Input.inputInt(in, 1900, 2004);
        System.out.print("Job title: ");
        jobTitle = Input.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Password: ");
        password = Input.inputString(inString, 3, 12);

        birthDate = LocalDate.of(birthYear, birthMonth, birthDay);

        managerArrayList.add(new Manager(firstName, surName, middleName, birthDate, jobTitle, password));
    }

    public static void registerCustomer(ArrayList<Customer> customerArrayList, Scanner inString) {

        String firstName;
        String surName;
        String password;

        System.out.println("Registering a new customer!");

        // Basic information
        System.out.print("First name: ");
        firstName = Input.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Second name: ");
        surName = Input.inputString(inString, NAME_MIN_LEN, NAME_MAX_LEN);
        System.out.print("Password: ");
        password = Input.inputString(inString, 3, 12);

        customerArrayList.add(new Customer(firstName, surName, password));
    }
}
