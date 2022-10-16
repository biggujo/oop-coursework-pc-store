package People;

import java.time.LocalDate;

public class Manager extends User {

    private String middleName;
    private LocalDate birthDate;
    private String jobTitle;

    public Manager(String firstName, String surName, String middleName, LocalDate birthDate, String jobTitle,
                   String password) {
        super(firstName, surName, password);
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
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

}
