package People;

import java.io.Serializable;

public class User implements Serializable {

    private String firstName;
    private String surName;
    private String password;

    public User(String firstName, String surName, String password) {
        this.firstName = firstName;
        this.surName = surName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
