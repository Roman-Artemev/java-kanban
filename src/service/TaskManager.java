package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;

public interface TaskManager {
    int addTask(Task task);

    void removeAllTasks();

    void removeTaskByID(Integer id);

    ArrayList<Task> getTasks();

    Task getTaskById(Integer id);

    void updatedTask(Integer id, Task updatedTask);

    int addEpic(Epic epic);

    int addSubtask(Subtask newSubtask);

    void removeAllEpics();

    void removeAllSubtasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtask();

    void removeEpicById(Integer id);

    void removeSubtaskById(Integer id);

    Epic getEpicById(Integer id);

    Subtask getSubtaskById(Integer id);

    void updateEpic(Integer id, Epic updatedEpic);

    void updateSubtask(Integer id, Subtask updatedSubtask);

    void updateEpicByStatus(Integer id);

    List<Task> getHistory();

    ArrayList<Subtask> getAllSubtaskByIdEpic();
}