package task;

import java.util.ArrayList;
import java.util.Scanner;

import model.Task;
import model.User;

public class TaskSearch {
    private ArrayList<Task> tasks;
    private User user;
    public TaskSearch(ArrayList<Task> tasks, User user) {
        this.tasks = tasks;
        this.user = user;
    }
    public void search() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== タスク検索 =====");
        System.out.println("qで戻る");
        System.out.print("検索するタスク名：");
        String keyword = scanner.nextLine();
        if (keyword.equals("q")) {
            return;
        }
        boolean found = false;
        for (Task task : tasks) {
            if (task.getUserId() == user.getUserId()
                    && task.getTaskName().contains(keyword)) {
                found = true;
                System.out.println("ID：" + task.getTaskId());
                System.out.println("タスク名：" + task.getTaskName());
                System.out.println("開始日：" + task.getStartDate());
                System.out.println("締切日：" + task.getEndDate());
                System.out.println("ステータス：" + task.getStatus());
                System.out.println("コメント：" + task.getComment());
                System.out.println("--------------------");
            }
        }

        if (!found) {
            System.out.println("該当するタスクがありません。");
        }
    }
}