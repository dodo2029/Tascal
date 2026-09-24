package service;
import java.io.IOException;
import java.util.List;

import model.User;

public interface UserRepository {
    void saveUsers(List<User> users) throws IOException;
    List<User> loadUsers() throws IOException;
}