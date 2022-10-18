package Users;

import java.io.Serializable;

public class Customer extends User implements Serializable {

    private String phone;

    public Customer(String firstName, String surName, String phone, String password) {
        super(TypesOfUsers.CUSTOMER.toString(), firstName, surName, password);
        this.phone = phone;
    }
}
