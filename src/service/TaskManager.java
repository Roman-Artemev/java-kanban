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


    public int addTask(Task task) {
        final int id = nextId();
        task.setId(id);
        tasks.put(id, task);
        task.setStatus(Status.NEW);
        return id;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeTaskByID(Integer id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Задачи с данным идентификатором нет");
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById(Integer id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Получить задачу по данном идентификатору невозможно");
        }
        return tasks.get(id);
    }

    public void updateTask(Task task) {
        Task updatedTask = new Task("Выучить уроки", "Согласно ДЗ");

        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), updatedTask);
            updatedTask.setStatus(Status.IN_PROGRESS);
            //task.setStatus(Status.DONE);
        }
    }

    public int addEpic(Epic epic) {
        generatorId = 1;
        final int id = nextId();
        epic.setId(id);
        epics.put(id, epic);
        epic.setStatus(Status.NEW);
        return id;
    }

    public int addSubtask(Subtask subtask) {
        final int newId = nextId();
        subtask.setId(newId);
        subtasks.put(newId, subtask);

        Integer epicID = subtask.getEpicId();
        Epic epic = epics.get(epicID);
        epic.subtaskIds.add(newId);

        subtask.setStatus(Status.NEW);

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
        for(Epic epic: epics.values()) {
            for(Integer subtaskIds: epic.subtaskIds) {
                Subtask subtask = subtasks.get(subtaskIds);
                System.out.println(subtask);
            }
        }

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

    public void getEpicById(Integer id) {
        Epic epic = epics.get(id);
        for(Integer subtaskId: epic.subtaskIds) {
            subtasks.get(subtaskId);
        }
    }

    public void getSubtaskById(Integer id) {
        subtasks.get(id);
    }

    public void updateEpic(Epic epic) {
        Epic updatedEpic = new Epic("Организовать поездку на море", "В отпуск");

        if (epics.containsKey(epic.getId())) {
            for(Integer subtaskId: epic.subtaskIds) {
                subtasks.remove(subtaskId);
            }
            epics.put(epic.getId(), updatedEpic);
            updatedEpic.setStatus(Status.IN_PROGRESS);
        }
    }

    public void updateSubtask(Subtask subtask) {
        Subtask updatedSubtask = new Subtask(2, "Торт испечем сами", "Сделанное своими руками всегда ценится");

        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), updatedSubtask);
            updatedSubtask.setStatus(Status.IN_PROGRESS);
        }
    }

    public int nextId() {
        return generatorId++;
    }
}