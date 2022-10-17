import Parts.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Store {

    private int MIN_PRICE = 1;
    private int MAX_PRICE = 1000000;

    private int MIN_LENGTH = 1;
    private int MAX_LENGTH = 32;

    private String name;

    // List of computer parts
    private ArrayList<Parts.Part> partArrayList = new ArrayList<>();

    public Store(String name) {
        this.name = name;
    }

    public void run() {

        Scanner in = new Scanner(System.in);
        Scanner inString = new Scanner(System.in);
        in.useLocale(Locale.US);

        partArrayList.add(addPartSSD(in, inString));

        System.out.println();
        System.out.println();
    }

    // Set shared attributes
    private void partSetGeneralAttr(Part part, Scanner in, Scanner inString) {


//        int price;
//        String name;
//        String manufacturer;
//        String color;
//
//        System.out.print("Price: ");
//        price = Input.inputInt(in, MIN_PRICE, MAX_PRICE);
//        System.out.print("Name: ");
//        name = Input.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//        System.out.print("Manufacturer: ");
//        manufacturer = Input.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//        System.out.print("Color: ");
//        color = Input.inputString(inString, MIN_LENGTH, MAX_LENGTH);
//
//        part.setPrice(price);
//        part.setName(name);
//        part.setManufacturer(manufacturer);
//        part.setColor(color);
    }

    private Part addPartCase(Scanner in, Scanner inString) {

        String formFactor;
        double weight;
        boolean backlight = false;

        PartCase partCase = new PartCase("Case");

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

    private Part addPartCooler(Scanner in, Scanner inString) {

        int rpm;
        int maxNoiseLevel;
        boolean backlight = false;

        PartCooler partCooler = new PartCooler("Cooler");

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

    private Part addPartCPU(Scanner in, Scanner inString) {

        String socket;
        int generation;
        int cores;
        double maxFrequency;

        PartCPU partCPU = new PartCPU("CPU");

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

    private Part addPartGPU(Scanner in, Scanner inString) {

        int frequency;
        int memory;
        int powerConsumption;

        PartGPU partGPU = new PartGPU("GPU");

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

    private Part addPartMotherboard(Scanner in, Scanner inString) {

        String socket;
        int maxMemoryAmount;
        String formFactor;

        PartMotherboard partMotherboard = new PartMotherboard("Motherboard");

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

    private Part addPartPowerSupply(Scanner in, Scanner inString) {

        int powerAmount;

        PartPowerSupply partPowerSupply = new PartPowerSupply("Power supply");

        partSetGeneralAttr(partPowerSupply, in, inString);

        System.out.print("Power amount (W): ");
        powerAmount = MyInput.inputInt(in, 50, 5000);

        partPowerSupply.setPowerAmount(powerAmount);

        return partPowerSupply;
    }

    private Part addPartRAM(Scanner in, Scanner inString) {

        int memory;
        double throughput;

        PartRAM partRAM = new PartRAM("RAM");

        partSetGeneralAttr(partRAM, in, inString);

        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 2048);
        System.out.print("Throughput (GB/s): ");
        throughput = MyInput.inputDouble(in, 1, 600);

        partRAM.setMemory(memory);
        partRAM.setThroughput(throughput);

        return partRAM;
    }

    private Part addPartSSD(Scanner in, Scanner inString) {

        String connector;
        int memory;

        PartSSD partSSD = new PartSSD("SSD");

        partSetGeneralAttr(partSSD, in, inString);

        System.out.print("Connector (SATA/M.2 etc.): ");
        connector = MyInput.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Memory (GB): ");
        memory = MyInput.inputInt(in, 1, 24576);

        partSSD.setConnector(connector);
        partSSD.setMemory(memory);

        return partSSD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
