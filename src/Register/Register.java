package Register;

import java.util.ArrayList;
import java.util.Scanner;

import login.login;
import model.Task;
import model.User;
import service.UserFileService;
import service.UserRepository;
import service.UserService;

public class Register {
	private ArrayList<User> users;
	private ArrayList<Task> tasks;
	public Register(ArrayList<User> users) {
	    this.users = users;
	}
    public void register() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        System.out.println("===== 新規登録 =====");
        System.out.print("ユーザーネーム：");
        String userName = scanner.next();
        String email;
        while (true) {

            System.out.print("メールアドレス：");
            email = scanner.next();

            if (userService.isValidEmail(email)) {
                break;
            }
            System.out.println("正しいメールアドレスを入力してください");
        }
        String password;
        while (true) {
            System.out.print("パスワード：");
            password = scanner.next();
            if (userService.isValidPassword(password)) {
                break;
            }

            System.out.println("パスワードは8文字以上で、英字と数字を含めてください");
        }
        User user = new User(
                users.size() + 1,
                userName,
                email,
                password
        );
        
        users.add(user);

        UserRepository userRepository = new UserFileService();

        try {
            userRepository.saveUsers(users);
        } catch (Exception e) {
            System.out.println("ユーザー情報の保存に失敗しました。");
        }

        System.out.println("アカウントを作成しました");

        login login = new login(users, tasks);
        login.login();
    }
}