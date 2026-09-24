package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Task;
import model.User;
import service.TaskFileService;
import service.TaskRepository;

public class TaskUpdate {
    private ArrayList<Task> tasks;
    private User user;
    public TaskUpdate(ArrayList<Task> tasks, User user) {
        this.tasks = tasks;
        this.user = user;
    }
    private String inputRequired(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            if (input.equals("q")) {
                return null;
            }
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("入力してください。");
        }
    }
    private LocalDate inputDate(Scanner scanner, String message) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd");
        while (true) {
            String input = inputRequired(scanner, message);
            if (input == null) {
                return null;
            }
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("日付は yyyy/MM/dd の形式で入力してください。");
            }
        }
    }
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== タスク更新 =====");
        System.out.println("qで戻る");
        System.out.print("更新するタスクIDを入力してください：");
        String taskId = scanner.nextLine();
        if (taskId.equals("q")) {
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task oldTask = tasks.get(i);
            if (oldTask.getTaskId().equals(taskId)
                    && oldTask.getUserId() == user.getUserId()) {
                String taskName =
                        inputRequired(scanner, "タスク名：");
                if (taskName == null) {
                    return;
                }
                LocalDate startDate =
                        inputDate(scanner, "開始日（年/月/日）：");
                if (startDate == null) {
                    return;
                }
                LocalDate endDate =
                        inputDate(scanner, "締切日（年/月/日）：");
                if (endDate == null) {
                    return;
                }
                String status =
                        inputRequired(scanner, "ステータス：");
                if (status == null) {
                    return;
                }
                String comment =
                        inputRequired(scanner, "コメント：");
                if (comment == null) {
                    return;
                }
                Task newTask = new Task();
                newTask.setTaskId(oldTask.getTaskId());
                newTask.setUserId(oldTask.getUserId());
                newTask.setTaskName(taskName);
                newTask.setStartDate(startDate);
                newTask.setEndDate(endDate);
                newTask.setStatus(status);
                newTask.setComment(comment);

                tasks.set(i, newTask);
                TaskRepository taskRepository =
                        new TaskFileService();
                try {
                    taskRepository.saveTasks(tasks);
                } catch (Exception e) {
                    System.out.println("タスク情報の保存に失敗しました");
                }
                System.out.println("タスクを更新しました");
                return;
            }
        }

        System.out.println("タスクが見つかりませんでした");
    }
}