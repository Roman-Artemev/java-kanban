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

        /*
    HashMap<String, ArrayList<String>> listOfDishesByType;

    void addDishes(String dishType, String dishName) {
        if (listOfDishesByType.containsKey(dishType)) {
            ArrayList<String> dishes = listOfDishesByType.get(dishType);
            dishes.add(dishName);
        } else {
            ArrayList<String> dishes = new ArrayList<>();
            dishes.add(dishName);
            listOfDishesByType.put(dishType, dishes);
        }
    }

    void printAllDishesByTypes() {
        for (String type : listOfDishesByType.keySet()) {
            System.out.println(type);
            ArrayList<String> dishes = listOfDishesByType.get(type);
            for (String dish : dishes) {
                System.out.println(dish);
            }
        }
    }

}





/*
    public Employee addEmployee(String firstname, String lastname) {
        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен");
        }

        String key = getKey(firstname, lastname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        Employee addedEmployee = new Employee(firstname, lastname);

        employees.put(key, addedEmployee);
        return addedEmployee;
    }

    public Employee removeEmployee(String firstname, String lastname) {
        Employee removedEmployee = new Employee(firstname, lastname);
        String key = getKey(firstname, lastname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        employees.remove(key, removedEmployee);
        return removedEmployee;
    }

    public Employee findEmployee(String firstname, String lastname) {
        String key = getKey(firstname, lastname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return employees.get(key);
    }

    private String getKey(String firstname, String lastname) {
        return firstname + lastname;
    }

}
}

HashMap<String, ArrayList<String>> listOfDishesByType;
    Random random = new Random();

    DinnerConstructor() {
        listOfDishesByType = new HashMap<>();
    }

    void addDishes(String dishType, String dishName) {
        if (listOfDishesByType.containsKey(dishType)) {
            ArrayList<String> dishes = listOfDishesByType.get(dishType);
            dishes.add(dishName);
        } else {
            ArrayList<String> dishes = new ArrayList<>();
            dishes.add(dishName);
            listOfDishesByType.put(dishType, dishes);
        }
    }

    void printAllDishesByTypes() {
        for (String type : listOfDishesByType.keySet()) {
            System.out.println(type);
            ArrayList<String> dishes = listOfDishesByType.get(type);
            for (String dish : dishes) {
                System.out.println(dish);
            }
        }
    }

    String printMenu(String type) {
        ArrayList<String> dishes = listOfDishesByType.get(type);
        int index = random.nextInt(dishes.size());
        String answer = dishes.get(index);
        return answer;
    }

 */