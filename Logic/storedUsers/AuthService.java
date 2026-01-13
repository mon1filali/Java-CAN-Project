package Logic.storedUsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import Logic.UsersRole;

public class AuthService {
    private static final String USERS_FILE = "users.txt";

    public static User login(String inputUsername, String inputPassword) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(USERS_FILE));

        for (String line : lines) {
            String[] parts = line.split(";");

            String username = parts[0];
            String password = parts[1];
            UsersRole role = UsersRole.valueOf(parts[2]);

            if (username.equals(inputUsername) && password.equals(inputPassword)) {
                return new User(username, password, role);
            }
        }
        return null;
    }
}
