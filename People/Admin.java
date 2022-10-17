package People;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String password) {
        super("Main", "Admin", password);
    }
}
