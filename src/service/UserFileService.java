package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserFileService implements UserRepository {
    private static final String FILE_NAME = "users.txt";
    @Override
    public void saveUsers(List<User> users) throws IOException {
        try (BufferedWriter writer =
                new BufferedWriter(
                    new FileWriter(FILE_NAME, StandardCharsets.UTF_8))) {
            for (User user : users) {
                writer.write(
                    user.getUserId() + "," +
                    user.getUserName() + "," +
                    user.getEmail() + "," +
                    user.getPassword()
                );
                writer.newLine();
            }
        }
    }
    @Override
    public List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return users;
        }
        try (BufferedReader reader =
                new BufferedReader(
                    new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length == 4) {
                    users.add(new User(
                        Integer.parseInt(data[0].trim()),
                        data[1],
                        data[2],
                        data[3]
                    ));
                }
            }
        }

        return users;
    }
}