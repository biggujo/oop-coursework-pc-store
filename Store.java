import Hardware.*;
import Users.Manager;
import Users.TypesOfUsers;
import Users.User;
import Utils.MyFiles;
import Utils.MyInput;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Store {

    // Constants for input
    private final static int MIN_LENGTH = 1;
    private final static int MAX_LENGTH = 32;

    // File to store hardware in binary format
    private final static String DB_HARDWARE_DAT = "db/hardware.dat";

    // File to export hardware in CSV format. Delimiter - comma
    private final static String DB_HARDWARE_CSV = "out/hardware.csv";

    // File to export users in CSV format. Delimiter - comma
    private final static String DB_USERS_CSV = "out/users.csv";

    // Constants to filter hardware
    private final static int SHOW_ALL = 0;
    private final static int SHOW_IN_STOCK = 1;
    private final static int SHOW_SOLD = 2;

    // Name of store
    private String name;

    public Store(String name) {
        this.name = name;
    }

    // Run store as customer
    public void runAsCustomer(ArrayList<User> userArrayList) {

        // Create file to store hardware
        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        // Scanner
        Scanner in = new Scanner(System.in);

        // To input double with dot
        in.useLocale(Locale.US);

        // Create ArrayList of hardware
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        // Read hardware from the binary file with deserialization
        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.print("How much money do you have today:");
        int customerMoney = MyInput.inputInt(in, 1, 99999999);

        // Create ArrayList of shopping cart with Hardware
        ArrayList<Hardware> shoppingCartArrayList = new ArrayList<>();

        // Array list to store indexes of users of some class
        ArrayList<Integer> indexArrayListOfClass;

        indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

        // If no managers
        if (indexArrayListOfClass.size() == 0) {

            // Say to "wait" for a manager
            System.out.println("Please, add some managers first to run the store!");
            return;
        }

        System.out.println("Store menu:");

        System.out.println("1 - View hardware");
        System.out.println("2 - Buy hardware");
        System.out.println("3 - Buy computer");
        System.out.println("4 - View shopping cart");
        System.out.println("5 - Checkout");
        System.out.println("0 - Close store");

        while (true) {

            System.out.print("> ");

            int choose = MyInput.inputInt(in, 0, 5);
            switch (choose) {

                // View hardware
                case 1:

                    if (hardwareArrayList.size() > 0) {

                        // Call method to print hardware
                        uiPrintHardware(hardwareArrayList, SHOW_IN_STOCK, in);
                    }

                    break;

                // Buy hardware or computer
                case 2: // hardware
                case 3: // computer

                    if (hardwareArrayList.size() == 0) {

                        System.out.println("No hardware! Please, wait for the hardware to be added!");
                        break;
                    }

                    // Save choose to buy
                    if (choose == 2) { // hardware

                        System.out.println("2 - Buy hardware");

                        choose = uiPrintHardware(hardwareArrayList, SHOW_IN_STOCK, in);
                        TypesOfHardware hardwareChoose = null;

                        // Get saved type to buy
                        switch (choose) {

                            case 1:

                                hardwareChoose = TypesOfHardware.CASE;
                                break;

                            case 2:

                                hardwareChoose = TypesOfHardware.COOLER;
                                break;

                            case 3:

                                hardwareChoose = TypesOfHardware.CPU;
                                break;

                            case 4:

                                hardwareChoose = TypesOfHardware.GPU;
                                break;

                            case 5:

                                hardwareChoose = TypesOfHardware.RAM;
                                break;

                            case 6:

                                hardwareChoose = TypesOfHardware.SSD;
                                break;

                            case 7:

                                hardwareChoose = TypesOfHardware.MOTHERBOARD;
                                break;

                            case 8:

                                hardwareChoose = TypesOfHardware.POWER_SUPPLY;
                                break;

                        }

                        // Save indexes of hardware of some type
                        indexArrayListOfClass = returnIndexesOfHardware(hardwareArrayList, hardwareChoose, SHOW_IN_STOCK);
                    }
                    else { // computer

                        System.out.println("3 - Buy computer");

                        printHardwareByType(hardwareArrayList, TypesOfHardware.PC, SHOW_IN_STOCK);

                        // Save indexes of PCs
                        indexArrayListOfClass = returnIndexesOfHardware(hardwareArrayList, TypesOfHardware.PC, SHOW_IN_STOCK);
                    }

                    // If given types of Hardware is found
                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose to buy (index): ");

                        int indexToAdd = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        // Add to cart prompted Hardware item
                        shoppingCartArrayList.add(hardwareArrayList.get(indexArrayListOfClass.get(indexToAdd - 1)));
                        hardwareArrayList.remove((int) indexArrayListOfClass.get(indexToAdd - 1));

                        System.out.println("Success!");
                    } else System.out.println("No such hardware!");

                    break;

                // View shopping cart
                case 4:

                    System.out.println("4 - View shopping cart");

                    // If no items in cart
                    if (shoppingCartArrayList.size() == 0) {

                        System.out.println("Please, add some items to card first!");
                    }

                    // Print all shopping cart of Hardware
                    for (Hardware h : shoppingCartArrayList) {
                        System.out.println(h);
                    }

                    break;

                // Checkout
                case 5:

                    // If shopping cart is empty
                    if (shoppingCartArrayList.size() == 0) {

                        System.out.println("Add items first!");
                        break;
                    }

                    // Create needed amount of money to checkout
                    int sum = 0;

                    // Get sum of all Hardware
                    for (Hardware h :
                            shoppingCartArrayList) {
                        sum += h.getPrice();
                    }

                    // If not enough money
                    if (sum > customerMoney) {

                        System.out.println("Not enough money!");
                        System.out.print("Needed: " + sum + ", present: " + customerMoney);
                    }
                    else { // enough money

                        System.out.println("Enter date of sell:");
                        System.out.print("Year: ");
                        int year = MyInput.inputInt(in, 1900, 3000);
                        System.out.print("Month: ");
                        int month = MyInput.inputInt(in, 1, 12);

                        System.out.println("What manager helped you to buy stuff?");

                        // Print managers
                        int userArrayListSize = userArrayList.size();
                        for (int i = 0, j = 0; i < userArrayListSize; i++) {
                            User u = userArrayList.get(i);

                            if (u.getType() == TypesOfUsers.MANAGER) {

                                System.out.printf("%d,%s,%s\n", j + 1, u.getFirstName(), u.getSurName());
                                j++;
                            }
                        }

                        // Choose a manager
                        indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                        System.out.print("> ");
                        choose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        Manager m = (Manager) userArrayList.get(indexArrayListOfClass.get(choose - 1));

                        // Save sold items to manager
                        for (Hardware h :
                                shoppingCartArrayList) {

                            // Save selling date
                            h.setSaleYear(year);
                            h.setSaleMonth(month);

                            // Add selling to manager
                            m.addSold(h);
                        }

                        // Empty shopping cart
                        shoppingCartArrayList = new ArrayList<>();
                    }

                    customerMoney -= sum;

                    System.out.println("Success!");
                    System.out.println("Current money: " + customerMoney);

                    break;

                // Close store
                case 0:

                    System.out.println("0 - Close store");

                    // If cart is not empty
                    if (shoppingCartArrayList.size() > 0) {

                        // Return all items to shop
                        int tmpSize = shoppingCartArrayList.size();
                        for (int i = 0; i < tmpSize; i++) {

                            hardwareArrayList.add(shoppingCartArrayList.get(0));
                            shoppingCartArrayList.remove(0);
                        }
                    }

                    // Save users to file with deserialization
                    if (!MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false)) {

                        System.out.println("Error saving to " + fileHardwareDB.getPath());
                    }
                    return;
            }
        }
    }

    // Run store as manager
    public void runAsManager(User currentUser) {

        // Create file to store hardware in binary file
        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        // Create file to store hardware in CSV file
        File fileHardwareCSV = new File(DB_HARDWARE_CSV);
        MyFiles.createFile(fileHardwareCSV);

        // Scanner for numbers
        Scanner in = new Scanner(System.in);

        // Scanner for strings
        Scanner inString = new Scanner(System.in);

        // To input double with dot
        in.useLocale(Locale.US);

        // List of computer parts
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        // Read hardware from the binary file with deserialization
        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.println("Store menu:");

        System.out.println("1 - Add part");
        System.out.println("2 - Add computer");
        System.out.println("3 - Print some stock");
        System.out.println("4 - Print all stock");
        System.out.println("5 - Print sold hardware by month");
        System.out.println("6 - Save all hardware to " + DB_HARDWARE_CSV);
        System.out.println("0 - Close store");

        while (true) {

            System.out.print("> ");

            switch (MyInput.inputInt(in, 0, 6)) {

                // Add part
                case 1:
                    System.out.println("1 - Add part");

                    System.out.println("Choose part:");
                    System.out.println("1 - Case");
                    System.out.println("2 - Cooler");
                    System.out.println("3 - CPU");
                    System.out.println("4 - GPU");
                    System.out.println("5 - RAM");
                    System.out.println("6 - SSD");
                    System.out.println("7 - Motherboard");
                    System.out.println("8 - Power supply");
                    System.out.println("0 - Go back");
                    System.out.print("> ");

                    // Add chosen hardware
                    switch (MyInput.inputInt(in, 1, 8)) {

                        case 1:

                            hardwareArrayList.add(addPartCase(in, inString));
                            break;

                        case 2:

                            hardwareArrayList.add(addPartCooler(in, inString));
                            break;

                        case 3:

                            hardwareArrayList.add(addPartCPU(in, inString));
                            break;

                        case 4:

                            hardwareArrayList.add(addPartGPU(in, inString));
                            break;

                        case 5:

                            hardwareArrayList.add(addPartRAM(in, inString));
                            break;

                        case 6:

                            hardwareArrayList.add(addPartSSD(in, inString));
                            break;

                        case 7:

                            hardwareArrayList.add(addPartMotherboard(in, inString));
                            break;

                        case 8:

                            hardwareArrayList.add(addPartPowerSupply(in, inString));
                            break;
                    }

                    System.out.println("Success!");
                    break;


                // Add computer
                case 2:
                    System.out.println("2 - Add computer");
                    hardwareArrayList.add(addPC(in, inString));

                    System.out.println("Success!");
                    break;

                // Print some stock
                case 3:

                    System.out.println("3 - Print some stock");

                    // If no hardware, print error
                    if (hardwareArrayList.size() == 0) {

                        System.out.println("Add some hardware first!");
                        break;
                    }

                    // Call method to print hardware
                    uiPrintHardware(hardwareArrayList, SHOW_IN_STOCK, in);

                    break;

                // Print all stock
                case 4:

                    System.out.println("4 - Print all stock");

                    // If no hardware, print error
                    if (hardwareArrayList.size() == 0) {

                        System.out.println("Add some hardware first!");
                        break;
                    }

                    // Print all hardware in stock
                    printHardwareByType(hardwareArrayList, null, SHOW_IN_STOCK);
                    break;

                // Print sold hardware by month
                case 5:

                    System.out.println("5 - Print sold hardware by month");

                    ArrayList<Hardware> soldParts;
                    Manager curManager = (Manager) currentUser;

                    // Get sold parts from logged in manager (current manager)
                    soldParts = curManager.getSoldParts();

                    if (soldParts.size() == 0) {

                        System.out.println("No hardware! Please, try hard to sell hardware!");
                    }

                    int month;
                    int year;

                    // Prompt exact year and month
                    System.out.print("Year: ");
                    year = MyInput.inputInt(in, 1900, 3000);
                    System.out.print("Month: ");
                    month = MyInput.inputInt(in, 1, 12);

                    System.out.printf("Sold on %02d.%4d:\n", month, year);

                    // Print all hardware with exact date of sale
                    for (Hardware h : soldParts) {

                        if (h.getSaleYear() == year && h.getSaleMonth() == month) {

                            System.out.println(h);
                        }
                    }

                    break;

                // Save hardware to CSV file
                case 6:
                    System.out.println("5 - Save all hardware to " + DB_HARDWARE_CSV);

                    if (hardwareArrayList.size() == 0) {

                        System.out.println("Add some hardware first!");
                    }

                    if ( MyFiles.fileWriterArrayList(hardwareArrayList, fileHardwareCSV)) {

                        System.out.println("Success!");
                    } else System.out.println("Error writing to " + fileHardwareCSV.getPath());

                    break;

                // Close store
                case 0:

                    System.out.println("0 - Close store");

                    // Save users to file with deserialization
                    if (!MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false)) {

                        System.out.println("Error saving to " + fileHardwareDB.getPath());
                    }
                    return;
            }
        }

    }

    // Run store as admin
    public void runAsAdmin(ArrayList<User> userArrayList) {

        // Create file to store hardware in binary file
        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        // Create file to export users to CSV file
        File fileUsersCSV = new File(DB_USERS_CSV);
        MyFiles.createFile(fileUsersCSV);

        // Scanner for numbers
        Scanner in = new Scanner(System.in);

        // To input double with dot
        in.useLocale(Locale.US);

        // List of computer parts
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        // Read hardware from the binary file with deserialization
        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.println("Store menu:");

        // Array list to store indexes of users of some class
        ArrayList<Integer> indexArrayListOfClass;

        System.out.println("1 - View managers");
        System.out.println("2 - View customers");
        System.out.println("3 - View stock");
        System.out.println("4 - Print manager sales");
        System.out.println("5 - Set salary for manager");
        System.out.println("6 - Fire manager");
        System.out.println("7 - Delete customer");
        System.out.println("8 - Save users to " + fileUsersCSV);
        System.out.println("0 - Close store");

        while (true) {

            System.out.print("> ");

            switch (MyInput.inputInt(in, 0, 8)) {

                // 1 - View managers
                case 1:

                    System.out.println("1 - View managers");
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    break;

                // 2 - View customers
                case 2:

                    System.out.println("2 - View customers");
                    printUsersByType(userArrayList, TypesOfUsers.CUSTOMER);
                    break;

                // 3 - View stock
                case 3:

                    System.out.println("3 - View hardware");

                    if (hardwareArrayList.size() > 0) {

                        // Call method to print hardware
                        uiPrintHardware(hardwareArrayList, SHOW_IN_STOCK, in);
                    }

                    break;

                // 4 - Print manager sales
                case 4:

                    System.out.println("4 - Print manager sales");

                    // Print managers
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                    // If managers are found
                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose manager:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        // Print chosen manager
                        System.out.println(userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));

                        // Get sold parts from manager
                        Manager tmpManager = (Manager) userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1));
                        ArrayList<Hardware> soldParts = tmpManager.getSoldParts();

                        // Print sold parts of chosen manager
                        for (Hardware h : soldParts) {
                            System.out.println(h);
                        }
                    } else System.out.println("Managers not found!");

                    break;

                // 5 - Set salary for manager
                case 5:

                    System.out.println("5 - Set salary for manager");

                    // Print managers
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                    // If managers are found
                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose manager:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        Manager curManager = (Manager) userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1));

                        // Print chosen manager
                        System.out.println(userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));

                        // Set new salary
                        System.out.println("New salary (1-999999): ");
                        curManager.setSalary(MyInput.inputInt(in, 1, 999999));

                        // Print updated manager
                        System.out.println(userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));
                        System.out.println("Success!");
                    } else System.out.println("Managers not found!");

                    break;

                // 6 - Fire manager
                case 6:

                    System.out.println("6 - Fire manager");

                    // Print managers
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                    // If managers are found
                    if (indexArrayListOfClass.size() > 0) {

                        // Choose manager to remove
                        System.out.println("Choose manager:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        // Print removed
                        System.out.println("DELETED: " + userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));

                        // Remove manager
                        userArrayList.remove((int) indexArrayListOfClass.get(tmpChoose - 1));
                        System.out.println("Success!");
                    } else System.out.println("Managers not found!");

                    break;

                // 7 - Delete customer
                case 7:

                    System.out.println("7 - Delete customer");

                    // Print customers
                    printUsersByType(userArrayList, TypesOfUsers.CUSTOMER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.CUSTOMER);

                    // If managers are found
                    if (indexArrayListOfClass.size() > 0) {

                        // Choose customer to remove
                        System.out.println("Choose customer:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        // Print removed
                        System.out.println("DELETED: " + userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));

                        // Remove customer
                        userArrayList.remove((int) indexArrayListOfClass.get(tmpChoose - 1));
                        System.out.println("Success!");
                    } else System.out.println("Customers not found!");

                    break;

                // 8 - Save users to CSV file
                case 8:

                    System.out.println("8 - Save users to " + fileUsersCSV);

                    if ( MyFiles.fileWriterArrayList(userArrayList, fileUsersCSV)) {

                        System.out.println("Success!");
                    } else System.out.println("Error writing to " + fileUsersCSV.getPath());

                    break;

                // 0 - Close store
                case 0:

                    // Save users to file with deserialization
                    if (!MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false)) {

                        System.out.println("Error saving to " + fileHardwareDB.getPath());
                    }
                    return;
            }
        }
    }

    // Set shared attributes for hardware
    private void partSetGeneralAttr(Hardware hardware, Scanner in, Scanner inString) {

        int price;
        String name;
        String manufacturer;
        String color;

        // Fill fields
        System.out.print("Name: ");
        name = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Manufacturer: ");
        manufacturer = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Price: ");
        price = MyInput.inputInt(in, 1, 9999999);
        System.out.print("Color: ");
        color = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);

        // Save fields
        hardware.setPrice(price);
        hardware.setName(name);
        hardware.setManufacturer(manufacturer);
        hardware.setColor(color);
    }

    // Add hardware "Case"
    private Hardware addPartCase(Scanner in, Scanner inString) {

        String formFactor;
        double weight;
        boolean backlight = false;

        // Create new part
        HardwareCase partCase = new HardwareCase();

        // Set all general attributes
        partSetGeneralAttr(partCase, in, inString);

        // Fill fields
        System.out.print("Form factor:");
        formFactor = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Weight (kg):");
        weight = MyInput.inputDouble(in, 1., 99.);
        System.out.print("Backlight (1 or 0): ");
        int tmp = MyInput.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;

        // Save fields
        partCase.setFormFactorCompatibility(formFactor);
        partCase.setWeight(weight);
        partCase.setBacklight(backlight);

        return partCase;
    }

    // Add hardware "Cooler"
    private Hardware addPartCooler(Scanner in, Scanner inString) {

        int rpm;
        int maxNoiseLevel;
        boolean backlight = false;

        // Create new part
        HardwareCooler partCooler = new HardwareCooler();

        // Set all general attributes
        partSetGeneralAttr(partCooler, in, inString);

        // Fill fields
        System.out.print("RPM: ");
        rpm = MyInput.inputInt(in, 30, 9999);
        System.out.print("Max noise level (dB): ");
        maxNoiseLevel = MyInput.inputInt(in, 20, 140);
        System.out.print("Backlight (1 or 0): ");
        int tmp = MyInput.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;

        // Save fields
        partCooler.setRpm(rpm);
        partCooler.setMaxNoiseLevel(maxNoiseLevel);
        partCooler.setBacklight(backlight);

        return partCooler;
    }

    // Add hardware "CPU"
    private Hardware addPartCPU(Scanner in, Scanner inString) {

        String socket;
        int generation;
        int cores;
        double maxFrequency;

        // Create new part
        HardwareCPU partCPU = new HardwareCPU();

        // Set all general attributes
        partSetGeneralAttr(partCPU, in, inString);

        // Fill fields
        System.out.print("Socket: ");
        socket = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Generation: ");
        generation = MyInput.inputInt(in, 1, 15);
        System.out.print("Cores: ");
        cores = MyInput.inputInt(in, 1, 64);
        System.out.print("Max frequency (GHz): ");
        maxFrequency = MyInput.inputDouble(in, 0.5, 6);

        // Save fields
        partCPU.setSocket(socket);
        partCPU.setGeneration(generation);
        partCPU.setCores(cores);
        partCPU.setMaxFrequency(maxFrequency);

        return partCPU;
    }

    // Add hardware "GPU"
    private Hardware addPartGPU(Scanner in, Scanner inString) {

        int frequency;
        int memory;
        int powerConsumption;

        // Create new part
        HardwareGPU partGPU = new HardwareGPU();

        // Set all general attributes
        partSetGeneralAttr(partGPU, in, inString);

        // Fill fields
        System.out.print("Frequency (MHz): ");
        frequency = MyInput.inputInt(in, 100, 10000);
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 128);
        System.out.print("Power consumption (W): ");
        powerConsumption = MyInput.inputInt(in, 50, 1500);

        // Save fields
        partGPU.setFrequency(frequency);
        partGPU.setMemory(memory);
        partGPU.setPowerConsumption(powerConsumption);

        return partGPU;
    }

    // Add hardware "Motherboard"
    private Hardware addPartMotherboard(Scanner in, Scanner inString) {

        String socket;
        int maxMemoryAmount;
        String formFactor;

        // Create new part
        HardwareMotherboard partMotherboard = new HardwareMotherboard();

        // Set all general attributes
        partSetGeneralAttr(partMotherboard, in, inString);

        // Fill fields
        System.out.print("Socket: ");
        socket = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Max memory (GB): ");
        maxMemoryAmount = MyInput.inputInt(in, 1, 2048);
        System.out.print("Form factor: ");
        formFactor = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);

        // Save fields
        partMotherboard.setSocket(socket);
        partMotherboard.setMaxMemoryAmount(maxMemoryAmount);
        partMotherboard.setFormFactor(formFactor);

        return partMotherboard;
    }

    // Add hardware "Power supply"
    private Hardware addPartPowerSupply(Scanner in, Scanner inString) {

        int powerAmount;

        // Create new part
        HardwarePowerSupply partPowerSupply = new HardwarePowerSupply();

        // Set all general attributes
        partSetGeneralAttr(partPowerSupply, in, inString);

        // Fill field
        System.out.print("Power amount (W): ");
        powerAmount = MyInput.inputInt(in, 50, 5000);

        // Save field
        partPowerSupply.setPowerAmount(powerAmount);

        return partPowerSupply;
    }

    // Add hardware "RAM"
    private Hardware addPartRAM(Scanner in, Scanner inString) {

        int memory;
        double throughput;

        // Create new part
        HardwareRAM partRAM = new HardwareRAM();

        // Set all general attributes
        partSetGeneralAttr(partRAM, in, inString);

        // Fill fields
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 2048);
        System.out.print("Throughput (GB/s): ");
        throughput = MyInput.inputDouble(in, 1, 600);

        // Save fields
        partRAM.setMemory(memory);
        partRAM.setThroughput(throughput);

        return partRAM;
    }

    // Add hardware "SSD"
    private Hardware addPartSSD(Scanner in, Scanner inString) {

        String connector;
        int memory;

        // Create new part
        HardwareSSD partSSD = new HardwareSSD();

        // Set all general attributes
        partSetGeneralAttr(partSSD, in, inString);

        // Fill fields
        System.out.print("Connector (SATA/M.2 etc.): ");
        connector = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 24576);

        // Save fields
        partSSD.setConnector(connector);
        partSSD.setMemory(memory);

        return partSSD;
    }

    // Add hardware "PC"
    private Hardware addPC(Scanner in, Scanner inString) {

        String motherboard;
        String cpu;
        String gpu;
        String ram;
        String ssd;
        String cooler;
        String powerSupply;
        boolean backlight = false;
        String formFactor;

        // Create new computer
        HardwarePC pc = new HardwarePC();

        // Set all general attributes
        partSetGeneralAttr(pc, in, inString);

        // Fill fields
        System.out.print("Motherboard: ");
        motherboard = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("CPU: ");
        cpu = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("GPU: ");
        gpu = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("RAM: ");
        ram = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("SSD: ");
        ssd = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Cooler: ");
        cooler = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Power supply: ");
        powerSupply = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Backlight (1 or 0): ");
        int tmp = MyInput.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;
        System.out.print("Form factor: ");
        formFactor = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);

        // Save fields
        pc.setMotherboard(motherboard);
        pc.setCpu(cpu);
        pc.setGpu(gpu);
        pc.setRam(ram);
        pc.setSsd(ssd);
        pc.setCooler(cooler);
        pc.setPowerSupply(powerSupply);
        pc.setBacklight(backlight);
        pc.setFormFactor(formFactor);

        return pc;
    }

    // Return ArrayList of indexes of some User instances in other ArrayList
    private ArrayList<Integer> returnIndexesOfUsers(ArrayList<User> userArrayList, TypesOfUsers type) {

        // Array to store indexes of managers
        ArrayList<Integer> indexArrayList = new ArrayList<>();

        for (int i = 0; i < userArrayList.size(); i++) {

            // If user of given type is found
            if (userArrayList.get(i).getType().equals(type)) {

                // Add index to ArrayList of indexes
                indexArrayList.add(i);
            }
        }

        return indexArrayList;
    }

    // Return ArrayList of indexes of some Hardware instances in other ArrayList
    // Modes: showSold: 0 - show all, 1 - show only in stock, 2 - show only sold
    private ArrayList<Integer> returnIndexesOfHardware(ArrayList<Hardware> hardwareArrayList, TypesOfHardware type, int showSold) {

        // Array to store indexes of managers
        ArrayList<Integer> indexArrayList = new ArrayList<>();

        for (int i = 0; i < hardwareArrayList.size(); i++) {

            Hardware h = hardwareArrayList.get(i);

            if (showSold == 1) {

                // Do not show sold items
                if (h.getSaleMonth() != 0 && h.getSaleYear() != 0) {

                    continue;
                }
            }

            if (showSold == 2) {

                // Do not show sold items
                if (h.getSaleMonth() == 0 && h.getSaleYear() == 0) {

                    continue;
                }
            }

            // If hardware of given type is found
            if (h.getType().equals(type)) {

                // Add index to ArrayList of indexes
                indexArrayList.add(i);
            }
        }

        return indexArrayList;
    }

    // Print users by type, if type == null, print all
    private void printUsersByType(ArrayList<User> userArrayList, TypesOfUsers type) {

        for (int i = 0, j = 0, tmpSize = userArrayList.size(); i < tmpSize; i++) {

            User u = userArrayList.get(i);

            // If all types, print all
            if (type == null) System.out.printf("%d,%s\n", ++j, u);

            // Else print if type is equal to given
            if (u.getType().equals(type)) {

                System.out.printf("%d,%s\n", ++j, u);
            }
        }
    }

    // Print hardware by type, if type == null, print all
    // Modes: showSold: 0 - show all, 1 - show only in stock, 2 - show only sold
    private void printHardwareByType(ArrayList<Hardware> hardwareArrayList, TypesOfHardware type, int showSold) {

        for (int i = 0, j = 0, tmpSize = hardwareArrayList.size(); i < tmpSize; i++) {

            Hardware h = hardwareArrayList.get(i);

            if (showSold == 1) {

                // Do not show sold items
                if (h.getSaleMonth() != 0 || h.getSaleYear() != 0) {

                    continue;
                }
            }

            if (showSold == 2) {

                // Do not show sold items
                if (h.getSaleMonth() == 0 && h.getSaleYear() == 0) {

                    continue;
                }
            }

            // If all types, print all
            if (type == null) System.out.printf("%d,%s\n", ++j, h);

            else {

                // Else print if type is equal to given
                if (h.getType().equals(type)) {

                    System.out.printf("%d,%s\n", ++j, h);
                }
            }
        }
    }

    // Reusable separate method to print hardware
    // Modes: showSold: 0 - show all, 1 - show only in stock, 2 - show only sold
    private int uiPrintHardware(ArrayList<Hardware> hardwareArrayList, int showSold, Scanner in) {

        System.out.println("Choose part:");
        System.out.println("1 - Case");
        System.out.println("2 - Cooler");
        System.out.println("3 - CPU");
        System.out.println("4 - GPU");
        System.out.println("5 - RAM");
        System.out.println("6 - SSD");
        System.out.println("7 - Motherboard");
        System.out.println("8 - Power supply");
        System.out.print("> ");

        // Chose hardware type
        int choose = MyInput.inputInt(in, 1, 8);
        TypesOfHardware partTypeToFind = null;

        switch (choose) {

            case 1:

                partTypeToFind = TypesOfHardware.CASE;
                break;
            case 2:

                partTypeToFind = TypesOfHardware.COOLER;
                break;
            case 3:

                partTypeToFind = TypesOfHardware.CPU;
                break;
            case 4:

                partTypeToFind = TypesOfHardware.GPU;
                break;

            case 5:

                partTypeToFind = TypesOfHardware.RAM;
                break;
            case 6:

                partTypeToFind = TypesOfHardware.SSD;
                break;

            case 7:

                partTypeToFind = TypesOfHardware.MOTHERBOARD;
                break;
            case 8:

                partTypeToFind = TypesOfHardware.POWER_SUPPLY;
                break;
        }

        // Print hardware
        printHardwareByType(hardwareArrayList, partTypeToFind, showSold);
        return choose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
