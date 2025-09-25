package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    private int generatorId = 1;
    //private int generatorIdEpic = 1;
    //private int generatorIdSubtask = 1;

    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public int addTask(Task task) {
        final int id = generatorId++;
        task.setId(id);
        tasks.put(id, task);
        historyManager.add(task);
        return id;
    }

    @Override
    public void removeAllTasks() {
        for (Task task : tasks.values()) {
            historyManager.remove(task.getId());
        }
        tasks.clear();
    }

    @Override
    public void removeTaskByID(Integer id) {
        tasks.remove(id);
        historyManager.remove(id);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById(Integer id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public void updatedTask(Integer id, Task updatedTask) {
        updatedTask.setId(id);
        tasks.put(updatedTask.getId(), updatedTask);
        historyManager.add(updatedTask);
    }

    @Override
    public int addEpic(Epic epic) {
        final int id = generatorId++;
        epic.setId(id);
        epics.put(id, epic);
        historyManager.add(epic);
        return id;
    }

    @Override
    public int addSubtask(Subtask newSubtask) {
        final int newId = generatorId++;
        newSubtask.setId(newId);
        subtasks.put(newId, newSubtask);

        Integer epicID = newSubtask.getEpicId();
        Epic epic = epics.get(epicID);
        epic.subtaskIds.add(newId);
        epic.setStatus(newSubtask.getStatus());
        historyManager.add(newSubtask);
        return newId;
    }

    @Override
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.subtaskIds.clear();
            epic.setStatus(Status.NEW);
        }
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void removeEpicById(Integer id) {
        Epic epic = epics.get(id);
        for (Integer subtaskId : epic.subtaskIds) {
            subtasks.remove(subtaskId);
        }
        epics.remove(id);
        epic.subtaskIds.clear();
    }

    @Override
    public void removeSubtaskById(Integer id) {
        Subtask subtask = subtasks.remove(id);

        Integer epicID = subtask.getEpicId();
        Epic epic = epics.get(epicID);
        epic.subtaskIds.remove(id);
        epic.setStatus(Status.IN_PROGRESS);
    }

    @Override
    public Epic getEpicById(Integer id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    @Override
    public void updateEpic(Integer id, Epic updatedEpic) {
        updatedEpic.setId(id);
        epics.put(updatedEpic.getId(), updatedEpic);
    }

    @Override
    public void updateSubtask(Integer id, Subtask updatedSubtask) {
        updatedSubtask.setId(id);
        subtasks.put(updatedSubtask.getId(), updatedSubtask);

        Integer epicID = updatedSubtask.getEpicId();
        Epic epic = epics.get(epicID);
        epic.setStatus(updatedSubtask.getStatus());

    }

    @Override
    public void updateEpicByStatus(Integer id) {
        int countByStatusDONE = 0;
        int countByStatusNEW = 0;
        int totalSubtasksInEpicByStatusNEW = 0;
        int totalSubtasksInEpicByStatusDONE = 0;
        Epic epic = epics.get(id);
        if (epic.subtaskIds.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        for (Integer epicId : epic.subtaskIds) {
            Subtask subtask = subtasks.get(epicId);
            if (subtask.getStatus() == Status.DONE) {
                countByStatusDONE++;
                totalSubtasksInEpicByStatusDONE++;
            }

            if (subtask.getStatus() == Status.NEW) {
                countByStatusNEW++;
                totalSubtasksInEpicByStatusNEW++;
            }
        }

        if ((countByStatusDONE == totalSubtasksInEpicByStatusDONE) && (countByStatusDONE != 0)) {
            epic.setStatus(Status.DONE);
        } else if ((countByStatusNEW == totalSubtasksInEpicByStatusNEW) && (countByStatusNEW != 0)) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public ArrayList<Subtask> getAllSubtaskByIdEpic() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();

        for (int i = 1; i <= epics.size(); i++) {
            Epic epic = epics.get(i);
            for (Integer epicId : epic.subtaskIds) {
                Subtask subtask = subtasks.get(epicId);
                subtaskArrayList.add(subtask);
            }
        }
        return subtaskArrayList;
    }
}
