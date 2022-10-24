package Users;

import Hardware.TypesOfHardware;

import java.io.Serializable;
import java.util.StringJoiner;

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

    public void setType(TypesOfUsers type) {

        this.type = type;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",");

        stringJoiner.add(type.toString()).add(firstName).add(surName).add(password);

        return stringJoiner.toString();
    }

    // Quote a string if contains commas
    public static String quotationIfComma(String string) {

        return string.contains(",") ? '"' + string + '"' : string;
    }
}
