package task;

import java.util.ArrayList;
import java.util.Scanner;

import model.Task;
import model.User;

public class TaskMenu {
    private ArrayList<Task> tasks;
    private User user;
    public TaskMenu(ArrayList<Task> tasks, User user) {
        this.tasks = tasks;
        this.user = user;
    }
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        TaskNearest taskNearest = new TaskNearest(tasks, user);
        taskNearest.show();
        while (true) {
            System.out.println("===== Tascal =====");
            System.out.println("1. タスク一覧");
            System.out.println("2. タスク登録");
            System.out.println("3. タスク更新");
            System.out.println("4. タスク削除");
            System.out.println("5. タスク検索");
            System.out.println("6. ログアウト");

            System.out.print("番号を選択してください：");
            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("数字を入力してください。");
                scanner.nextLine();
                continue;
            }
            if (choice == 1) {
                Tasklist tasklist = new Tasklist(tasks, user);
                tasklist.list();
            } else if (choice == 2) {
                TaskCreate taskCreate = new TaskCreate(tasks, user);
                taskCreate.create();
            } else if (choice == 3) {
                TaskUpdate taskUpdate = new TaskUpdate(tasks, user);
                taskUpdate.update();
            } else if (choice == 4) {
                TaskDelete taskDelete = new TaskDelete(tasks, user);
                taskDelete.delete();
            } else if (choice == 5) {
                TaskSearch taskSearch = new TaskSearch(tasks, user);
                taskSearch.search();
            } else if (choice == 6) {
                System.out.println("ログアウトしました。");
                return;
            } else {
                System.out.println("1～6を入力してください。");
            }
        }
    }
}