package test;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTaskManagerTest {
    InMemoryTaskManager taskManager;
    InMemoryHistoryManager historyManager;
    Task task;
    Task task1;
    Epic epic;
    Epic epic1;
    Subtask subtask;
    Subtask subtask1;
    Subtask subtask2;
    Subtask subtask3;

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
        task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
        task1 = new Task("Test addNewTask_1", "Test addNewTask description_1", Status.NEW);

        epic = new Epic("Организовать свадьбу", "Что нужно?", Status.NEW);
        epic1 = new Epic("Организовать юбилей маме", "Кого задействовать?", Status.NEW);

        subtask = new Subtask(1, "Найти тамаду", "Веселый и умный", Status.NEW);
        subtask1 = new Subtask(1, "Забронировать ресторан", "Красивый и дорогой", Status.NEW);
        subtask2 = new Subtask(1, "Подать заявление в ЗАГС", "Не забыть документы", Status.DONE);
        subtask3 = new Subtask(1, "Подать заявление в ЗАГС", "Не забыть документы", Status.IN_PROGRESS);

    }

    @Test
    void getHistory() {
        taskManager.addTask(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void addTask() {
        final int taskId = taskManager.addTask(task);

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи совпадают.");
        Task newTask = tasks.get(0);
        assertEquals(task, newTask);
    }

    @Test
    void removeAllTasks() {
        taskManager.addTask(task);
        taskManager.addTask(task1);
        assertEquals(2, taskManager.getTasks().size());

        taskManager.removeAllTasks();
        assertTrue(taskManager.getTasks().isEmpty());
        assertEquals(0, taskManager.getTasks().size(), "Список пуст");
    }

    @Test
    void removeTaskByID() {
        final int taskId = taskManager.addTask(task);
        Task savedTask = taskManager.getTaskById(taskId);
        assertEquals(1, taskManager.getTasks().size());
        assertEquals(task, savedTask);
        taskManager.removeTaskByID(1);
        assertTrue(taskManager.getTasks().isEmpty());
        assertEquals(0, taskManager.getTasks().size(), "Список пуст");
    }

    @Test
    void getTasks() {
        final int taskId = taskManager.addTask(task);
        Task savedTask = taskManager.getTaskById(taskId);
        final int taskId1 = taskManager.addTask(task1);
        Task savedTask1 = taskManager.getTaskById(taskId1);
        assertNotNull(taskManager.getTasks(), "Задачи не возвращаются.");
        assertEquals(2, taskManager.getTasks().size());
        assertEquals(task, savedTask);
        assertEquals(task1, savedTask1);

        final List<Task> tasks = taskManager.getTasks();
        assertEquals(tasks, taskManager.getTasks());
    }

    @Test
    void getTaskById() {
        final int taskId = taskManager.addTask(task);
        Task savedTask = taskManager.getTaskById(taskId);
        assertEquals(1, taskManager.getTasks().size());
        taskManager.getTaskById(1);
        assertEquals(1, taskManager.getTasks().size(), "Длина массива - 1");
        assertEquals(task, savedTask);
    }

    @Test
    void updatedTask() {
        final int taskId = taskManager.addTask(task);
        Task savedTask = taskManager.getTaskById(taskId);
        final List<Task> tasks = taskManager.getTasks();
        taskManager.updatedTask(1, task1);
        assertEquals(task1, tasks.get(0));
        assertEquals(task1, savedTask);
    }

    @Test
    void addEpic() {
        final int epicId = taskManager.addEpic(epic);

        final Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final List<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи совпадают.");
        Epic newEpic = epics.get(0);
        assertEquals(epic, newEpic);
    }

    @Test
    void addSubtask() {
        taskManager.addEpic(epic);

        final int subtaskId = taskManager.addSubtask(subtask);

        final Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают.");

        final List<Subtask> subtaks = taskManager.getAllSubtask();

        assertNotNull(subtaks, "Задачи не возвращаются.");
        assertEquals(1, subtaks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtaks.get(0), "Задачи совпадают.");

        assertEquals(1, epic.subtaskIds.size());
        assertEquals(Status.NEW, savedSubtask.getStatus());
    }

    @Test
    void removeAllEpics() {
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        assertEquals(1, taskManager.getAllEpics().size());
        assertEquals(2, epic.subtaskIds.size());

        taskManager.removeAllEpics();
        assertTrue(taskManager.getAllEpics().isEmpty());
        assertTrue(taskManager.getAllSubtask().isEmpty());
        assertEquals(0, taskManager.getAllEpics().size(), "Список пуст");
        assertEquals(0, taskManager.getAllSubtask().size(), "Список пуст");
    }

    @Test
    void removeAllSubtasks() {
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        assertEquals(1, taskManager.getAllEpics().size());
        assertEquals(2, epic.subtaskIds.size());

        taskManager.removeAllSubtasks();
        assertTrue(taskManager.getAllSubtask().isEmpty());
        assertEquals(0, epic.subtaskIds.size());
        assertEquals(Status.NEW, epic.getStatus());
    }

    @Test
    void getAllEpics() {
        taskManager.addEpic(epic);
        taskManager.addEpic(epic1);
        final List<Epic> epics = taskManager.getAllEpics();
        assertEquals(2, epics.size(), "Неверное количество задач.");
        assertEquals(epics, taskManager.getAllEpics(), "Неверное количество задач.");
    }

    @Test
    void getAllSubtask() {
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        final List<Subtask> subtasks = taskManager.getAllSubtask();
        assertEquals(3, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtasks, taskManager.getAllSubtask(), "Неверное количество задач.");
    }

    @Test
    void removeEpicById() {
        final int epicId = taskManager.addEpic(epic);
        final int epicId1 = taskManager.addEpic(epic1);

        final List<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(2, epics.size(), "Неверное количество задач.");
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        taskManager.removeEpicById(epicId);
        assertEquals(1, taskManager.getAllEpics().size(), "Список пуст");
        assertEquals(0, taskManager.getAllSubtask().size(), "Список пуст");
        assertEquals(0, epic.subtaskIds.size());
    }

    @Test
    void removeSubtaskById() {
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        assertEquals(2, epic.subtaskIds.size());

        taskManager.removeSubtaskById(1);
        assertEquals(1, taskManager.getAllSubtask().size(), "Список пуст");
        assertEquals(1, epic.subtaskIds.size());
    }

    @Test
    void getEpicById() {
        taskManager.addEpic(epic);
        taskManager.addEpic(epic1);
        assertEquals(2, taskManager.getAllEpics().size(), "Список пуст");
        Epic epic3 = taskManager.getEpicById(2);
        assertEquals(epic1, epic3);

    }

    @Test
    void getSubtaskById() {
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        assertEquals(2, epic.subtaskIds.size());

        assertEquals(subtask1, taskManager.getSubtaskById(2));

        final List<Subtask> subtaks = taskManager.getAllSubtask();
        assertEquals(taskManager.getSubtaskById(2), subtaks.get(1));
    }

    @Test
    void updateEpic() {
        taskManager.addEpic(epic);
        Epic newEpic = new Epic("Организовать юбилей маме", "Кого задействовать?", Status.NEW);
        final List<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи совпадают.");
        taskManager.updateEpic(1, newEpic);

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(newEpic, epics.get(0), "Задачи совпадают.");
    }

    @Test
    void updateSubtask() {
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask);
        taskManager.addSubtask(subtask1);
        final List<Subtask> subtaks = taskManager.getAllSubtask();
        assertNotNull(subtaks, "Задачи не возвращаются.");
        assertEquals(2, subtaks.size(), "Неверное количество задач.");
        assertEquals(2, epic.subtaskIds.size());

        Subtask newSubtask = new Subtask(1, "Подать заявление в ЗАГС", "Не забыть документы", Status.NEW);

        taskManager.updateSubtask(2, newSubtask);
        assertEquals(2, subtaks.size(), "Неверное количество задач.");
        assertEquals(2, epic.subtaskIds.size());
        assertEquals(newSubtask, subtaks.get(1));

    }

    @Test
    void updateEpicByStatus() {
        Epic newEpic = new Epic("Организовать юбилей маме", "Кого задействовать?", Status.DONE);
        taskManager.addEpic(newEpic);
        final List<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        taskManager.updateEpicByStatus(newEpic.getId());
        assertEquals(Status.NEW, newEpic.getStatus());

        taskManager.addSubtask(subtask2);
        taskManager.updateEpicByStatus(newEpic.getId());
        assertEquals(Status.DONE, newEpic.getStatus());
        taskManager.removeSubtaskById(1);

        taskManager.addSubtask(subtask3);
        taskManager.updateEpicByStatus(newEpic.getId());
        assertEquals(Status.IN_PROGRESS, newEpic.getStatus());
    }
}