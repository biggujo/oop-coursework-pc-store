package Users;

import java.io.Serializable;

public class User implements Serializable {

    private TypesOfUsers type;
    private String firstName;
    private String surName;
    private String password;

    public User(TypesOfUsers type, String firstName, String surName, String password) {
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

    public TypesOfUsers getType() {
        return type;
    }

    public String toString() {

        return type.toString() + ',' + firstName + ',' + surName + ',' + password;
    }
}
