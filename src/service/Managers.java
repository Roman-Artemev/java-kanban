package service;

import java.io.File;

public class Managers {

    public static TaskManager getTaskManager(TaskManagerType type) {
        switch (type) {
            case IN_MEMORY -> {
                return new InMemoryTaskManager();
            }
            case FILE_BACKED -> {
                File saveFile = new File("tasks.csv");
                return FileBackedTaskManager.loadFromFile(saveFile);
            }
        }
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
