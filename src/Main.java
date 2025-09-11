import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Сходить в магазин", "Купить такого-сякого");
        Task task2 = new Task("Постирать вещи", "Закинуть все грязные вещи в стирку");
        Task task3 = new Task("Приготовить", "Любимый борщ");

        Epic epic1 = new Epic("Организовать свадьбу", "Что нужно?");
        Epic epic2 = new Epic("Организовать юбилей маме", "Кого задействовать?");

        Subtask subtask1 = new Subtask(1, "Найти тамаду", "Веселый и умный");
        Subtask subtask2 = new Subtask(1, "Забронировать ресторан", "Красивый и дорогой");
        Subtask subtask3 = new Subtask(1, "Подать заявление в ЗАГС", "Не забыть документы");
        Subtask subtask4 = new Subtask(2,"Заказать именной торт", "Не забыть указать надпись - с юбилеем");
        Subtask subtask5 = new Subtask(2, "Организовать стол", "В элитном ресторане");
        Subtask subtask6 = new Subtask(2, "Жене сказать, чтобы купила букет цветов", "Самый большой букет в магазине");

        /*
        System.out.println(taskManager.addTask(task1));
        System.out.println(taskManager.addTask(task2));
        System.out.println(taskManager.addTask(task3));

        System.out.println(taskManager.getTasks());
        taskManager.updateTask(task2);
        System.out.println(taskManager.getTasks());
        taskManager.removeTaskByID(2);
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getTaskById(1));
        taskManager.updateTask(task1);
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

        /*
        taskManager.printAllEpics();
        //taskManager.removeEpicByID(3);
        taskManager.updateEpic(epic1);
        taskManager.printAllEpics();
        taskManager.printEpicById(1);
        taskManager.removeAllEpics();

         */

        /*
        Subtask subtask1 = new Subtask("Пожарить яищницу", "2 яйца пожарить на сковородке в течении 5 минут", Status.NEW);
        Subtask subtask2 = new Subtask("Попить кофе", "Засыпать кофе в кружку и залить горячей водой, перемешать", Status.NEW);
        Subtask subtask3 = new Subtask("Пропылесосить", "Вставить шнур от пылесоса в розетку", Status.NEW);
        Subtask subtask4 = new Subtask("Протереть пыль", "Вытирать сухой тряпкой", Status.NEW);

        taskManager.addSubtask(epic1, subtask1);
        taskManager.addSubtask(epic1, subtask2);
        taskManager.addSubtask(epic2, subtask3);
        taskManager.addSubtask(epic2, subtask4);

        taskManager.printAllSubtasks();
        taskManager.removeAllSubtasks();

         */
    }
}