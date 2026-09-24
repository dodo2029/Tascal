package task;

import java.util.ArrayList;
import java.util.Scanner;

import model.Task;
import model.User;
import service.TaskFileService;
import service.TaskRepository;

public class TaskDelete {
    private ArrayList<Task> tasks;
    private User user;
    public TaskDelete(ArrayList<Task> tasks, User user) {
        this.tasks = tasks;
        this.user = user;
    }
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== タスク削除 =====");
        System.out.println("qで戻る");
        System.out.print("削除するタスクIDを入力してください：");
        String taskId = scanner.nextLine();
        if (taskId.equals("q")) {
            return;
        }
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)
                    && task.getUserId() == user.getUserId()) {
                System.out.println("タスク名：" + task.getTaskName());
                System.out.print("本当に削除しますか？（y/n）：");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    tasks.remove(task);
                    TaskRepository taskRepository =
                            new TaskFileService();
                    try {
                        taskRepository.saveTasks(tasks);
                    } catch (Exception e) {
                        System.out.println("タスク情報の保存に失敗しました。");
                    }
                    System.out.println("タスクを削除しました");
                } else {
                    System.out.println("削除をキャンセルしました。");
                }

                return;
            }
        }

        System.out.println("タスクが見つかりませんでした。");
    }
}