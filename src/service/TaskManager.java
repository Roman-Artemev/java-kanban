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
        for(Epic epic: epics.values()) {
            epic.setStatus(Status.DONE);
        }
    }

    public ArrayList<Epic> getAllEpics() {
        /*
        for(Epic epic: epics.values()) {
            for(Integer subtaskIds: epic.subtaskIds) {
                Subtask subtask = subtasks.get(subtaskIds);
                System.out.println(subtask);
            }
        }

         */
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    public void removeEpicById(Integer id) {
        final Epic epic = epics.remove(id);
        for(Integer subtaskId: epic.subtaskIds) {
            subtasks.remove(subtaskId);
        }
    }

    public void removeSubtaskById(Integer id) {
        Subtask subtask = subtasks.remove(id);
        Integer idSubtask = subtask.getEpicId();
        Epic epic = epics.get(idSubtask);
        epic.subtaskIds.remove(idSubtask);
        if (epic.subtaskIds.isEmpty()) {
            epic.setStatus(Status.DONE);
        }
    }

    public Epic getEpicById(Integer id) {
        /*
        Epic epic = epics.get(id);
        for(Integer subtaskId: epic.subtaskIds) {
            subtasks.get(subtaskId);
        }

         */
        return epics.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public void updateEpic(Integer id, Epic updatedEpic) {
        updatedEpic.setId(id);
        epics.put(updatedEpic.getId(), updatedEpic);
        updatedEpic.setStatus(Status.NEW);

        for(Integer subtaskId: updatedEpic.subtaskIds) {
            updatedEpic.subtaskIds.remove(subtaskId);
            subtasks.remove(subtaskId);
        }
    }

    public void updateSubtask(Subtask updatedSubtask) {
        if (subtasks.containsKey(updatedSubtask.getId())) {
            subtasks.put(updatedSubtask.getId(), updatedSubtask);
            updatedSubtask.setStatus(Status.IN_PROGRESS);
        }
    }

    public void updateStatusEpicBySubtask() {
        for(Epic epic: epics.values()) {
            for (Integer subtaskIds : epic.subtaskIds) {
                Subtask subtask = subtasks.get(subtaskIds);
                if (subtask.getStatus() == Status.DONE) {
                    epic.setStatus(Status.DONE);
                }
                if (subtask.getStatus() == Status.IN_PROGRESS) {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
        }
    }
}