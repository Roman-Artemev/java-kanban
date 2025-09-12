import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Сходить в магазин", "Купить такого-сякого", Status.NEW);
        Task task2 = new Task("Постирать вещи", "Закинуть все грязные вещи в стирку", Status.NEW);
        Task task3 = new Task("Приготовить", "Любимый борщ", Status.NEW);

        Epic epic1 = new Epic("Организовать свадьбу", "Что нужно?", Status.NEW);
        Epic epic2 = new Epic("Организовать юбилей маме", "Кого задействовать?", Status.NEW);

        Subtask subtask1 = new Subtask(1, "Найти тамаду", "Веселый и умный", Status.NEW);
        Subtask subtask2 = new Subtask(1, "Забронировать ресторан", "Красивый и дорогой", Status.NEW);
        Subtask subtask3 = new Subtask(1, "Подать заявление в ЗАГС", "Не забыть документы", Status.NEW);
        Subtask subtask4 = new Subtask(2,"Заказать именной торт", "Не забыть указать надпись - с юбилеем", Status.NEW);
        Subtask subtask5 = new Subtask(2, "Организовать стол", "В элитном ресторане", Status.NEW);
        Subtask subtask6 = new Subtask(2, "Жене сказать, чтобы купила букет цветов", "Самый большой букет в магазине", Status.NEW);
/*
        System.out.println(taskManager.addTask(task1));
        System.out.println(taskManager.addTask(task2));
        System.out.println(taskManager.addTask(task3));

        System.out.println(taskManager.getTasks());
        Task updatedTask = new Task("Влажная уборка", "Всей квартиры", Status.NEW);
        taskManager.updatedTask(1, updatedTask);
        System.out.println(taskManager.getTasks());
        taskManager.removeTaskByID(2);
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getTaskById(1));
        taskManager.updatedTask(2, task1);
        System.out.println(taskManager.getTasks());

        taskManager.removeAllTasks();
        System.out.println(taskManager.getTasks());


 */
        System.out.println(taskManager.addEpic(epic1));
        System.out.println(taskManager.addEpic(epic2));

        System.out.println(taskManager.addSubtask(subtask1));
        System.out.println(taskManager.addSubtask(subtask2));
        System.out.println(taskManager.addSubtask(subtask3));
        System.out.println(taskManager.addSubtask(subtask4));
        System.out.println(taskManager.addSubtask(subtask5));
        System.out.println(taskManager.addSubtask(subtask6));


        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtask());

        System.out.println(taskManager.getEpicById(1));
        System.out.println(taskManager.getSubtaskById(2));

        Epic updatedEpic = new Epic("Поехать отдыхать за границу", "Что нужно?", Status.NEW);
        taskManager.updateEpic(2, updatedEpic);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtask());

        Subtask updatedSubtask = new Subtask(2, "Торт испечем сами", "Сделанное своими руками всегда ценится", Status.NEW);
        taskManager.updateSubtask(subtask3);

        /*
        taskManager.removeEpicById(2);
        taskManager.removeSubtaskById(4);

        taskManager.removeAllEpics();
        taskManager.removeAllSubtasks();

         */

    }
}