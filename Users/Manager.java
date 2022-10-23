package Users;

import Hardware.Hardware;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Manager extends User implements Serializable {

    private String middleName;
    private LocalDate birthDate;
    private String jobTitle;
    private String phone;

    private int salary;
    private ArrayList<Hardware> soldParts = new ArrayList<>();

    public Manager(String firstName, String surName, String middleName, LocalDate birthDate, String jobTitle,
                   String phone, String password, int salary) {
        super(TypesOfUsers.MANAGER, firstName, surName, password);
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

        return super.getType().toString() + ',' + super.getFirstName() + ',' + super.getSurName() + ',' + middleName + ',' +
                birthDate.toString() + ',' + jobTitle + ',' + phone + ',' + salary + ',' + super.getPassword() +
                ',' + "Sales: " + soldParts.size();
    }
    
    public void soldPartsToString() {

        for (Hardware h :
                soldParts) {
            System.out.println(h);
        }
    }
}
