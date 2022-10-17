import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyFiles {

    // Write some ArrayList to binary file
    public static <T> boolean serializeArrayList(ArrayList<T> arrayList, File fileDB) {

        try (FileOutputStream fileOS = new FileOutputStream(fileDB, false);
             ObjectOutputStream oos = new ObjectOutputStream(fileOS)) {

            oos.writeObject(arrayList);
            return true;
        }
        catch (IOException e) {

            System.out.println("Error saving to " + fileDB.getPath());
            return false;
        }
    }

    // Read some ArrayList from binary file
    public static <T> ArrayList<T> deserializeArrayList(File fileDB) {

        ArrayList<T> arrayList;

        try (FileInputStream fileIS = new FileInputStream(fileDB);
             ObjectInputStream ois = new ObjectInputStream(fileIS)) {

            arrayList = (ArrayList<T>) ois.readObject();
            return arrayList;
        }
        catch (IOException e) {

            System.out.println("Error reading from " + fileDB.getPath());
            return new ArrayList<>();
        }
        catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    // Save one object to file
    public static <T> boolean serializeObject(T obj, File fileDB) {

        try (FileOutputStream fileOS = new FileOutputStream(fileDB, false);
             ObjectOutputStream oos = new ObjectOutputStream(fileOS)) {

            oos.writeObject(obj);
            return true;
        }
        catch (IOException e) {

            System.out.println("Error saving to " + fileDB.getPath());
            return false;
        }
    }

    // Read one object from file
    public static <T> T deserializeObject(File fileDB) {

        T obj;

        try (FileInputStream fileIS = new FileInputStream(fileDB);
             ObjectInputStream ois = new ObjectInputStream(fileIS)) {

            obj = (T) ois.readObject();
            return obj;
        }
        catch (IOException e) {

            System.out.println("Error reading from " + fileDB.getPath());
            return null;
        }
        catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
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
