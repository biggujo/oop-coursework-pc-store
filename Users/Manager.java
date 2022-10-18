package Users;

import java.io.Serializable;
import java.time.LocalDate;

public class Manager extends User implements Serializable {

    private String middleName;
    private LocalDate birthDate;
    private String jobTitle;
    private String phone;

    public Manager(String firstName, String surName, String middleName, LocalDate birthDate, String jobTitle,
                   String phone, String password) {
        super(TypesOfUsers.MANAGER.toString(), firstName, surName, password);
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.phone = phone;
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
}
