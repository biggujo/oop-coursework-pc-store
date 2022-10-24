package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyFiles {

    // Write some ArrayList to binary file
    public static <T> boolean serializeArrayList(ArrayList<T> arrayList, File file, boolean append) {

        try (FileOutputStream fileOS = new FileOutputStream(file, append);
             ObjectOutputStream oos = new ObjectOutputStream(fileOS)) {

            oos.writeObject(arrayList);
            return true;
        }
        catch (IOException e) {

            return false;
        }
    }

    // Read some ArrayList from binary file
    public static <T> ArrayList<T> deserializeArrayList(File file) {

        ArrayList<T> arrayList;

        try (FileInputStream fileIS = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fileIS)) {

            arrayList = (ArrayList<T>) ois.readObject();
            return arrayList;
        }
        catch (IOException | ClassNotFoundException e) {

            return new ArrayList<>();
        }
    }

    // Save all objects from file
    public static <T> boolean serializeObject(T obj, File file, boolean append) {

        try (FileOutputStream fileOS = new FileOutputStream(file, append);
             ObjectOutputStream oos = new ObjectOutputStream(fileOS)) {

            oos.writeObject(obj);
            return true;
        }
        catch (IOException e) {

            return false;
        }
    }

    // Read one object from file
    public static <T> T deserializeObject(File file) {

        try (FileInputStream fileIS = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fileIS)) {

            return (T) ois.readObject();
        }
        catch (FileNotFoundException e) {

            System.out.println("Error reading from " + file.getPath());
            return null;
        }
        catch (IOException | ClassNotFoundException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    // Read one object to some ArrayList
    public static <T> void deserializeAllObjects(ArrayList<T> arrayList, File file) {

        T obj;

        try (FileInputStream fileIS = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fileIS)) {

            while ((obj = (T) ois.readObject()) != null) {

                arrayList.add(obj);
            }
        }
        catch (FileNotFoundException e) {

            System.out.println("Error reading from " + file.getPath());
        }
        catch (IOException | ClassNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // Write to file ArrayList line by line
    public static <T> boolean fileWriterArrayList(ArrayList<T> arrayList, File file) {

        try (FileWriter fileWriter = new FileWriter(file)) {

            for (T t :
                    arrayList) {
                fileWriter.write(t.toString() + '\n');
            }
            return true;
        }
        catch (IOException e) {

            return false;
        }
    }

    // Create file and path if were not existed
    public static void createFile(File file) {

        if (!file.exists()) {

            try {
                Files.createDirectories(Paths.get(file.getParent()));
                if (!file.createNewFile()) {

                    throw new IOException();
                }
            } catch (IOException e) {

                System.out.println("Error creating " + file.getPath());
            }
        }
    }
}
