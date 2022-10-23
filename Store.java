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

    private final static int MIN_LENGTH = 1;
    private final static int MAX_LENGTH = 32;

    private final static String DB_HARDWARE_DAT = "db/hardware.dat";
    private final static String DB_HARDWARE_CSV = "out/hardware.csv";

    private final static int SHOW_ALL = 0;
    private final static int SHOW_IN_STOCK = 1;
    private final static int SHOW_SOLD = 2;

    private String name;

    public Store(String name) {
        this.name = name;
    }

    // TODO: Complete
    public void runAsCustomer(User currentUser, ArrayList<User> userArrayList) {

        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US);

        // List of computer parts
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        // TODO: Delete
//        hardwareArrayList.add(new HardwareCooler(2000, "not sold 0", "manufacturer1", "color", 3200, 65, true));
//        hardwareArrayList.add(new HardwareCooler(2000, "name2", "manufacturer2", "color", 3000, 80, true));
//        hardwareArrayList.get(1).setSaleYear(2022);
//        hardwareArrayList.get(1).setSaleMonth(5);
//        hardwareArrayList.add(new HardwareCooler(2000, "not sold 2", "manufacturer3", "color", 3200, 65, true));
//        hardwareArrayList.add(new HardwareCooler(2000, "name4", "manufacturer4", "color", 3200, 65, true));
//        hardwareArrayList.add(new HardwarePC(25000, "name1 (index 4)", "manufacturer1", "color1", "motherboard1", "cpu1", "gpu1",
//                "ram1", "ssd1", "cooler1", "power1", false, "ATX"));
//        hardwareArrayList.get(3).setSaleYear(2022);
//        hardwareArrayList.get(3).setSaleMonth(6);
//        hardwareArrayList.add(new HardwareCooler(1234, "not sold 4", "manufacturer666", "666", 1200, 80, false));
//        hardwareArrayList.add(new HardwarePC(25000, "name1 (index 6)", "manufacturer1", "color1", "motherboard1", "cpu1", "gpu1",
//                "ram1", "ssd1", "cooler1", "power1", false, "ATX"));

        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.print("How much money do you have today:");

        ArrayList<Hardware> shoppingCartArrayList = new ArrayList<>();
        int customerMoney = MyInput.inputInt(in, 1, 99999999);

        // Array list to store indexes of users of some class
        ArrayList<Integer> indexArrayListOfClass = new ArrayList<>();

        indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

        if (indexArrayListOfClass.size() == 0) {

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

                    uiPrintHardware(hardwareArrayList, SHOW_IN_STOCK, in);
                    break;

                // Buy hardware or computer
                case 2: // hardware
                case 3: // computer

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

                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose to buy (index): ");

                        int indexToAdd = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        shoppingCartArrayList.add(hardwareArrayList.get(indexArrayListOfClass.get(indexToAdd - 1)));
                        hardwareArrayList.remove((int) indexArrayListOfClass.get(indexToAdd - 1));

                        System.out.println("Success!");
                    } else System.out.println("No such hardware!");

                    break;

                // View shopping cart
                case 4:

                    System.out.println("4 - View shopping cart");

                    if (shoppingCartArrayList.size() == 0) {

                        System.out.println("Please, add some items to card first!");
                    }

                    for (Hardware h : shoppingCartArrayList) {
                        System.out.println(h);
                    }

                    break;

                // Checkout
                case 5:

                    if (shoppingCartArrayList.size() == 0) {

                        System.out.println("Add items first!");
                        break;
                    }

                    int sum = 0;

                    for (Hardware h :
                            shoppingCartArrayList) {
                        sum += h.getPrice();
                    }

                    if (sum > customerMoney) {

                        System.out.println("Not enough money!");
                        System.out.print("Needed: " + sum + ", present: " + customerMoney);
                    }
                    else {

                        System.out.println("Enter date of sell:");
                        System.out.print("Year: ");
                        int year = MyInput.inputInt(in, 1900, 3000);
                        System.out.print("Month: ");
                        int month = MyInput.inputInt(in, 1, 12);

                        System.out.println("What manager helped you to buy stuff?");

                        int userArrayListSize = userArrayList.size();
                        for (int i = 0, j = 0; i < userArrayListSize; i++) {
                            User u = userArrayList.get(i);

                            if (u.getType() == TypesOfUsers.MANAGER) {

                                System.out.printf("%d,%s,%s\n", j + 1, u.getFirstName(), u.getSurName());
                                j++;
                            }
                        }

                        indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                        System.out.print("> ");
                        choose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        Manager m = (Manager) userArrayList.get(indexArrayListOfClass.get(choose - 1));

                        for (Hardware h :
                                shoppingCartArrayList) {

                            // Save selling date
                            h.setSaleYear(year);
                            h.setSaleMonth(month);

                            // Add selling to manager
                            m.addSold(h);
                        }

                        shoppingCartArrayList = null;
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

                        // Return all items
                        int tmpSize = shoppingCartArrayList.size();
                        for (int i = 0; i < tmpSize; i++) {

                            hardwareArrayList.add(shoppingCartArrayList.get(0));
                            shoppingCartArrayList.remove(0);
                        }
                    }

                    MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false);
                    return;
            }
        }
    }

    public void runAsManager(User currentUser, ArrayList<User> userArrayList) {

        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        File fileHardwareCSV = new File(DB_HARDWARE_CSV);
        MyFiles.createFile(fileHardwareCSV);

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US);

        // List of computer parts
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.println("Store menu:");

        System.out.println("1 - Add part");
        System.out.println("2 - Add computer");
        System.out.println("3 - Print some stock");
        System.out.println("4 - Print all stock");
        System.out.println("5 - Print sold hardware by month");
        System.out.println("6 - Save all hardware to " + DB_HARDWARE_CSV);
        System.out.println("0 - Close store");

        // TODO: Delete
        hardwareArrayList.add(new HardwareCooler(2000, "name1", "manufacturer1", "color", 3200, 65, true));
        hardwareArrayList.get(0).setSaleYear(2022);
        hardwareArrayList.get(0).setSaleMonth(5);
        hardwareArrayList.add(new HardwareCooler(2000, "name2", "manufacturer2", "color", 3000, 80, true));
        hardwareArrayList.get(1).setSaleYear(2022);
        hardwareArrayList.get(1).setSaleMonth(5);
        hardwareArrayList.add(new HardwareCooler(2000, "name3", "manufacturer3", "color", 3200, 65, true));
        hardwareArrayList.get(2).setSaleYear(2022);
        hardwareArrayList.get(2).setSaleMonth(6);
        hardwareArrayList.add(new HardwareCooler(2000, "name4", "manufacturer4", "color", 3200, 65, true));
        hardwareArrayList.get(3).setSaleYear(2022);
        hardwareArrayList.get(3).setSaleMonth(6);

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

                    if (hardwareArrayList.size() == 0) {

                        System.out.println("Add some hardware first!");
                        break;
                    }

                    uiPrintHardware(hardwareArrayList, SHOW_IN_STOCK, in);

                    break;

                // Print all stock
                case 4:

                    System.out.println("4 - Print all stock");

                    if (hardwareArrayList.size() == 0) {

                        System.out.println("Add some hardware first!");
                        break;
                    }

                    for (Hardware h: hardwareArrayList) {
                        System.out.println(h);
                    }
                    break;

                // Print sold hardware by month
                case 5:

                    System.out.println("5 - Print sold hardware by month");

                    int month;
                    int year;

                    System.out.print("Year: ");
                    year = MyInput.inputInt(in, 1900, 3000);
                    System.out.print("Month: ");
                    month = MyInput.inputInt(in, 1, 12);

                    System.out.printf("Sold on %02d.%4d:\n", month, year);

                    ArrayList<Hardware> soldParts;
                    Manager curManager = (Manager) currentUser;

                    soldParts = curManager.getSoldParts();

                    for (Hardware h : soldParts) {

                        if (h.getSaleYear() == year && h.getSaleMonth() == month) {

                            System.out.println(h);
                        }
                    }

                    break;

                // Save hardware to CSV file
                case 6:
                    System.out.println("5 - Save all hardware to " + DB_HARDWARE_CSV);

                    if ( MyFiles.fileWriterArrayList(hardwareArrayList, fileHardwareCSV)) {

                        System.out.println("Success!");
                    }

                    break;

                // Close store
                case 0:

                    System.out.println("0 - Close store");

                    MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false);
                    return;
            }
        }

    }

    public void runAsAdmin(User currentUser, ArrayList<User> userArrayList) {

        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US);

        // List of computer parts
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.println("Store menu:");

        // Array list to store indexes of users of some class
        ArrayList<Integer> indexArrayListOfClass;

        System.out.println("1 - View managers");
        System.out.println("2 - View customers");
        System.out.println("3 - Print manager sales");
        System.out.println("4 - Set salary for manager");
        System.out.println("5 - Fire manager");
        System.out.println("6 - Delete customer");
        System.out.println("0 - Close store");

        while (true) {

            System.out.print("> ");

            switch (MyInput.inputInt(in, 0, 6)) {

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

                // 3 - Print manager sales
                case 3:

                    System.out.println("3 - Print manager sales");
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose manager:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        System.out.println(userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));

                        Manager tmpManager = (Manager) userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1));
                        ArrayList<Hardware> soldParts = tmpManager.getSoldParts();
                        for (Hardware h :
                                soldParts) {
                            System.out.println(h);
                        }
                    } else System.out.println("Managers not found!");

                    break;

                // 4 - Set salary for manager
                case 4:

                    System.out.println("4 - Set salary for manager");
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose manager:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        Manager curManager = (Manager) userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1));

                        System.out.println(userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));

                        System.out.println("New salary (1-999999): ");
                        curManager.setSalary(MyInput.inputInt(in, 1, 999999));

                        System.out.println(userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));
                        System.out.println("Success!");
                    } else System.out.println("Managers not found!");

                    break;

                // 5 - Fire manager
                case 5:

                    System.out.println("5 - Fire manager");
                    printUsersByType(userArrayList, TypesOfUsers.MANAGER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.MANAGER);

                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose manager:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        System.out.println("DELETED: " + userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));
                        userArrayList.remove((int) indexArrayListOfClass.get(tmpChoose - 1));
                        System.out.println("Success!");
                    } else System.out.println("Managers not found!");

                    break;

                // 6 - Delete customer
                case 6:

                    System.out.println("6 - Delete customer");
                    printUsersByType(userArrayList, TypesOfUsers.CUSTOMER);
                    indexArrayListOfClass = returnIndexesOfUsers(userArrayList, TypesOfUsers.CUSTOMER);

                    if (indexArrayListOfClass.size() > 0) {

                        System.out.println("Choose customer:");
                        int tmpChoose = MyInput.inputInt(in, 1, indexArrayListOfClass.size());

                        System.out.println("DELETED: " + userArrayList.get(indexArrayListOfClass.get(tmpChoose - 1)));
                        userArrayList.remove((int) indexArrayListOfClass.get(tmpChoose - 1));
                        System.out.println("Success!");
                    } else System.out.println("Managers not found!");

                    break;

                // Close store
                case 0:

                    MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false);
                    return;
            }
        }
    }

    // Set shared attributes
    private void partSetGeneralAttr(Hardware hardware, Scanner in, Scanner inString) {

//        int price;
//        String name;
//        String manufacturer;
//        String color;
//
//        System.out.print("Name: ");
//        name = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//        System.out.print("Manufacturer: ");
//        manufacturer = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//        System.out.print("Price: ");
//        price = MyInput.inputInt(in, 1, 9999999);
//        System.out.print("Color: ");
//        color = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//
//        hardware.setPrice(price);
//        hardware.setName(name);
//        hardware.setManufacturer(manufacturer);
//        hardware.setColor(color);
    }

    private Hardware addPartCase(Scanner in, Scanner inString) {

        String formFactor;
        double weight;
        boolean backlight = false;

        HardwareCase partCase = new HardwareCase();

        partSetGeneralAttr(partCase, in, inString);

        System.out.print("Form factor:");
        formFactor = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Weight (kg):");
        weight = MyInput.inputDouble(in, 1., 99.);
        System.out.print("Backlight (1 or 0): ");
        int tmp = MyInput.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;

        partCase.setFormFactor(formFactor);
        partCase.setWeight(weight);
        partCase.setBacklight(backlight);

        return partCase;
    }

    private Hardware addPartCooler(Scanner in, Scanner inString) {

        int rpm;
        int maxNoiseLevel;
        boolean backlight = false;

        HardwareCooler partCooler = new HardwareCooler();

        partSetGeneralAttr(partCooler, in, inString);

        System.out.print("RPM: ");
        rpm = MyInput.inputInt(in, 30, 9999);
        System.out.print("Max noise level (dB): ");
        maxNoiseLevel = MyInput.inputInt(in, 20, 140);
        System.out.print("Backlight (1 or 0): ");
        int tmp = MyInput.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;

        partCooler.setRpm(rpm);
        partCooler.setMaxNoiseLevel(maxNoiseLevel);
        partCooler.setBacklight(backlight);

        return partCooler;
    }

    private Hardware addPartCPU(Scanner in, Scanner inString) {

        String socket;
        int generation;
        int cores;
        double maxFrequency;

        HardwareCPU partCPU = new HardwareCPU();

        partSetGeneralAttr(partCPU, in, inString);

        System.out.print("Socket: ");
        socket = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Generation: ");
        generation = MyInput.inputInt(in, 1, 15);
        System.out.print("Cores: ");
        cores = MyInput.inputInt(in, 1, 64);
        System.out.print("Max frequency (GHz): ");
        maxFrequency = MyInput.inputDouble(in, 0.5, 6);

        partCPU.setSocket(socket);
        partCPU.setGeneration(generation);
        partCPU.setCores(cores);
        partCPU.setMaxFrequency(maxFrequency);

        return partCPU;
    }

    private Hardware addPartGPU(Scanner in, Scanner inString) {

        int frequency;
        int memory;
        int powerConsumption;

        HardwareGPU partGPU = new HardwareGPU();

        partSetGeneralAttr(partGPU, in, inString);

        System.out.print("Frequency (MHz): ");
        frequency = MyInput.inputInt(in, 100, 10000);
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 128);
        System.out.print("Power consumption (W): ");
        powerConsumption = MyInput.inputInt(in, 50, 1500);

        partGPU.setFrequency(frequency);
        partGPU.setMemory(memory);
        partGPU.setPowerConsumption(powerConsumption);

        return partGPU;
    }

    private Hardware addPartMotherboard(Scanner in, Scanner inString) {

        String socket;
        int maxMemoryAmount;
        String formFactor;

        HardwareMotherboard partMotherboard = new HardwareMotherboard();

        partSetGeneralAttr(partMotherboard, in, inString);

        System.out.print("Socket: ");
        socket = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Max memory (GB): ");
        maxMemoryAmount = MyInput.inputInt(in, 1, 2048);
        System.out.print("Form factor: ");
        formFactor = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);

        partMotherboard.setSocket(socket);
        partMotherboard.setMaxMemoryAmount(maxMemoryAmount);
        partMotherboard.setFormFactor(formFactor);

        return partMotherboard;
    }

    private Hardware addPartPowerSupply(Scanner in, Scanner inString) {

        int powerAmount;

        HardwarePowerSupply partPowerSupply = new HardwarePowerSupply();

        partSetGeneralAttr(partPowerSupply, in, inString);

        System.out.print("Power amount (W): ");
        powerAmount = MyInput.inputInt(in, 50, 5000);

        partPowerSupply.setPowerAmount(powerAmount);

        return partPowerSupply;
    }

    private Hardware addPartRAM(Scanner in, Scanner inString) {

        int memory;
        double throughput;

        HardwareRAM partRAM = new HardwareRAM();

        partSetGeneralAttr(partRAM, in, inString);

        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 2048);
        System.out.print("Throughput (GB/s): ");
        throughput = MyInput.inputDouble(in, 1, 600);

        partRAM.setMemory(memory);
        partRAM.setThroughput(throughput);

        return partRAM;
    }

    private Hardware addPartSSD(Scanner in, Scanner inString) {

        String connector;
        int memory;

        HardwareSSD partSSD = new HardwareSSD();

        partSetGeneralAttr(partSSD, in, inString);

        System.out.print("Connector (SATA/M.2 etc.): ");
        connector = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 24576);

        partSSD.setConnector(connector);
        partSSD.setMemory(memory);

        return partSSD;
    }

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

        HardwarePC pc = new HardwarePC();

        partSetGeneralAttr(pc, in, inString);

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

            if (userArrayList.get(i).getType().equals(type)) {

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

            if (h.getType().equals(type)) {

                indexArrayList.add(i);
            }
        }

        return indexArrayList;
    }

    // Print users by type, if type == null, print all
    private void printUsersByType(ArrayList<User> userArrayList, TypesOfUsers type) {

        for (int i = 0, j = 0, tmpSize = userArrayList.size(); i < tmpSize; i++) {

            User u = userArrayList.get(i);
            if (type == null) System.out.printf("%d,%s\n", ++j, u);
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

            if (type == null) System.out.printf("%d,%s\n", ++j, h);
            else {

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
