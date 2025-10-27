import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import service.InMemoryTaskManager;
import service.Managers;
import service.TaskManager;
import service.TaskManagerType;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getTaskManager(TaskManagerType.FILE_BACKED);

        Task task1 = new Task("Сходить в магазин", "Купить такого-сякого", Status.NEW);
        Task task2 = new Task("Постирать вещи", "Закинуть все грязные вещи в стирку", Status.NEW);
        Task task3 = new Task("Приготовить", "Любимый борщ", Status.NEW);

        Epic epic1 = new Epic("Организовать свадьбу", "Что нужно?", Status.NEW);
        Epic epic2 = new Epic("Организовать юбилей маме", "Кого задействовать?", Status.DONE);

        Subtask subtask1 = new Subtask(4, "Найти тамаду", "Веселый и умный", Status.DONE);
        Subtask subtask2 = new Subtask(4, "Забронировать ресторан", "Красивый и дорогой", Status.DONE);
        Subtask subtask3 = new Subtask(4, "Подать заявление в ЗАГС", "Не забыть документы", Status.DONE);
        Subtask subtask4 = new Subtask(5, "Заказать именной торт", "Не забыть указать надпись - с юбилеем", Status.NEW);
        Subtask subtask5 = new Subtask(5, "Организовать стол", "В элитном ресторане", Status.NEW);
        Subtask subtask6 = new Subtask(5, "Жене сказать, чтобы купила букет цветов", "Самый большой букет в магазине", Status.NEW);

        System.out.println("Добавление задачи: ");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);
        taskManager.addSubtask(subtask5);
        taskManager.addSubtask(subtask6);

        printIssuesAndHistory(taskManager);


        System.out.println("Изменение задачи: ");
        Task updatedTask = new Task("Влажная уборка", "Всей квартиры", Status.IN_PROGRESS);
        taskManager.updatedTask(1, updatedTask);


        System.out.println("Удаление задачи: ");
        taskManager.removeTaskByID(2);

        System.out.println("Изменение задачи_2: ");
        taskManager.updatedTask(3, task1);

        printIssuesAndHistory(taskManager);

        System.out.println("Очистка всех tasks");
        taskManager.removeAllTasks();
        taskManager.removeAllSubtasks();

        printIssuesAndHistory(taskManager);


        /*
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.getEpicById(1);
        taskManager.getEpicById(2);
        taskManager.getEpicById(2);
        taskManager.getEpicById(1);
        taskManager.getSubtaskById(1);
        taskManager.getSubtaskById(2);
        taskManager.getSubtaskById(3);
        taskManager.getSubtaskById(4);
        taskManager.getSubtaskById(5);
        taskManager.getSubtaskById(6);


         */



        /*
        taskManager.updateEpicByStatus(1);
        System.out.println(taskManager.getAllEpics());

        Epic updatedEpic = new Epic("Поехать отдыхать за границу", "Что нужно?", Status.NEW);
        //taskManager.updateEpic(2, updatedEpic);
        System.out.println(taskManager.getAllEpics());


        Subtask updatedSubtask = new Subtask(2, "Торт испечем сами", "Сделанное своими руками всегда ценится", Status.NEW);
        //taskManager.updateSubtask(4, updatedSubtask);
        System.out.println(taskManager.getAllSubtask());


        System.out.println(taskManager.getHistory());


        //taskManager.removeEpicById(1);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtask());

        //taskManager.removeSubtaskById(4);
        System.out.println(taskManager.getAllSubtask());



        taskManager.updateEpicByStatus(2);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtaskByIdEpic());


        //taskManager.removeAllEpics();
        //taskManager.removeAllSubtasks();

         */


    }

    private static void printIssuesAndHistory(TaskManager taskManager) {
        System.out.println("Список задач");
        taskManager.getTasks().forEach(task -> {
            System.out.println(task.toString());
        });

        taskManager.getAllEpics().forEach(epic -> {
            System.out.println(epic.toString());
        });

        taskManager.getAllSubtask().forEach(subtask -> {
            System.out.println(subtask.toString());
        });

        System.out.println("\nИстория просмотров");
        taskManager.getHistory().forEach(task -> {
            System.out.println(task);
        });
    }
}

