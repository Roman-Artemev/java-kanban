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
    private Subtask subtask1;
    int MAX_SIZE = 10;

    @BeforeEach
    public void setUp() {
        manager = new InMemoryHistoryManager();

        taskManager = new InMemoryTaskManager();
        task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
        epic = new Epic("Организовать свадьбу", "Что нужно?", Status.NEW);
        subtask = new Subtask(1, "Найти тамаду", "Веселый и умный", Status.NEW);
        subtask1 = subtask;

    }

    @Test
    public void testAddSingleTask() {
        taskManager.addTask(task);
        manager.add(task);
        assertEquals(1, manager.getHistory().size());
        assertEquals(task, manager.getHistory().get(0));
    }

    @Test
    public void testAddMultipleTasks() {
        taskManager.addTask(task);
        taskManager.addTask(epic);
        taskManager.addTask(subtask);
        taskManager.addTask(subtask1);
        manager.add(task);
        manager.add(epic);
        manager.add(subtask);
        manager.add(subtask1);

        assertEquals(3, manager.getHistory().size());
        assertEquals(task, manager.getHistory().get(0));
        assertEquals(epic, manager.getHistory().get(1));
        assertEquals(subtask, manager.getHistory().get(2));
    }

    @Test
    void testRemoveTaskById() {
        taskManager.addTask(task);
        taskManager.addTask(epic);
        taskManager.addTask(subtask);
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
    void testUpdateExistingTask() {
        taskManager.addTask(task);
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