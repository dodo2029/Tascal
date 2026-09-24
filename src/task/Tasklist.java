package task;

import java.util.ArrayList;

import model.Task;
import model.User;

public class TaskList {
    private ArrayList<Task> tasks;
    private User user;
    public TaskList(ArrayList<Task> tasks, User user) {
        this.tasks = tasks;
        this.user = user;
    }
    public void list() {
        System.out.println("===== タスク一覧 =====");
        if (tasks.isEmpty()) {
            System.out.println("タスクはありません。");
            return;
        }
        for (Task task : tasks) {
            if (task.getUserId() == user.getUserId()) {
                System.out.println("ID：" + task.getTaskId());
                System.out.println("タスク名：" + task.getTaskName());
                System.out.println("開始日：" + task.getStartDate());
                System.out.println("締切日：" + task.getEndDate());
                System.out.println("ステータス：" + task.getStatus());
                System.out.println("コメント：" + task.getComment());
                System.out.println("--------------------");
            }
        }
    }
}