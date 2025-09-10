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

    static int generatorId;
    static int generatorIdEpics;
    static int generatorIdSubtask;


    public int addTask(Task task) {
        final int id = ++generatorId;
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

    public Task getTaskById(int id) {
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
            task.setStatus(Status.DONE);
        }
    }

    public int addEpic(Epic epic) {
        final int id = ++generatorIdEpics;
        epic.setId(id);
        epics.put(id, epic);
        epic.setStatus(Status.NEW);
        return id;
    }

    public int addSubtask(Subtask subtask) {
        final int id = ++generatorIdSubtask;
        subtask.setId(id);
        subtasks.put(id, subtask);
        subtask.setEpicId(id);
        subtask.setStatus(Status.NEW);
        return id;
    }

    public ArrayList<Integer> getAllEpics() {
        ArrayList<Integer> listSubtasks = new ArrayList<>();
        listSubtasks.add(subtasks.)
        return listSubtasks;
    }
}

// Я уже не знаю к кому обратиться. Помогите, пожалуйста, мне с этой задачей. Как взаимосвязаны listSubtasks и EpicId
//Решите хоть какой-то метод, потому что я не понимаю как они реализованы.. Вообще не понимаю. Мне нужна помощь. я в тупике
// Я не знаю как еще просить помощи. Помогите мне на моем примере. Что не так я делаю?! Помогите !!! Сделайте уже жирные подсказки.
//Напишите код, чтобы я туда точку поставил и все заработало. Мне нужно решение, чтобы проанализировать. Смысл я днями сижу и не понимаю как решать!!!!!!!!!!!



    /*

    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeEpicByID(Integer id) {
        final Epic epic = epics.remove(id);
        for(Integer subtaskId: epic.getSubtaskIds()) {
            subtasks.remove(subtaskId);
        }
    }

    public void getAllEpics() {
        ArrayList<Epic> e = new ArrayList<>(epics.values());
        for(ep)

        for (Integer idEpic : epics.keySet()) {
            System.out.println(epics.get(idEpic));
        }
    }

    public void getEpicById(Integer id) {
        if (!epics.containsKey(id)) {
            System.out.println("Получить большую задачу по данном идентификатору невозможно");
        } else {
            System.out.println(epics.get(id));
        }
    }

    public void updateEpic(Epic epic) {
        System.out.println("Выберите большую задачу. которую необходимо обновить: ");
        if (epics.containsValue(epic)) {
            epic.setStatus(Status.IN_PROGRESS);
            System.out.println(epic);
        } else {
            System.out.println("Данную задачу обновить не получилось");
        }
    }
}
    /*

    public void addSubtask(Epic epic, Subtask subtask) {
        if (subtasks.containsKey(epic.getId())) {
            ArrayList<Subtask> sub = subtasks.get(epic.getId());
            sub.add(subtask);
            System.out.println("Задача " + subtask.getName() + " под номером " + (subtask.getId() + 1) + " добавлена");
        } else {
            ArrayList<Subtask> sub = new ArrayList<>();
            sub.add(subtask);
            subtasks.put(epic.getId(), sub);
            System.out.println("Задача " + subtask.getName() + " под номером " + (subtask.getId() + 1) + " добавлена в большую задачу под номером " + subtasks.get(epic.getId()));
        }
    }

    public void printAllSubtasks() {
        for (Integer idEpic : subtasks.keySet()) {
            System.out.println(idEpic + " : " + subtasks.values());
        }
    }

    public void removeAllSubtasks() {
        subtasks.clear();
        System.out.println("Все задачи удалены");
    }


    public void removeSubtaskByID(Integer id) {

    }

    public void printSubtaskById(Integer id) {

    }

    public void updateSubtask(Subtask subtask) {
        System.out.println("Выберите подзадачу. которую необходимо обновить: ");
        if (subtasks.containsValue(subtask)) {
            subtask.setStatus(Status.IN_PROGRESS);
            System.out.println(subtask);
        } else {
            System.out.println("Данную задачу обновить не получилось");
        }
    }
}

     */
