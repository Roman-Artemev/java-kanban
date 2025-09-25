package service;

public class Managers {

    public static TaskManager getTaskManager(TaskManagerType type) {
        switch (type) {
            case IN_MEMORY -> {
                return new InMemoryTaskManager();
            }
            case FILE_BACKED -> {
                return null;
            }
        }
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
