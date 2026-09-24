package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import model.Task;
import model.User;

public class TaskNearest {
    private ArrayList<Task> tasks;
    private User user;
    public TaskNearest(ArrayList<Task> tasks, User user) {
        this.tasks = tasks;
        this.user = user;
    }
    public void show() {
        Task nearestTask = null;
        for (Task task : tasks) {
            if (task.getUserId() == user.getUserId()) {
                if (nearestTask == null
                        || task.getEndDate().isBefore(nearestTask.getEndDate())) {
                    nearestTask = task;
                }
            }
        }
        System.out.println("===== 期限が一番近いタスク =====");
        if (nearestTask == null) {
            System.out.println("タスクはありません。");
            return;
        }
        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(
                today,
                nearestTask.getEndDate()
        );
        System.out.println("タスク名：" + nearestTask.getTaskName());
        System.out.println("締切日：" + nearestTask.getEndDate());
        System.out.println("ステータス：" + nearestTask.getStatus());

        if (days < 0) {
            System.out.println("期限切れ");
        } else if (days == 0) {
            System.out.println("今日が締切です");
        } else {
            System.out.println("あと" + days + "日");
        }
    }
}