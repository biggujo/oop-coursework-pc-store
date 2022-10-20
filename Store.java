import Hardware.*;
import Users.TypesOfUsers;
import Users.User;
import Utils.MyFiles;
import Utils.MyInput;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Store {

    private final static int MIN_PRICE = 1;
    private final static int MAX_PRICE = 1000000;

    private final static int MIN_LENGTH = 1;
    private final static int MAX_LENGTH = 32;

    private final static String DB_HARDWARE_DAT = "db/hardware.dat";

    private String name;

    public Store(String name) {
        this.name = name;
    }

    public void run(User currentUser, ArrayList<User> userArrayList) {

        File fileHardwareDB = new File(DB_HARDWARE_DAT);
        MyFiles.createFile(fileHardwareDB);

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US);

        // List of computer parts
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        if (fileHardwareDB.length() > 0) hardwareArrayList = MyFiles.deserializeArrayList(fileHardwareDB);

        System.out.println("Store menu:");

        // If manager
        if (currentUser.getType().equals(TypesOfUsers.MANAGER.toString())) {

            System.out.println("1 - Add part");
            System.out.println("2 - Add computer");
            System.out.println("3 - Print some stock");
            System.out.println("4 - Print all stock");
            System.out.println("0 - Close store");

            while (true) {

                System.out.print("> ");

                switch (MyInput.inputInt(in, 0, 4)) {

                    // Add part
                    case 1:
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


                    // TODO: Add computers
                    case 2:

                        break;

                    // Print some stock
                    case 3:

                        if (hardwareArrayList.size() == 0) {

                            System.out.println("Add some hardware first!");
                            break;
                        }

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
                        String partTypeToFind = null;

                        switch (choose) {

                            case 1:

                                partTypeToFind = TypesOfHardware.CASE.toString();
                                break;
                            case 2:

                                partTypeToFind = TypesOfHardware.COOLER.toString();
                                break;
                            case 3:

                                partTypeToFind = TypesOfHardware.CPU.toString();
                                break;
                            case 4:

                                partTypeToFind = TypesOfHardware.GPU.toString();
                                break;

                            case 5:

                                partTypeToFind = TypesOfHardware.RAM.toString();
                                break;
                            case 6:

                                partTypeToFind = TypesOfHardware.SSD.toString();
                                break;

                            case 7:

                                partTypeToFind = TypesOfHardware.MOTHERBOARD.toString();
                                break;
                            case 8:

                                partTypeToFind = TypesOfHardware.POWER_SUPPLY.toString();
                                break;
                        }

                        // Print parts of given type
                        for (Hardware h: hardwareArrayList) {
                            if (h.getPartType().equals(partTypeToFind)) {

                                System.out.println(h);
                            }
                        }
                        break;

                    // Print all stock
                    case 4:

                        if (hardwareArrayList.size() == 0) {

                            System.out.println("Add some hardware first!");
                            break;
                        }

                        for (Hardware h: hardwareArrayList) {
                            System.out.println(h);
                        }
                        break;

                    // Close store
                    case 0:

                        MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false);
                        return;
                }
            }
        }

        if (currentUser.getType().equals(TypesOfUsers.ADMIN.toString())) {

            ArrayList<Integer> indexArrayListOfClass;

            System.out.println("1 - View managers");
            System.out.println("2 - View customers");
            System.out.println("3 - Print manager sales");
            System.out.println("3 - Set salary for manager");
            System.out.println("4 - Fire manager");
            System.out.println("5 - Delete customer");
            System.out.println("0 - Close store");

            while (true) {

                System.out.print("> ");

                switch (MyInput.inputInt(in, 0, 5)) {

                    // 1 - View managers
                    case 1:

                        System.out.println("1 - View managers");

                        for (int i = 0, j = 0, tmpSize = userArrayList.size(); i < tmpSize; i++) {

                            User u = userArrayList.get(i);
                            if (u.getType().equals(TypesOfUsers.MANAGER.toString())) {

                                System.out.printf("%d,%s\n", ++j, u);
                            }
                        }
                        break;

                    // 2 - View customers
                    case 2:

                        System.out.println("2 - View customers");

                        break;

                    // 3 - Print manager sales
                    case 3:

                        System.out.println("3 - Print manager sales");

                        indexArrayListOfClass = returnIndexesOfClass(userArrayList, TypesOfUsers.MANAGER.toString());

                        System.out.println();

                        break;

                    case 4:

                        break;

                    // Close store
                    case 0:

                        MyFiles.serializeArrayList(hardwareArrayList, fileHardwareDB, false);
                        return;
                }
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
//        price = MyInput.inputInt(in, MIN_PRICE, MAX_PRICE);
//        System.out.print("Color: ");
//        color = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//
//        hardwarePart.setPrice(price);
//        hardwarePart.setName(name);
//        hardwarePart.setManufacturer(manufacturer);
//        hardwarePart.setColor(color);
    }

    private Hardware addPartCase(Scanner in, Scanner inString) {

        String formFactor;
        double weight;
        boolean backlight = false;

        HardwareCase partCase = new HardwareCase(TypesOfHardware.CASE.toString());

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

        HardwareCooler partCooler = new HardwareCooler(TypesOfHardware.COOLER.toString());

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

        HardwareCPU partCPU = new HardwareCPU(TypesOfHardware.CPU.toString());

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

        HardwareGPU partGPU = new HardwareGPU(TypesOfHardware.GPU.toString());

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

        HardwareMotherboard partMotherboard = new HardwareMotherboard(TypesOfHardware.MOTHERBOARD.toString());

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

        HardwarePowerSupply partPowerSupply = new HardwarePowerSupply(TypesOfHardware.POWER_SUPPLY.toString());

        partSetGeneralAttr(partPowerSupply, in, inString);

        System.out.print("Power amount (W): ");
        powerAmount = MyInput.inputInt(in, 50, 5000);

        partPowerSupply.setPowerAmount(powerAmount);

        return partPowerSupply;
    }

    private Hardware addPartRAM(Scanner in, Scanner inString) {

        int memory;
        double throughput;

        HardwareRAM partRAM = new HardwareRAM(TypesOfHardware.RAM.toString());

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

        HardwareSSD partSSD = new HardwareSSD(TypesOfHardware.SSD.toString());

        partSetGeneralAttr(partSSD, in, inString);

        System.out.print("Connector (SATA/M.2 etc.): ");
        connector = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 24576);

        partSSD.setConnector(connector);
        partSSD.setMemory(memory);

        return partSSD;
    }

    // Return ArrayList of indexes of some class instances in other ArrayList
    private ArrayList<Integer> returnIndexesOfClass(ArrayList<User> userArrayList, String type) {

        // Array to store indexes of managers
        ArrayList<Integer> indexArrayList = new ArrayList<>();

        for (int i = 0; i < userArrayList.size(); i++) {

            if (userArrayList.get(i).getType().equals(type)) {

                indexArrayList.add(i);
            }
        }

        return indexArrayList;
    }

//    private

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
