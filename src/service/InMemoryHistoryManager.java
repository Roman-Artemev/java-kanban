package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public static final int MAX_SIZE = 10;
    List<Task> listTasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (listTasks.size() >= MAX_SIZE) {
            listTasks.remove(0);
        }
        listTasks.add(task);
    }


    @Override
    public List<Task> getHistory() {
        return listTasks;
    }
}
