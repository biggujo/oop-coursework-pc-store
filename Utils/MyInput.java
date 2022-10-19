package Utils;

import java.util.Scanner;

// Class to return proper input (values in proper ranges or string with length in some range)
public class MyInput {
    public static int inputInt(Scanner in, int min, int max) {

        int value;

        while (true) {

            try {
                value = in.nextInt();

                if (value < min || value > max) {

                    throw new RuntimeException();
                }
                return value;
            }
            catch (Exception e) {

                System.out.println("Please, enter a number between " + min + " and " + max);
                in.nextLine();
            }
        }
    }

    public static double inputDouble(Scanner in, double min, double max) {

        double value;

        while (true) {

            try {
                value = in.nextDouble();

                if (value < min || value > max) {

                    throw new RuntimeException();
                }
                return value;
            }
            catch (Exception e) {

                System.out.println("Please, enter a number between " + min + " and " + max);
                in.nextLine();
            }
        }
    }

    public static String inputString(Scanner in, int minLen, int maxLen) {

        String string;

        while (true) {

            try {

                in.reset();

                string = in.nextLine();

                if (string.length() < minLen || string.length() > maxLen) {

                    throw new RuntimeException();
                }
                return string;
            }
            catch (Exception e) {

                System.out.println("Please, enter a string length of between " + minLen + " and " + maxLen);
            }
        }
    }
}
