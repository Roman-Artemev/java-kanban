import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Сходить в магазин", "Купить такого-сякого", Status.NEW);
        Task task2 = new Task("Сходить в магазин", "Купить такого-сякого", Status.NEW);
        Task task3 = new Task("Сходить в магазин", "Купить такого-сякого", Status.NEW);

        /*
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        taskManager.printAllTasks();
        taskManager.updateTask(task1);
        taskManager.removeTaskByID(4);
        taskManager.printAllTasks();;
        taskManager.printTaskById(2);
        taskManager.removeAllTasks();

         */


        Epic epic1 = new Epic("Позавтракать", "Что нужно?", Status.NEW);
        Epic epic2 = new Epic("Сделать уборку", "По квартире", Status.NEW);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        /*
        taskManager.printAllEpics();
        taskManager.removeEpicByID(3);
        taskManager.updateEpic(epic1);
        taskManager.printAllEpics();
        taskManager.printEpicById(1);
        taskManager.removeAllEpics();


         */


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

    }


}
