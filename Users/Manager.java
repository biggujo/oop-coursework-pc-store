package Users;

import Hardware.Hardware;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Manager extends Customer implements Serializable {

    private String middleName;
    private LocalDate birthDate;
    private String jobTitle;

    private int salary;
    private ArrayList<Hardware> soldParts = new ArrayList<>();

    public Manager(String firstName, String surName, String middleName, LocalDate birthDate, String jobTitle,
                   String phone, String password, int salary) {
        super(firstName, surName, phone, password);
        this.setType(TypesOfUsers.MANAGER);
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Hardware getSold(int index) {

        return soldParts.get(index);
    }

    public void addSold(Hardware hardware) {

        soldParts.add(hardware);
    }

    public ArrayList<Hardware> getSoldParts() {

        return soldParts;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",",
                super.toString().substring(0, super.toString().length() - 2) + ",", "}");

        stringJoiner.add(middleName).add(birthDate.toString()).add(jobTitle).
                add(Integer.toString(salary)).add("Sales: " + soldParts.size());

        return stringJoiner.toString();
    }
    
    public void soldPartsToString() {

        for (Hardware h :
                soldParts) {
            System.out.println(h);
        }
    }
}
