package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Task;
public class TaskFileService implements TaskRepository {
    private static final String FILE_NAME = "tasks.txt";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @Override
    public void saveTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter writer =
                new BufferedWriter(
                    new FileWriter(FILE_NAME, StandardCharsets.UTF_8))) {
            for (Task task : tasks) {
                writer.write(
                    task.getTaskId() + "|" +
                    task.getUserId() + "|" +
                    task.getTaskName() + "|" +
                    task.getStartDate().format(FORMATTER) + "|" +
                    task.getEndDate().format(FORMATTER) + "|" +
                    (task.getPriority() == null ? "" : task.getPriority()) + "|" +
                    task.getStatus() + "|" +
                    task.getComment() + "|" +
                    (task.getCreatedAt() == null ? "" : task.getCreatedAt()) + "|" +
                    (task.getUpdatedAt() == null ? "" : task.getUpdatedAt())
                );
                writer.newLine();
            }
        }
    }
    @Override
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader =
                new BufferedReader(
                    new FileReader(FILE_NAME, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length == 10) {
                    Task task = new Task();
                    task.setTaskId(data[0]);
                    task.setUserId(Integer.parseInt(data[1]));
                    task.setTaskName(data[2]);

                    task.setStartDate(
                            LocalDate.parse(data[3], FORMATTER)
                    );
                    task.setEndDate(
                            LocalDate.parse(data[4], FORMATTER)
                    );
                    task.setPriority(data[5]);
                    task.setStatus(data[6]);
                    task.setComment(data[7]);
                    task.setCreatedAt(data[8]);
                    task.setUpdatedAt(data[9]);

                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
}