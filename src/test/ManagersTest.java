package test;

import org.junit.jupiter.api.Test;
import service.*;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void getTaskManager() {
        TaskManager manager = Managers.getTaskManager(TaskManagerType.IN_MEMORY);
        assertNotNull(manager);
        assertTrue(manager instanceof InMemoryTaskManager);

        manager = Managers.getTaskManager(TaskManagerType.FILE_BACKED);
        assertNull(manager);
    }

    @Test
    void getDefaultHistory() {
        HistoryManager history = Managers.getDefaultHistory();
        assertNotNull(history);
        assertTrue(history instanceof InMemoryHistoryManager);
    }
}