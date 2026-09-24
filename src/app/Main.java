package app;

import java.util.ArrayList;
import java.util.Scanner;

import auth.Register;
import auth.login;
import model.Task;
import model.User;
import service.TaskFileService;
import service.TaskRepository;
import service.UserFileService;
import service.UserRepository;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        UserRepository userRepository = new UserFileService();
        ArrayList<User> users = null;
        TaskRepository taskRepository = new TaskFileService();
        ArrayList<Task> tasks = null;
        try {
            tasks = new ArrayList<>(taskRepository.loadTasks());
        } catch (Exception e) {
            System.out.println("タスク情報の読み込みに失敗しました。");
            tasks = new ArrayList<>();
        }
        try {
            users = new ArrayList<>(userRepository.loadUsers());
        } catch (Exception e) {
            System.out.println("ユーザー情報の読み込みに失敗しました。");
            users = new ArrayList<>();
        }

        Register register = new Register(users);
        System.out.println("===== Tascal =====");
        System.out.println("1. ログイン");
        System.out.println("2. 新規登録");
        System.out.println("3. 終了");
        System.out.println();
        System.out.println("番号を入力して下さい");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("ログインを選択しました");
                login login = new login(users, tasks);
                login.login();
            } else if (choice == 2) {
                register.register();
            } else if (choice == 3) {
                System.out.println("終了します");
            } else {
                System.out.println("1～3の番号を入力してください");
            }
        } else {
            System.out.println("数字を入力してください");
        }
        scanner.close();
    }
}