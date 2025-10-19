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

    @BeforeEach
    public void setUp() {
        manager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager();
        task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
        task.setId(1);
        epic = new Epic("Организовать свадьбу", "Что нужно?", Status.NEW);
        epic.setId(2);
        subtask = new Subtask(2, "Найти тамаду", "Веселый и умный", Status.NEW);
        subtask.setId(3);
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
    public void testGetEmptyHistory() {
        List<Task> history = manager.getHistory();
        assertTrue(history.isEmpty());
        assertEquals(0, history.size());
    }
}