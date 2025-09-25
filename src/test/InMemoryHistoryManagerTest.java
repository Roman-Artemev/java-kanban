package test;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    private InMemoryHistoryManager manager;
    private InMemoryTaskManager taskManager;
    private Task task;
    private Epic epic;
    private Subtask subtask;
    int MAX_SIZE = 10;

    @BeforeEach
    public void setUp() {
        manager = new InMemoryHistoryManager();

        taskManager = new InMemoryTaskManager();
        task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
        epic = new Epic("Организовать свадьбу", "Что нужно?", Status.NEW);
        subtask = new Subtask(1, "Найти тамаду", "Веселый и умный", Status.NEW);

    }

    @Test
    public void testAddSingleTask() {
        manager.add(task);
        assertEquals(1, manager.getHistory().size());
        assertEquals(task, manager.getHistory().get(0));
    }

    @Test
    public void testAddMultipleTasks() {
        manager.add(task);
        manager.add(epic);
        manager.add(subtask);

        assertEquals(3, manager.getHistory().size());
        assertEquals(task, manager.getHistory().get(0));
        assertEquals(epic, manager.getHistory().get(1));
        assertEquals(subtask, manager.getHistory().get(2));
    }

    @Test
    void testRemoveTaskById() {
        manager.add(task);
        manager.add(epic);
        manager.add(subtask);

        manager.remove(2);

        assertEquals(2, manager.getHistory().size());
        assertTrue(manager.getHistory().contains(subtask));
        assertTrue(manager.getHistory().contains(task));
        assertFalse(manager.getHistory().contains(epic));
    }

    @Test
    public void testMaxSize() {
        // Создаем список задач размером MAX_SIZE + 1
        List<Task> tasks = List.of(
                new Task("Task 1", "Task1", Status.NEW),
                new Epic("Epic 2", "Epic2", Status.NEW),
                new Subtask(1, "Subtask3", "Subtask3", Status.NEW),
                new Task("Task 4", "Task4", Status.NEW),
                new Task("Task 5", "Task5", Status.NEW),
                new Task("Task 6", "Task6", Status.NEW),
                new Task("Task 7", "Task7", Status.NEW),
                new Task("Task 8", "Task8", Status.NEW),
                new Task("Task 9", "Task9", Status.NEW),
                new Task("Task 10", "Task10", Status.NEW),
                new Task("Task 11", "Task11", Status.NEW)
        );

        // Добавляем все задачи
        for (Task task : tasks) {
            manager.add(task);
        }

        // Проверяем, что размер списка не превышает MAX_SIZE
        assertEquals(MAX_SIZE, manager.getHistory().size());

        // Проверяем, что первая задача была удалена
        assertNotEquals(tasks.get(0), manager.getHistory().get(0));

        // Проверяем, что последняя задача присутствует
        assertEquals(tasks.get(tasks.size() - 1), manager.getHistory().get(MAX_SIZE - 1));
    }

    @Test
    void testUpdateExistingTask() {
        manager.add(task);

        // Обновляем задачу с тем же ID
        Task updatedTask = new Task(1, "Updated Task 1", "Updated Task1");
        manager.add(updatedTask);

        assertEquals(1, manager.getHistory().size());
        assertEquals(updatedTask, manager.getHistory().get(0));
    }

    @Test
    void testEmptyHistory() {
        assertTrue(manager.getHistory().isEmpty());
    }
}