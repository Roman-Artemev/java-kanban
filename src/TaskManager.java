import java.util.*;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, ArrayList<Subtask>> subtasks = new HashMap<>();


    public void addTask(Task task) {
        tasks.put(task.getId(), task);
        System.out.println("Задача " + task.getName() + " под номером " + (task.getId() + 1) + " добавлена");
    }

    public void removeAllTasks() {
        tasks.clear();
        System.out.println("Все задачи удалены");
    }

    public void removeTaskByID(Integer id) {
        if (tasks.containsKey(id)) {
            System.out.println(tasks.remove(id));
        } else {
            System.out.println("Задачи с данным идентификатором нет");
        }
    }

    public void printAllTasks() {
        for (Integer idTask : tasks.keySet()) {
            System.out.println(tasks.get(idTask));
        }
    }

    public void printTaskById(Integer id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Получить задачу по данном идентификатору невозможно");
        } else {
            System.out.println(tasks.get(id));
        }
    }

    public void updateTask(Task task) {
        System.out.println("Выберите задачу. которую необходимо обновить: ");
        if (tasks.containsValue(task)) {
            task.setStatus(Status.IN_PROGRESS);
            System.out.println(task);
        } else {
            System.out.println("Данную задачу обновить не получилось");
        }
    }

    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        System.out.println("Большая задача " + epic + " под номером " + (epic.getId() + 1) + " добавлена");
    }

    public void removeAllEpics() {
        tasks.clear();
        System.out.println("Все задачи удалены");
    }

    public void removeEpicByID(Integer id) {
        if (epics.containsKey(id)) {
            System.out.println(epics.remove(id));
        } else {
            System.out.println("Большой задачи с данным идентификатором нет");
        }
    }

    public void printAllEpics() {
        for (Integer idEpic : epics.keySet()) {
            System.out.println(epics.get(idEpic));
        }
    }

    public void printEpicById(Integer id) {
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
        for (Integer idEpic: subtasks.keySet()) {
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