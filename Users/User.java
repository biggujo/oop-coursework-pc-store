package Users;

import java.io.Serializable;

public class User implements Serializable {

    private String type;
    private String firstName;
    private String surName;
    private String password;

    public User(String type, String firstName, String surName, String password) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {

        return type + ',' + firstName + ',' + surName + ',' + password;
    }
}
