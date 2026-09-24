package service;

import java.io.IOException;
import java.util.List;

import model.Task;

public interface TaskRepository {
    void saveTasks(List<Task> tasks) throws IOException;
    List<Task> loadTasks() throws IOException;
}