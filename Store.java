import Parts.Part;
import Parts.PartCase;
import Parts.PartCooler;

import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    private static int MIN_PRICE = 1;
    private static int MAX_PRICE = 1000000;

    private static int MIN_LENGTH = 1;
    private static int MAX_LENGTH = 32;

    // List of computer parts
    ArrayList<Parts.Part> partArrayList = new ArrayList<>();

    Scanner in = new Scanner(System.in);
    Scanner inString = new Scanner(System.in);

//    public static void run() {
//
//
//    }

    private static void partAddGeneralAttr(Part part, Scanner in, Scanner inString) {

        int price;
        String name;
        String manufacturer;
        String color;

        System.out.print("Price: ");
        price = Input.inputInt(in, MIN_PRICE, MAX_PRICE);
        System.out.print("Name: ");
        name = Input.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Manufacturer: ");
        manufacturer = Input.inputString(inString, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Color: ");
        color = Input.inputString(inString, MIN_LENGTH, MAX_LENGTH);

        part.setPrice(price);
        part.setName(name);
        part.setManufacturer(manufacturer);
        part.setColor(color);

    }

    private static void addPartCase(ArrayList<Parts.Part> partArrayList, Scanner in, Scanner inString) {

        String formFactor;
        double weight;
        boolean backlight = false;

        PartCase partCase = new PartCase();

        partAddGeneralAttr(partCase, in, inString);

        System.out.print("Form factor:");
        formFactor = Input.inputString(in, MIN_LENGTH, MAX_LENGTH);
        System.out.print("Weight:");
        weight = Input.inputDouble(in, 1, 99);
        System.out.print("Backlight (1 or 0): ");
        int tmp = Input.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;

        partCase.setFormFactor(formFactor);
        partCase.setWeight(weight);
        partCase.setBacklight(backlight);

    }

    private static void addPartCooler(ArrayList<Parts.Part> partArrayList, Scanner in, Scanner inString) {

        ArrayList<String> socketArrayList = new ArrayList<>();
        int rpm;
        int maxNoiseLevel;
        boolean backlight = false;

        PartCooler partCooler = new PartCooler();

        partAddGeneralAttr(partCooler, in, inString);

        System.out.println("Socket (one for each line, 0 to end):");
        while (true) {

            String curSocket = Input.inputString(in, MIN_LENGTH, MAX_LENGTH);

            if (!curSocket.equals("0")) {

                socketArrayList.add(curSocket);
            } else break;
        }
        System.out.print("RPM: ");
        rpm = Input.inputInt(in, 30, 9999);
        System.out.print("Max noise level (dB): ");
        maxNoiseLevel = Input.inputInt(in, 20, 140);
        System.out.print("Backlight (1 or 0): ");
        int tmp = Input.inputInt(in, 0, 1);
        if (tmp == 1) backlight = true;

        partCooler.setSocketArrayList(socketArrayList);
        partCooler.setRpm(rpm);
        partCooler.setMaxNoiseLevel(maxNoiseLevel);
        partCooler.setBacklight(backlight);
    }
}
