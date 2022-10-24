package Users;

import java.io.Serializable;
import java.util.StringJoiner;

public class Customer extends User implements Serializable {

    private final String phone;

    public Customer(String firstName, String surName, String phone, String password) {
        super(TypesOfUsers.CUSTOMER, firstName, surName, password);
        this.phone = phone;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(phone);

        return stringJoiner.toString();
    }
}
