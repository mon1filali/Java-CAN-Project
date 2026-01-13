package Logic.storedUsers;

import Logic.UsersRole;

public class User {
    public String userName;
    public String password;
    public UsersRole role;

    public User(String userName, String password, UsersRole role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UsersRole getRole() {
        return role;
    }
}
