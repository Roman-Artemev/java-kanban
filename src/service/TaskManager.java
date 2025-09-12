package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int generatorId = 1;
    private int generatorIdEpic = 1;
    private int generatorIdSubtask = 1;


    public int addTask(Task task) {
        final int id = generatorId++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeTaskByID(Integer id) {
        tasks.remove(id);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public void updatedTask(Integer id, Task updatedTask) {
        updatedTask.setId(id);
        tasks.put(updatedTask.getId(), updatedTask);
    }

    public int addEpic(Epic epic) {
        final int id = generatorIdEpic++;
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public int addSubtask(Subtask newSubtask) {
        final int newId = generatorIdSubtask++;
        newSubtask.setId(newId);
        subtasks.put(newId, newSubtask);

        Integer epicID = newSubtask.getEpicId();
        Epic epic = epics.get(epicID);
        epic.subtaskIds.add(newId);

        return newId;
    }

    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setStatus(Status.DONE);
        }
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    public void removeEpicById(Integer id) {
        final Epic epic = epics.remove(id);
        for (Integer subtaskId : epic.subtaskIds) {
            subtasks.remove(subtaskId);
        }
    }

    public void removeSubtaskById(Integer id) {
        subtasks.remove(id);
    }

    public Epic getEpicById(Integer id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public void updateEpic(Integer id, Epic updatedEpic) {
        updatedEpic.setId(id);
        epics.put(updatedEpic.getId(), updatedEpic);
    }

    public void updateSubtask(Integer id, Subtask updatedSubtask) {
        updatedSubtask.setId(id);
        subtasks.put(updatedSubtask.getId(), updatedSubtask);
    }

    private void updateEpicByStatus(Integer id) {
        int countByStatusDONE = 0;
        int countByStatusNEW = 0;
        Epic epic = epics.get(id);
        if (epic.subtaskIds.isEmpty()) {
            epic.setStatus(Status.NEW);
        }

        for (Integer epicId : epic.subtaskIds) {
            Subtask subtask = subtasks.get(epicId);
            if (subtask.getStatus() == Status.DONE) {
                countByStatusDONE++;
            }

            if (subtask.getStatus() == Status.NEW) {
                countByStatusNEW++;
            }
        }

        if (countByStatusDONE == subtasks.size()) {
            epic.setStatus(Status.DONE);
        } else if (countByStatusNEW == subtasks.size()) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}