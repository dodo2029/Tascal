package service;

public class UserService {
    public boolean isValidEmail(String email) {

        return email.contains("@")
                && email.indexOf("@") < email.lastIndexOf(".");
    }
    public boolean isValidPassword(String password) {

        return password.length() >= 8
                && password.matches(".*[A-Za-z].*")
                && password.matches(".*[0-9].*");
    }
}