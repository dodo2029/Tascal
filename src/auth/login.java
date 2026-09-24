package auth;

import java.util.ArrayList;
import java.util.Scanner;

import model.Task;
import model.User;
import task.TaskMenu;

public class login {
    private ArrayList<User> users;
    private ArrayList<Task> tasks;
    public login(ArrayList<User> users, ArrayList<Task> tasks) {
        this.users = users;
        this.tasks = tasks;
    }
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== ログイン =====");
        System.out.println("=== おかえりなさい ===");
        System.out.print("アカウント名：");
        String userName = scanner.next();
        System.out.print("メールアドレス：");
        String email = scanner.next();
        System.out.print("パスワード：");
        String password = scanner.next();
        for (User user : users) {
            if (user.getUserName().equals(userName)
                    && user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {
                System.out.println("ログイン");
                System.out.println("ようこそ" + user.getUserName() + "さん");
                TaskMenu taskMenu = new TaskMenu(tasks, user);
                taskMenu.showMenu();
                return;
            }
        }

        System.out.println("アカウント名、メールアドレス、またはパスワードが間違っています。");
    }
}