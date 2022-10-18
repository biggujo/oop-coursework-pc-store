package Users;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String firstName, String surName, String password) {
        super(TypesOfUsers.ADMIN.toString(), firstName, surName, password);
    }
}
